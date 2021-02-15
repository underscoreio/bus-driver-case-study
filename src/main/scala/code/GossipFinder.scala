package code

case class Driver(id: Int, stops: List[Int], gossipReceivedFrom: Set[Int]) {

  def hasReceivedGossipFromAll(numberOfDrivers: Int) = gossipReceivedFrom.size == numberOfDrivers - 1

  def getStopForCurrentIteration(index: Int) : Int = {
    stops(index % stops.length)
  }

  def withGossipFrom(driver: Int):Driver = {
    this.copy(gossipReceivedFrom = this.gossipReceivedFrom + driver)
  }

}

object GossipFinder extends App {

  def findGossip(drivers: Seq[Driver]): Option[Int]
  =
  {
//    val numberOfDrivers = drivers.size(1 to 480).foreach(index => {}) None
    ???
  }
}