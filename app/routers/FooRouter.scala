package routers

import akka.actor.ActorSystem
import hello.grpc.foo.{ AbstractFooServiceRouter, FooReply, FooRequest }
import javax.inject.Inject

import scala.concurrent.Future

class FooRouter @Inject()(system: ActorSystem)
  extends AbstractFooServiceRouter(system) {

  override def sayFoo(in: FooRequest): Future[FooReply] =
    Future.successful(FooReply(s"Hello, ${in.name}!"))
}
