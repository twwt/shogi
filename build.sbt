name := "shogi"

version := "2.0"

lazy val `shogi` = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

resolvers ++= Seq(
  Resolver.sonatypeRepo("releases"),
  Resolver.sonatypeRepo("snapshots"),
  "Scalaz Bintray Repo" at "http://dl.bintray.com/scalaz/releases")


libraryDependencies ++= Seq(
  jdbc, cache, ws,
  //  specs2 % Test,
  "org.scalatest" % "scalatest_2.11" % "2.2.1" % "test",
  "org.scalaz" %% "scalaz-core" % "7.2.4"
)

unmanagedResourceDirectories in Test <+= baseDirectory(_ / "target/web/public/test")

