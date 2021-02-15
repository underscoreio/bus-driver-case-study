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

  "driver.getStopForCurrentIteration" should {
    "identify current stop following repeating pattern" in {
      Driver(1, List(3, 1, 2, 4), Set()).getStopForCurrentIteration(4) shouldBe 3
      Driver(1, List(3, 1, 2, 4), Set()).getStopForCurrentIteration(3) shouldBe 4
      Driver(1, List(3, 1, 2, 4), Set()).getStopForCurrentIteration(100) shouldBe 3
    }
  }

  "driver.hasReceivedGossipFromAll" should {
    "should return true when all other drivers have been heard from" in {
      Driver(1, List(3, 1, 2, 4), Set(2, 3)).hasReceivedGossipFromAll(3) shouldBe true
      Driver(1, List(3, 1, 2, 4), Set(2)).hasReceivedGossipFromAll(3) shouldBe false
      Driver(1, List(3, 1, 2, 4), Set(2, 3)).hasReceivedGossipFromAll(6) shouldBe false
    }
  }

  "driver.withGossip" should {
    "give us a new Driver who has heard gossip from another driver" in {
      val expectedDriver = Driver(1, List(3, 1, 2, 4), Set(2, 3, 4))
      Driver(1, List(3, 1, 2, 4), Set(2, 3)).withGossipFrom(4) shouldBe expectedDriver
    }
  }
}
