package observatory

object Main extends App {

    val result = Extraction.locationYearlyAverageRecords(Extraction.locateTemperatures(2015, "/test_stations.csv", "/1900.csv"))
    result.foreach(println(_))

}
