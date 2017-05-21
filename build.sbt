scalaVersion := "2.12.2"

organization := "andrasp3a"

name := "fpis"

version := "1.0"

scalacOptions ++= Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-unchecked",
  "-feature",
  "-language:implicitConversions",
  "-language:postfixOps",
  "-Ywarn-dead-code",
  "-Xlint",
  "-Xfatal-warnings",
  "-language:higherKinds"
)

libraryDependencies ++= {
  val scalaTestV = "3.0.1"
  Seq(
    "org.typelevel" %% "cats"      % "0.9.0",
    "org.scalatest" %% "scalatest" % scalaTestV % "test"
  )
}
