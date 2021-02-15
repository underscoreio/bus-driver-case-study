package code

import code.GossipFinder.Driver
import org.scalatest._

class GossipFinderSpec extends WordSpec with Matchers {
  "gossipFinder" should {
    "example1 - three drivers exchanging all gossip after 5 stops" in {
    //  3 1 2 3
    //  3 2 3 1
    //  4 2 3 4 5
      val drivers = Seq(
      Driver(1, List(3, 1, 2, 3)),
      Driver(2, List(3, 2, 3, 1)),
      Driver(3, List(4, 2, 3, 4, 5))
    )
      GossipFinder.findGossip(drivers) shouldBe Some(5)
    }
  }
}
