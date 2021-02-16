package code

case class Driver(id: Int, stops: List[Int], gossipReceivedFrom: Set[Int]) {

  def hasReceivedGossipFromAll(numberOfDrivers: Int) = gossipReceivedFrom.size == numberOfDrivers

  def getStopForCurrentIteration(index: Int) : Int = {
    stops(index % stops.length)
  }

  def withGossipFrom(drivers: Set[Int]):Driver = {
    this.copy(gossipReceivedFrom = this.gossipReceivedFrom ++ drivers)
  }

}

object GossipFinder extends App {

  def findGossip(min: Int = 0, drivers: Seq[Driver]): Option[Int] = {
    println(s" min=> $min   driver = $drivers")
    println("======")
    if (min >= 480) None
    else {
      if (drivers.forall(_.hasReceivedGossipFromAll(drivers.size)))
        Some(min)
      else {
        findGossip(min + 1, exchangeGossips(min, drivers))
      }
    }
  }

  def exchangeGossips(min: Int, drivers: Seq[Driver]): Seq[Driver] = {
    drivers.map(driver => exchangeGossip(min, driver, drivers ))
  }
  def exchangeGossip(min: Int, driver: Driver, drivers: Seq[Driver]): Driver = {
    // find stop of driver
    val stop = driver.getStopForCurrentIteration(min)

    //iterate drivers excluding driver => compare whether they are at same stop
    val newGossipReceived =
      drivers.filter(_.getStopForCurrentIteration(min) == stop)
        .flatMap(_.gossipReceivedFrom).toSet
    driver.withGossipFrom(newGossipReceived)
  }
}