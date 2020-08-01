name := "sfik"

version := "0.1"

scalaVersion := "2.13.2"

lazy val root = (project in file("."))
  .aggregate(core)

lazy val core = (project in file("core")).settings(
  libraryDependencies ++= Seq(
    "org.slf4j" % "slf4j-api" % "1.7.30",
    "org.apache.maven" % "maven-resolver-provider" % "3.6.3",
    "org.apache.maven.resolver" % "maven-resolver-connector-basic" % "1.4.2",
    "org.apache.maven.resolver" % "maven-resolver-transport-file" % "1.4.2",
    "org.apache.maven.resolver" % "maven-resolver-transport-http" % "1.4.2",
    "io.circe" %% "circe-yaml" % "0.12.0",
    "io.circe" %% "circe-generic" % "0.12.0",
    "org.scalatest" %% "scalatest" % "3.2.0" % "test",
    "org.scalatest" %% "scalatest-flatspec" % "3.2.0" % "test",
  )
)
