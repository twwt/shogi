name := "shogi"

version := "1.0"

lazy val `shogi` = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  jdbc, cache, ws, specs2 % Test,
  "org.scalatest" %% "scalatest" % "2.2.1" % "test",
  "org.scalatestplus" %% "play" % "1.4.0-M3" % "test"
)

unmanagedResourceDirectories in Test <+= baseDirectory(_ / "target/web/public/test")

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"  