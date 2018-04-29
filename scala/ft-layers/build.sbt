scalaVersion := "2.12.4"

resolvers += Resolver.sonatypeRepo("releases")

addCompilerPlugin(("org.scalameta" % "paradise" % "3.0.0-M11").cross(CrossVersion.full))

libraryDependencies ++= Seq(
  "co.fs2" %% "fs2-core" % "0.10.4",
  "org.http4s" %% "http4s-dsl" % "0.18.8",
  "org.http4s" %% "http4s-server" % "0.18.8",
  "org.http4s" %% "http4s-blaze-server" % "0.18.8",
  "com.h2database" % "h2" % "1.4.197",
  "org.typelevel" %% "cats-effect" % "0.10.1",
  "org.typelevel" %% "cats-core" % "1.1.0",
  "org.tpolecat" %% "doobie-core" % "0.5.2",
  "com.kailuowang" %% "mainecoon-macros" % "0.6.2",
  "org.slf4j" % "slf4j-simple" % "1.8.0-beta2"
)

scalacOptions ++= Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-feature",
  "-unchecked",
  "-Ypartial-unification",
  "-language:higherKinds",
)
