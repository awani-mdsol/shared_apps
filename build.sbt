ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

lazy val root = (project in file("."))
  .settings(
    name := "shared_apps"
  )

ThisBuild / libraryDependencies  ++= Seq(

  "org.typelevel" %% "cats-core" % "2.7.0"
)

lazy val platformscala = project
lazy val scalalib = project
lazy val issues = project
lazy val actions = project
lazy val comments = project
lazy val others = project
