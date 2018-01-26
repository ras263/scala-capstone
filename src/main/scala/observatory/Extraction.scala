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

  /* Not sure about such implementation. Leave here just as note. Need to be changed. */
  lazy val stations: RDD[Station] = sparkContext.textFile("src/main/resources/stations.csv").map(
    (line) => {
      val lineParts = line.split(",")
      if (lineParts(3).isEmpty || lineParts(4).isEmpty) {
        NotFoundStation()
      } else {
        Station(lineParts(1), lineParts(2), Location(lineParts(3).toDouble, lineParts(4).toDouble))
      }
    }
  ).filter(!_.isInstanceOf[NotFoundStation]).persist()

  case class Station(stn: String, wban: String = "", location: Location)
  case class NotFoundStation() extends Station("", "", Location(200, 200))

  case class TemperatureData(stn: String, wban: String = "", month: Int, day: Int, temperature: Temperature)

  def linesList(year: Year) = Source.fromInputStream(getClass.getResourceAsStream("/" + year + ".csv")).getLines()

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
     * 3) Join stations with temperatures by key.
     *
     */
    val filePath: String = "src/main/resources"
    /* First */
    val stations = sparkContext.textFile(filePath + stationsFile)
      .map(_.split(","))
      .filter((ar) => ar.length == 4 && ar(3).nonEmpty && ar(4).nonEmpty)
      .map(
        (ar) =>
          (ar(1), ar(2)) -> Station(ar(1), ar(2), Location(ar(3).toDouble, ar(4).toDouble))
      )

    /* Second */
    val temperatures = sparkContext.textFile(filePath + temperaturesFile)
      .map(_.split(","))
      .filter((ar) => ar.length == 5)
      .map(
        (td) =>
          (td(1), td(2)) -> TemperatureData(td(1), td(2), td(3).toInt, td(4).toInt, td(5).toDouble)
      )

    /* Third */
    stations.join(temperatures).values.map{
      case (station, temperatureData) =>
        (new LocalDate(year, temperatureData.month, temperatureData.day), station.location, temperatureData.temperature)
    }.collect()
  }

  /**
    * @param records A sequence containing triplets (date, location, temperature)
    * @return A sequence containing, for each location, the average temperature over the year.
    */
  def locationYearlyAverageRecords(records: Iterable[(LocalDate, Location, Temperature)]): Iterable[(Location, Temperature)] = {
    sparkAverageRecords(sparkContext.parallelize(records.toSeq)).collect().toSeq
  }

  // Added method:
  def sparkAverageRecords(
                           records: RDD[(LocalDate, Location, Temperature)]
                         ): RDD[(Location, Temperature)] = {
    ??? // actual work done here
  }

}
