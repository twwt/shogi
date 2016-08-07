name := "shogi"

version := "1.0"

lazy val `shogi` = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

<<<<<<< HEAD
resolvers ++= Seq(
  Resolver.sonatypeRepo("releases"),
  Resolver.sonatypeRepo("snapshots"))

=======
libraryDependencies ++= Seq(
  jdbc, cache, ws, specs2 % Test,
  "org.scalatest" %% "scalatest" % "2.2.1" % "test",
  "org.scalatestplus" %% "play" % "1.4.0-M3" % "test",
  "org.typelevel" %% "cats" % "0.6.1",
  "org.scalaz" %% "scalaz-core" % "7.2.4",
  "com.chuusai" %% "shapeless" % "2.3.1"
)

unmanagedResourceDirectories in Test <+= baseDirectory(_ / "target/web/public/test")
>>>>>>> 7f9a1320c96bd4909156b39f3fceb5e8444f50d5

libraryDependencies ++= Seq(
  jdbc, cache, ws, specs2 % Test,
  "org.scalatest" % "scalatest_2.11" % "2.2.1" % "test",
  "org.scalaz" %% "scalaz-core" % "7.2.4",
  "com.chuusai" %% "shapeless" % "2.3.1"
)

unmanagedResourceDirectories in Test <+= baseDirectory(_ / "target/web/public/test")

