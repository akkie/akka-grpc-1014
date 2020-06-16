resolvers += Resolver.bintrayRepo("playframework", "maven")

libraryDependencies += "com.lightbend.play" %% "play-grpc-generators" % "0.8.2"

addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.8.1")

addSbtPlugin("com.lightbend.akka.grpc" %% "sbt-akka-grpc" % "0.8.4")
