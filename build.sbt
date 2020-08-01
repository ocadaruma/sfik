name := "sfik"

version := "0.1"

scalaVersion := "2.13.2"

lazy val root = (project in file("."))
  .aggregate(core)

lazy val core = (project in file("core")).settings(
  libraryDependencies ++= Seq(
    "org.slf4j" % "slf4j-api" % "1.7.30"
  )
)
