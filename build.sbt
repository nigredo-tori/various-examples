scalaVersion in ThisBuild := "2.12.8"

val foo = project.in(file("foo"))
  .settings(
    libraryDependencies += "org.typelevel" %% "cats-effect" % "1.1.0"
  )

val bar = project.in(file("bar"))
  .settings(
    libraryDependencies += "org.typelevel" %% "cats-effect" % "1.2.0"
  ).dependsOn(foo)

val root = project.in(file("."))
  .enablePlugins(ScalaUnidocPlugin)
  .aggregate(foo, bar)
