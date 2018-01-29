package observatory

import com.sksamuel.scrimage.{Image, Pixel}

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
    ???
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

    def findClosestColors(points: Iterable[(Temperature, Color)]): ((Temperature, Color), (Temperature, Color)) = {
      val sortedPoints = points.toList.sortWith(_._1 < _._1)
      val smaller = sortedPoints.filter(_._1 <= value).last
      val bigger = sortedPoints.filter(_._1 >= value).head
      (smaller, bigger)
    }

    def interpolateColorIn(colderColor: (Temperature, Color), warmerColor: (Temperature, Color), value: Temperature): Color = {
      val r = interpolate(colderColor._2.red, warmerColor._2.red, colderColor._1, warmerColor._1, value).toInt
      val g = interpolate(colderColor._2.green, warmerColor._2.green, colderColor._1, warmerColor._1, value).toInt
      val b = interpolate(colderColor._2.blue, warmerColor._2.blue, colderColor._1, warmerColor._1, value).toInt
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
      y0 * (1 - ((x - x0) / (x1 - x0))) + y1 * ((x - x0) / (x1 - x0))
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

    for {
      i <- 0 to 359
      j <- 0 to 179
    } yield {
      val location = Location(i, j)
      /* First */
      val temperature = predictTemperature(temperatures, location)
      /* Second */
      val color = interpolateColor(colors, temperature)
      /* Create pixel and set it to result array */
      pixelsArray((i * columns) + j) = Pixel.apply(color.red, color.green, color.blue, 1)
    }
    Image(columns, rows, pixelsArray)
  }

}

