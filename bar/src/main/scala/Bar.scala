import cats.effect._

object Bar {
  Resource.pure[IO, Unit](())
    .evalMap(IO.pure)
}
