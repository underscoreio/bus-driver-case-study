package code

import org.scalatest._

class GossipFinderSpec extends WordSpec with Matchers {
  "gossipFinder" should {
    "example1 - three drivers exchanging all gossip after 5 stops" in {

      val drivers = Seq(
        Driver(1, List(3, 1, 2, 3), Set()),
        Driver(2, List(3, 2, 3, 1), Set()),
        Driver(3, List(4, 2, 3, 4, 5), Set())
      )
      GossipFinder.findGossip(drivers) shouldBe Some(5)
    }
  }
  "driver with stops 3123" should {
    "getStopForCurrentIteration return 3 when index is 4" in {

      Driver(1, List(3, 1, 2, 3), Set()).getStopForCurrentIteration(4) shouldBe 3
    }
  }
}
