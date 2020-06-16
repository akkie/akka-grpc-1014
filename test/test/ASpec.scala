package test

import akka.util.CompactByteString
import play.api.test._

class ASpec extends PlaySpecification {

  val byteString = CompactByteString.apply("test".getBytes)
}
