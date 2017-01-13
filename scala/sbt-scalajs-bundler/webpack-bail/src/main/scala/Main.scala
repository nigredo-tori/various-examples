package example

import scala.scalajs.js
import js.JSApp
import js.annotation._

@js.native
@JSImport("no-such-module", "Foo")
class Foo extends js.Any {}

object Main extends JSApp {
  def main(): Unit = {
    new Foo()
  }
}
