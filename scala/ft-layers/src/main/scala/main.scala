import java.net.InetSocketAddress

import cats.Monad
import cats.implicits._
import cats.effect.IO
import doobie._
import doobie.implicits._
import fs2.{Stream, StreamApp}
import StreamApp.ExitCode
import mainecoon.{autoFunctorK, finalAlg}
import mainecoon.implicits._
import org.http4s.HttpService
import org.http4s.dsl.io._
import org.http4s.server.blaze.BlazeBuilder

// DAO layer
trait Dao[F[_]] {
  def getCounter: F[Int]
  def setCounter(counter: Int): F[Unit]
}

object Dao {
  implicit val dbDao: Dao[ConnectionIO] = new Dao[ConnectionIO] {
    def getCounter =
      sql"select n from counter".query[Int].unique
    def setCounter(counter: Int) =
      sql"update counter set n = $counter".update.run.void
  }
}

// Service layer
@autoFunctorK
@finalAlg
trait Service[F[_]] {
  def click: F[Int]
}

object Service {
  implicit def impl[F[_]: Monad](
    implicit dao: Dao[F]
  ): Service[F] =
    new Service[F] {
      def click: F[Int] =
        dao.getCounter
          .map(_ + 1)
          .flatTap(dao.setCounter)
    }
}

// Top layer
object Main extends StreamApp[IO] {

  import scala.concurrent.ExecutionContext.Implicits.global

  // Dependency inversion wiring
  val xa = Transactor.fromDriverManager[IO](
    "org.h2.Driver",
    "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1",
    "sa", ""
  )

  val service: Service[IO] = {
    val impl: Service[ConnectionIO] = Service[ConnectionIO]

    // Here's where the transactions happen
    impl.mapK[IO](xa.trans)
  }

  // For test purposes
  val initDb = sql"create table counter as (select 0 as n)".update
    .run.transact(xa)

  // HTTP API wiring
  val httpApi = HttpService[IO] {
    case GET -> Root =>
      service.click.flatMap(n => Ok(n.toString))
  }

  val serveHttp: Stream[IO, ExitCode] =
    BlazeBuilder[IO]
      .mountService(httpApi, "")
      .serve

  // Bring it all together
  override def stream(
    args: List[String],
    requestShutdown: IO[Unit]
  ) = Stream.eval_(initDb) ++ serveHttp
}
