import play.grpc.gen.scaladsl.{ PlayScalaClientCodeGenerator, PlayScalaServerCodeGenerator }

name := "akka-grpc-1014"
version := "1.0"

val playVersion = play.core.PlayVersion.current

lazy val `play-scala-grpc-example` = (project in file("."))
  .enablePlugins(PlayScala)
  .enablePlugins(AkkaGrpcPlugin)
  .enablePlugins(PlayAkkaHttp2Support)
  .settings(
    akkaGrpcGeneratedLanguages := Seq(AkkaGrpc.Scala),
    akkaGrpcExtraGenerators += PlayScalaClientCodeGenerator,
    akkaGrpcExtraGenerators += PlayScalaServerCodeGenerator,
    PlayKeys.devSettings ++= Seq(
      "play.server.http.port" -> "disabled",
      "play.server.https.port" -> "9443",
      "play.server.https.keyStore.path" -> "conf/selfsigned.keystore",
      "play.server.provider" -> "play.core.server.AkkaHttpServerProvider"
    )
  )
  .settings(
    libraryDependencies ++= Seq(
      guice,
      "com.lightbend.play"      %% "play-grpc-runtime"   % "0.8.2",
      "com.lightbend.play"      %% "play-grpc-specs2"    % "0.8.2" % Test,
      "com.typesafe.play"       %% "play-test"           % playVersion % Test,
      "com.typesafe.play"       %% "play-specs2"         % playVersion % Test
    )
  )

scalaVersion := "2.13.2"
scalacOptions ++= List("-encoding", "utf8", "-deprecation", "-feature", "-unchecked", "-Wconf:any:warning-verbose")
parallelExecution in Test := false
fork in Test := true
testGrouping in Test := (definedTests in Test).map { tests => {
  val sortedTests = tests.map { test =>
    Tests.Group(test.name, Seq(test), Tests.InProcess)
  }.sortBy(_.name.toLowerCase).flatMap(_.tests)
  Seq(Tests.Group("Tests", sortedTests, Tests.InProcess))
}}.value
