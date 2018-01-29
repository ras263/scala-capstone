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
    val filteredPoints =  points.filter((point) => point._1 == value)
    if (filteredPoints.size == 1) return filteredPoints.toList.head._2 else ???
  }

  /**
    * @param temperatures Known temperatures
    * @param colors Color scale
    * @return A 360×180 image where each pixel shows the predicted temperature at its location
    */
  def visualize(temperatures: Iterable[(Location, Temperature)], colors: Iterable[(Temperature, Color)]): Image = {
    ???
  }

}

