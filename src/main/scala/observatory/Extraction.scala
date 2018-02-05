package observatory

import java.time.LocalDate

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

import scala.io.Source

/**
  * 1st milestone: data extraction
  */
object Extraction {

  def sparkInit(): SparkContext = {
    import org.apache.log4j.{Level, Logger}
    Logger.getLogger("org.apache.spark").setLevel(Level.WARN)

    val conf: SparkConf = new SparkConf().setAppName("Scala capstone.").setMaster("local")
    new SparkContext(conf)
  }

  lazy val sparkContext: SparkContext = sparkInit()

  case class Station(stn: String, wban: String = "", location: Location)
  case class TemperatureData(stn: String, wban: String = "", month: Int, day: Int, temperature: Temperature)

  /**
    * @param year             Year number
    * @param stationsFile     Path of the stations resource file to use (e.g. "/stations.csv")
    * @param temperaturesFile Path of the temperatures resource file to use (e.g. "/1975.csv")
    * @return A sequence containing triplets (date, location, temperature)
    */
  def locateTemperatures(year: Year, stationsFile: String, temperaturesFile: String): Iterable[(LocalDate, Location, Temperature)] = {
    /*
     *
     * 1) Parse stations. Extract stn and wban pair as a key.
     * 2) Parse temperatures of single year. Extract stn and wban pair as a key.
     * 3) Compute celsius from fahrenheit.
     * 4) Join stations with temperatures by key.
     *
     */
§    /* First */
    val stations = sparkContext.parallelize(
      Source.fromInputStream(getClass.getResourceAsStream(stationsFile))
        .getLines().toList)
      .map(_.split(","))
      .filter((ar) => ar.length == 4 && ar(2).nonEmpty && ar(3).nonEmpty)
      .map(
        (ar) =>
          (ar(0), ar(1)) -> Station(ar(0), ar(1), Location(ar(2).toDouble, ar(3).toDouble))
      )

    /* Second and third */
    val temperatures = sparkContext.parallelize(
      Source.fromInputStream(getClass.getResourceAsStream(temperaturesFile))
        .getLines().toList)
      .map(_.split(","))
      .filter((ar) => ar.length == 5)
      .map(
        (td) =>
          (td(0), td(1)) -> TemperatureData(
            td(0),
            td(1),
            td(2).toInt,
            td(3).toInt,
            //(td(4).toDouble - 32) / 1.8 // without rounding
            math.round(((td(4).toDouble - 32) / 1.8) * 100.0) / 100.0 // with rounding
          )
      )

    /* Fourth */
    stations.join(temperatures).values.map{
      case (station, temperatureData) =>
        (LocalDate.of(year, temperatureData.month, temperatureData.day), station.location, temperatureData.temperature)
    }.collect().toSeq
  }

  /**
    * @param records A sequence containing triplets (date, location, temperature)
    * @return A sequence containing, for each location, the average temperature over the year.
    */
  def locationYearlyAverageRecords(records: Iterable[(LocalDate, Location, Temperature)]): Iterable[(Location, Temperature)] = {
    sparkAverageRecords(sparkContext.parallelize(records.toSeq)).collect().toSeq.reverse
  }

  // Added method:
  def sparkAverageRecords(
                           records: RDD[(LocalDate, Location, Temperature)]
                         ): RDD[(Location, Temperature)] = {
    /*
    * Average temperatures for each location.
    * 1) Group elements by location.
    * 2) Prepare pairs by deleting dates and location from value.
    * 3) Compute average of temperatures.
    * */

    /* First */
    val grouped = records.groupBy(_._2)
    /* Second and third */
    grouped.mapValues(
      (seq) => {
        val size = seq.size
        seq.map(_._3).sum / size
      }
    )
  }

}
