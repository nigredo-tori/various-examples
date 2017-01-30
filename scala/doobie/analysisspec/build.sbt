scalaVersion := "2.11.8"

val doobieVer = "0.4.0"

libraryDependencies ++= Seq(
  "org.tpolecat" %% "doobie-core" % doobieVer,
  "org.tpolecat" %% "doobie-postgres" % doobieVer,
  "org.tpolecat" %% "doobie-h2" % doobieVer,
  "org.tpolecat" %% "doobie-specs2" % doobieVer,
  "org.specs2" %% "specs2-core" % "3.8.4" % Test,
  "com.h2database" % "h2" % "1.3.170" % Test
)
