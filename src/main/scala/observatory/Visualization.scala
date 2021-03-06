package observatory

import com.sksamuel.scrimage.{Image, Pixel}

import scala.math.{atan2, pow, sqrt}

/**
  * 2nd milestone: basic visualization
  */
object Visualization {

  /**
    * @param temperatures Known temperatures: pairs containing a location and the temperature at this location
    * @param location Location where to predict the temperature
    * @return The predicted temperature at `location`
    */
  def predictTemperature(temperatures: Iterable[(Location, Temperature)], location: Location): Temperature = {
    import math.{sin, cos, toRadians}
    /** Power parameter. May been equals 2 or bigger. */
    val p = 2
    val earthRadius = 6371

    def distance(from: Location, to: Location): Double = {
      val Location(latA, lonA) = from
      val Location(latB, lonB) = to
      val latDistance = toRadians(latB - latA)
      val lonDistance = toRadians(lonB - lonA)

      val a = pow(sin(latDistance / 2), 2) +
        cos(toRadians(latA)) * cos(toRadians(latB)) *
          pow(sin(lonDistance / 2), 2)

      val c = 2 * atan2(sqrt(a), sqrt(1 - a))
      c * earthRadius
    }

    /**
      * Compute inverse distance weighting by applying Shepard's method.
      * @return Interpolated value of temperature at the specific location.
      */
    def shepardsMethod(): Double = {

      def weight(d: Double): Double = {
        1 / math.pow(d, p)
      }

      val et = temperatures.filter(_._1 == location)
      if (et.size == 1) et.head._2 else {
        val sums = temperatures.map{
          case (l, t) =>
            val dist = distance(location, l)
            if (dist < 1) return t
            val w = weight(dist)
            (w * t, w)
        }.reduce(
          (first, second) => (first._1 + second._1, first._2 + second._2)
        )
        sums._1 / sums._2
      }
    }

    shepardsMethod()
  }

  /**
    * @param points Pairs containing a value and its associated color
    * @param value The value to interpolate
    * @return The color that corresponds to `value`, according to the color scale defined by `points`
    */
  def interpolateColor(points: Iterable[(Temperature, Color)], value: Temperature): Color = {

    /**
      * Note: Points must be sorted by temperature in ascending order.
      * @param sortedPoints Sorted color points.
      * @return Two closest colors.
      */
    def findTwoClosestFromAscendingOrderedPoints(sortedPoints: Iterable[(Temperature, Color)], temperature: Temperature): ((Temperature, Color), (Temperature, Color)) = {
      val warmest = sortedPoints.last
      val coolest = sortedPoints.head
      if (warmest._1 <= temperature) (warmest, warmest)
      else if (coolest._1 >= temperature) (coolest, coolest)
      else sortedPoints.zip(sortedPoints.tail).filter{
          case ((t1, c1), (t2, c2)) => temperature >= t1 && temperature <= t2
        }.head
    }

    def interpolateColorIn(colderColor: (Temperature, Color), warmerColor: (Temperature, Color), value: Temperature): Color = {
      if (colderColor._2 == warmerColor._2) warmerColor._2
      else if (colderColor._1 == value) colderColor._2
      else if (warmerColor._1 == value) warmerColor._2
      else {
        val (t1, Color(r1, g1, b1)) = colderColor
        val (t2, Color(r2, g2, b2)) = warmerColor
        val r = interpolate(r1, r2, t1, t2, value).round.toInt
        val g = interpolate(g1, g2, t1, t2, value).round.toInt
        val b = interpolate(b1, b2, t1, t2, value).round.toInt
        Color(r, g, b)
      }
    }

    val filteredPoints =  points.filter(_._1 == value)
    if (filteredPoints.size == 1)
      filteredPoints.toList.head._2
    else {
      val (colder, warmer) = findTwoClosestFromAscendingOrderedPoints(points,  value)
      interpolateColorIn(colder, warmer, value)
    }
  }

  /**
    * Compute a linear interpolation between two specific color values.
    * @param y0 Smaller color value.
    * @param y1 Bigger color value.
    * @param x0 Smaller temperature.
    * @param x1 Bigger temperature.
    * @param x Specific temperature.
    * @return
    */
  def interpolate(y0: Int, y1: Int, x0: Double, x1: Double, x: Double): Double = {
    y0 * (1 - ((x - x0) / (x1 - x0))) + y1 * ((x - x0) / (x1 - x0))
  }

  /**
    * @param temperatures Known temperatures
    * @param colors Color scale
    * @return A 360×180 image where each pixel shows the predicted temperature at its location
    */
  def visualize(temperatures: Iterable[(Location, Temperature)], colors: Iterable[(Temperature, Color)]): Image = {
    /*
    * For each coordinate (x, y):
    * 1) Compute average temperature using .predictTemperature() method.
    * 2) Choose color by computed temperature using .interpolateColor() method.
    * For all:
    * 1) Collect all pixels' colors and create image (360x180).
    * */
    val rows = 180
    val columns = 360
    val pixelsArray = Array.ofDim[Pixel](rows * columns)
    val colours = colors.toList.sortWith(_._1 < _._1)

    for {
      i <- 0 until rows
      j <- 0 until columns
    } yield {
      val location = Location(90 - i, j - 180)
      /* First */
      val temperature = predictTemperature(temperatures, location)
      /* Second */
      val color = interpolateColor(colours, temperature)
      /* Create pixel and set it to result array */
      pixelsArray((i * columns) + j) = Pixel.apply(color.red, color.green, color.blue, 127)

    }
    val image = Image(columns, rows, pixelsArray)
    //image.output(new java.io.File("target/some-image.png"))
    image
  }

}

