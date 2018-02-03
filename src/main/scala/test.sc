import observatory.{Color, Location, Temperature, Visualization}

import scala.collection.immutable
import scala.math._


object test {
  "24.5".toDouble

  val a: immutable.IndexedSeq[(Int, Int)] = for {
    j <- (-89 to 90).reverse
    i <- -180 to 179
  } yield {
    //println(s"$i : $j")
    (i, j)
  }
  //test1.foreach(println(_))

  val earthRadius = 6137

  val loc1 = Location(12, 45)
  val loc2 = Location(26, 46)

  val dist = distance(loc1, loc2)
  val dist1 = distance(loc2, loc1)
  val dist2 = distance2(loc1, loc2)


  def distance(from: Location, to: Location): Double = {

    def computeSigma(): Double = {
      if (from.lat == to.lat && from.lon == to.lon) {
        0
      } else if (from.lat == -to.lat && from.lon == -(180 - to.lon)) {
        math.Pi
      } else {
        //val dLambla =
        val in = sin(toRadians(from.lat)) * sin(toRadians(to.lat))
        + (cos(toRadians(from.lat)) * cos(toRadians(to.lat)) * cos(abs(toRadians(from.lon) - toRadians(to.lon))))
        if (abs(in) <= 1.0) acos(in) else acos(1.0)
      }
    }

    val sigma = computeSigma()

    earthRadius * sigma
  }

  def distance2(locA: Location, locB: Location): Double = {
    val Location(latA, lonA) = locA
    val Location(latB, lonB) = locB
    val latDistance = toRadians(latB - latA)
    val lonDistance = toRadians(lonB - lonA)

    val a = pow(sin(latDistance / 2), 2) +
      cos(toRadians(latA)) * cos(toRadians(latB)) *
        pow(sin(lonDistance / 2), 2)

    val c = 2 * atan2(sqrt(a), sqrt(1 - a))
    c * 6371
  }

  val points: List[(Temperature, Color)] = List(
    (60, Color(255, 255, 255)),
    (32, Color(255, 0, 0)),
    (12, Color(255, 255, 0)),
    (0, Color(0, 255, 255)),
    (-15, Color(0, 0, 255)),
    (-27, Color(255, 0, 255)),
    (-50, Color(33, 0, 107)),
    (-60, Color(0, 0, 0))
  )
  val sortedPoints = points.sortWith(_._1 < _._1)

  val b = sortedPoints.zip(sortedPoints.tail)


}