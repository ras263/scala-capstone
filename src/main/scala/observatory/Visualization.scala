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
    import math.{abs, acos, sin, cos, toRadians}
    /**
      * Power parameter. May been equals 2 or bigger.
      */
    val p = 16
    val earthRadius = 6371

    /**
      * Great circle distance.
      * @param from First point.
      * @param to Second point.
      * @return Distance in kilometers.
      */
    def distance(from: Location, to: Location): Double = {

      /*def computeSigma(): Double = {
        if (from.lat == to.lat && from.lon == to.lon) {
          0
        } else if (from.lat == -to.lat && from.lon == -(180 - to.lon)) {
          math.Pi
        } else {
          val in = sin(toRadians(from.lat)) * sin(toRadians(to.lat))
              + (cos(toRadians(from.lat)) * cos(toRadians(to.lat)) * cos(toRadians(abs(from.lon - to.lon))))
          if (abs(in) <= 1.0) acos(in) else acos(1.0)
        }
      }

      val sigma = computeSigma()

      earthRadius * sigma*/


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
      *
      * Note : However, running the inverse distance weighting algorithm with
      * small distances will result in huge numbers (since we divide by the distance
      * raised to the power of p), which can be a problem. A solution to this problem
      * is to directly use the known temperature of the close (less than 1 km)
      * location as a prediction.
      *
      * @return Interpolated value of temperature at the specific location.
      */
    def shepardsMethod(): Double = {

      def weight(d: Double): Double = {
        1 / math.pow(d, p)
      }

      /*val ft = temperatures.filter(
        (lt) => {
          abs(lt._1.lat - location.lat) <= 50 &&
            abs(lt._1.lon - location.lon) <= 50
        }
      )*/
      val et = temperatures.filter(_._1 == location)
      if (temperatures.size == 1) et.head._2 else {
        val sums = temperatures.map{
          case (l, t) =>
            val dist = distance(location, l)
            if (dist < 1) return t
            val w = weight(dist)
            if (dist < earthRadius) (w * t, w) else (0.0, 0.0)
        }.reduce(
          (first, second) => (first._1 + second._1, first._2 + second._2)
        )
        if (sums._2 == 0) -60 else sums._1 / sums._2
      }


      // With closest

    }

    def modifiedShepardsMethod(): Temperature = {
      temperatures.map(
        (lt) => {
          val dist = distance(lt._1, location)
          if (dist < 1) return lt._2
          math.pow(math.max(0.0, earthRadius - dist) / (earthRadius * dist), 2)
        }
      ).sum
    }

    shepardsMethod()
    //modifiedShepardsMethod()
  }

  /**
    * @param points Pairs containing a value and its associated color
    * @param value The value to interpolate
    * @return The color that corresponds to `value`, according to the color scale defined by `points`
    */
  def interpolateColor(points: Iterable[(Temperature, Color)], value: Temperature): Color = {
    /*
    * 1) Find two closest colors.
    * 2) Interpolate temperature to color.
    *
    *
    *  // Imprecise method, which does not guarantee v = v1 when t = 1, due to floating-point arithmetic error.
    *  // This form may be used when the hardware has a native fused multiply-add instruction.
    * float lerp(float v0, float v1, float t) {
    *    return v0 + t * (v1 - v0);
    *  }
    *
    *  // Precise method, which guarantees v = v1 when t = 1.
    *  float lerp(float v0, float v1, float t) {
    *    return (1 - t) * v0 + t * v1;
    *  }
    *
    *  if Temperature equal 12.0 then Color could be equal Color(255 255 0)
    *
    * */

    /**
      * Points must be sorted by temperature in ascending order.
      * @param points Sorted color points.
      * @return Two closest colors.
      */
    def findClosestColors(points: Iterable[(Temperature, Color)]): ((Temperature, Color), (Temperature, Color)) = {
      try {
        val colder = points.filter(_._1 <= value).last
        val warmer = points.filter(_._1 >= value).head
        (colder, warmer)
      } catch {
        case exp: Exception => println(points); println(value); throw exp
      }
    }

    def interpolateColorIn(colderColor: (Temperature, Color), warmerColor: (Temperature, Color), value: Temperature): Color = {
      val (t1, Color(r1, g1, b1)) = colderColor
      val (t2, Color(r2, g2, b2)) = warmerColor
      val r = interpolate(r1, r2, t1, t2, value).round.toInt
      val g = interpolate(g1, g2, t1, t2, value).round.toInt
      val b = interpolate(b1, b2, t1, t2, value).round.toInt
      Color(r, g, b)
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
      //y0 * (1 - ((x - x0) / (x1 - x0))) + y1 * ((x - x0) / (x1 - x0))
      y0 + ((x - x0) * ((y1 - y0) / (x1 - x0)))
    }

    val filteredPoints =  points.filter(_._1 == value)
    if (filteredPoints.size == 1)
      filteredPoints.toList.head._2
    else {
      val (colder, warmer) = findClosestColors(points)
      interpolateColorIn(colder, warmer, value)
    }
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

    //todo For testing
    //val temps = temperatures.filter(_._1.lat <= 90 - rows).filter(_._1.lon <= columns - 180)
    //println(temps)

    for {
      i <- 0 until rows
      j <- 0 until columns
    } yield {
      val location = Location(90 - i, j - 180)
      /*if (i == 102 && j == 265) {
        println("Here you are!")
      }*/
      /* First */
      val temperature = predictTemperature(temperatures, location)
      /* Second */
      val color = interpolateColor(colours, temperature)
      /* Create pixel and set it to result array */
      pixelsArray((i * columns) + j) = Pixel.apply(color.red, color.green, color.blue, 255)
      //todo Remove after testing.
      if (j == columns - 1) println(s"Row $i completed.")
      //println(s"Column $j completed.")
    }
    val image = Image(columns, rows, pixelsArray)
    image.output(new java.io.File("target/some-image.png"))
    image
  }

}

