package observatory

import scala.collection.mutable

/**
  * 4th milestone: value-added information
  */
object Manipulation {

  /**
    * @param temperatures Known temperatures
    * @return A function that, given a latitude in [-89, 90] and a longitude in [-180, 179],
    *         returns the predicted temperature at this location
    */
  def makeGrid(temperatures: Iterable[(Location, Temperature)]): GridLocation => Temperature = {
    lazy val grid = memoize((gl: GridLocation) => Visualization.predictTemperature(temperatures, Location(gl.lat, gl.lon)))
    grid
  }

  def memoize[I, O](f: I => O): I => O = new mutable.HashMap[I, O]() {
    override def apply(key: I) = getOrElseUpdate(key, f(key))
  }

  /**
    * @param temperaturess Sequence of known temperatures over the years (each element of the collection
    *                      is a collection of pairs of location and temperature)
    * @return A function that, given a latitude and a longitude, returns the average temperature at this location
    */
  def average(temperaturess: Iterable[Iterable[(Location, Temperature)]]): GridLocation => Temperature = {
    (gridLocation: GridLocation) => temperaturess.map(makeGrid(_)(gridLocation)).sum / temperaturess.size
  }

  /**
    * @param temperatures Known temperatures
    * @param normals A grid containing the “normal” temperatures
    * @return A grid containing the deviations compared to the normal temperatures
    */
  def deviation(temperatures: Iterable[(Location, Temperature)],
                normals: GridLocation => Temperature): GridLocation => Temperature = {
    (gridLocation: GridLocation) => {
      val temperature = makeGrid(temperatures)(gridLocation)
      val average = normals(gridLocation)
      if (temperature - average > 0) stdDev(temperature, average) else -stdDev(temperature, average)
    }
  }

  def mean(temps: Iterable[Temperature]): Temperature = {
    temps.sum / temps.size
  }

  def variance(temperature: Temperature, average: Temperature): Temperature = {
    math.pow(temperature - average, 2)
  }

  def stdDev(temperature: Temperature, average: Temperature): Temperature = {
    math.sqrt(variance(temperature, average))
  }


}

