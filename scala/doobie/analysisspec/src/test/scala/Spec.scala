import doobie.imports._
import doobie.specs2.imports._
import doobie.h2.imports._
import org.specs2.mutable.Specification
import org.specs2.specification.dsl.Online._
import org.specs2.specification.BeforeAll

class Spec extends Specification with AnalysisSpec with BeforeAll {

  // Setup
  val initQ = sql"create table some_table (value varchar not null)".update
  val targetQ = sql"select * from some_table".query[String]

  val transactor: Transactor[IOLite] = H2Transactor[IOLite](
    "jdbc:h2:mem:",
    "sa",
    ""
  ).unsafePerformIO

  // The test itself

  check(targetQ)

  // A hook for database initialization
  def beforeAll() = initQ
    .run
    .transact(transactor)
    .unsafePerformIO
}
