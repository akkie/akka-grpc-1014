package test

import hello.grpc.foo.{ FooRequest, FooServiceClient }
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.test._
import play.grpc.specs2.ServerGrpcClient

class FooSpec extends ForServer with ServerGrpcClient with PlaySpecification with ApplicationFactories {

  protected def applicationFactory: ApplicationFactory = withGuiceApp(GuiceApplicationBuilder())


  "A Play server bound to a gRPC router" should {
    "work with a gRPC client" >> { implicit rs: RunningServer =>
      withGrpcClient[FooServiceClient] { client: FooServiceClient =>
        val reply = await(client.sayFoo(FooRequest("Alice")))
        reply.message must ===("Hello, Alice!")
      }
    }
  }
}
