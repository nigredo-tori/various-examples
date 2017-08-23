trait Foo[A, B]
object Foo {
  type FooInt[X] = Foo[Int, X]
}
case class Bar(foo: Foo.FooInt[String])
