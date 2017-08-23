case class Foo(
  bar: Bar[Option]
)
case class Bar[F[_]]()
