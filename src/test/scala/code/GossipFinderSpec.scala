package code

import org.scalatest._

class GossipFinderSpec extends WordSpec with Matchers {
  "gossipFinder" should {
    "example1 - three drivers exchanging all gossip after 5 stops" in {

      val drivers = Seq(
        Driver(1, List(3, 1, 2, 3), Set(1)),
        Driver(2, List(3, 2, 3, 1), Set(2)),
        Driver(3, List(4, 2, 3, 4, 5), Set(3))
      )
      GossipFinder.findGossip(0,drivers) shouldBe Some(5)
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
      Driver(1, List(3, 1, 2, 4), Set(2, 3)).withGossipFrom(Set(4)) shouldBe expectedDriver
    }
  }

  //  "driver.exchangeGossip" should {
  //    "exchange gossip with another driver if they are same stop" in {
  //      val driver1 = Driver(1, List(3, 1, 2, 4), Set())
  //      val driver2 = Driver(2, List(3, 2, 3, 1), Set())
  //
  //      driver1.exchangeGossip(driver2)
  //
  //      driver1 shouldBe Driver(1, List(3, 1, 2, 4), Set(2))
  //      driver2 shouldBe Driver(2, List(3, 2, 3, 1), Set(1))
  //
  //    }
  //  }
}