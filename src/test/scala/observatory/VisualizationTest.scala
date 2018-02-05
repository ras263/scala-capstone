package observatory


import org.scalatest.FunSuite
import org.scalatest.prop.Checkers

trait VisualizationTest extends FunSuite with Checkers {

  val points: List[(Temperature, Color)] = List(
    (60, Color(255, 255, 255)),
    (32, Color(255, 0, 0)),
    (12, Color(255, 255, 0)),
    (0, Color(0, 255, 255)),
    (-15, Color(0, 0, 255)),
    (-27, Color(255, 0, 255)),
    (-50, Color(33, 0, 107)),
    (-60, Color(0, 0, 0))
  )
  val sortedPoints: List[(Temperature, Color)] = points.sortWith(_._1 < _._1)

  test("interpolateColor with exact match test") {
    val result = Visualization.interpolateColor(List((12.0, Color(255, 255, 0))), 12.0)
    val expected = Color(255, 255, 0)
    assert(result === expected)
  }

  test("interpolateColor test") {
    val result = Visualization.interpolateColor(
      sortedPoints,
      40.0
    )
    val expected = Color(255, 73, 73)
    assert(result === expected)
  }

  test("interpolateColor test2") {
    val result = Visualization.interpolateColor(
      sortedPoints,
      -75.0
    )
    val expected = Color(0, 0, 0)
    assert(result === expected)
  }

  test("interpolateColor test3") {
    val result = Visualization.interpolateColor(
      sortedPoints,
      84.0
    )
    val expected = Color(255, 255, 255)
    assert(result === expected)
  }

  test("predictTemperature test") {
    val data = Extraction.locationYearlyAverageRecords(Extraction.locateTemperatures(2015, "/test_stations.csv", "/1900.csv"))
    val result = Visualization.predictTemperature(data, Location(37, -78))
    result
  }


}
