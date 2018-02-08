package observatory

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
    ??? // Add restrictions
  }

  /**
    * @param temperaturess Sequence of known temperatures over the years (each element of the collection
    *                      is a collection of pairs of location and temperature)
    * @return A function that, given a latitude and a longitude, returns the average temperature at this location
    */
  def average(temperaturess: Iterable[Iterable[(Location, Temperature)]]): GridLocation => Temperature = {
    val temps = temperaturess.flatten.groupBy(_._1).mapValues(
      (data) => {
        mean(data.map(_._2))
      }
    )
    makeGrid(temps)
  }

  /**
    * @param temperatures Known temperatures
    * @param normals A grid containing the “normal” temperatures
    * @return A grid containing the deviations compared to the normal temperatures
    */
  def deviation(temperatures: Iterable[(Location, Temperature)],
                normals: GridLocation => Temperature): GridLocation => Temperature = {

    def result(gridLocation: GridLocation): Temperature = {
      stdDev(makeGrid(temperatures)(gridLocation), normals(gridLocation))
    }

    result
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

