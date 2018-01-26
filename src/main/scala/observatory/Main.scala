package observatory

object Main extends App {




  override def main(args: Array[String]): Unit = {
    val result = Extraction.locationYearlyAverageRecords(Extraction.locateTemperatures(1975, "/stations.csv", "/1975.csv"))
    result.foreach(println(_))
  }


}
