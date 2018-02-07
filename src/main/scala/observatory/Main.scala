package observatory



object Main extends App {

    def firstSecond(): Unit = {
        val dataExtRes = Extraction.locationYearlyAverageRecords(Extraction.locateTemperatures(1975, "/stations.csv", "/1975_test.csv"))
        //val prTempRes = Visualization.predictTemperature(dataExtRes, Location(37.354, -78.436))
        //val a = dataExtRes.toList.sortBy(_._2)

        val colors = List(
            (60.0, Color(255, 255, 255)),
            (32.0, Color(255, 0, 0)),
            (12.0, Color(255, 255, 0)),
            (0.0, Color(0, 255, 255)),
            (-15.0, Color(0, 0, 255)),
            (-27.0, Color(255, 0, 255)),
            (-50.0, Color(33, 0, 107)),
            (-60.0, Color(0, 0, 0))
        )

        val sortedPoints = colors.sortWith(_._1 < _._1)

        val visRes = Visualization.visualize(dataExtRes, colors)
        //dataExtRes.foreach(println(_))
        //println(prTempRes)
    }

    def third(): Unit = {
        import observatory.Interaction.tileLocation

        for {
            i <- 0 to 3
            j <- 0 to 2
            k <- 0 to 2
        } {
            val tile = Tile(j, k, i)
            val location = tileLocation(tile)
            println(tile + " -> " + location)
        }
    }

    firstSecond()

}
