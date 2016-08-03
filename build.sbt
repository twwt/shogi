name := "shogi"

version := "1.0"

lazy val `shogi` = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

resolvers ++= Seq(
  Resolver.sonatypeRepo("releases"),
  Resolver.sonatypeRepo("snapshots"),
  "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"
)


libraryDependencies ++= Seq(
  jdbc, cache, ws, specs2 % Test, "org.scalatest" % "scalatest_2.11" % "2.2.1" % "test",
  "com.chuusai" %% "shapeless" % "2.3.1"
)

unmanagedResourceDirectories in Test <+= baseDirectory(_ / "target/web/public/test")

