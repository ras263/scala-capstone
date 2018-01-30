package observatory


import org.scalatest.FunSuite
import org.scalatest.prop.Checkers

trait VisualizationTest extends FunSuite with Checkers {


  test("interpolateColor with exact match test") {
    val result = Visualization.interpolateColor(List((12.0, Color(255, 255, 0))), 12.0)
    val expected = Color(255, 255, 0)
    assert(result === expected)
  }

  test("interpolateColor test") {
    val result = Visualization.interpolateColor(
      List(
          (60, Color(255, 255, 255)),
          (32, Color(255, 0, 0)),
          (12, Color(255, 255, 0)),
          (0, Color(0, 255, 255)),
          (-15, Color(0, 0, 255)),
          (-27, Color(255, 0, 255)),
          (-50, Color(33, 0, 107)),
          (-60, Color(0, 0, 0))
      ),
      40.0
    )
    val expected = Color(255, 72, 72)
    assert(result === expected)
  }

  test("predictTemperature test") {

  }


}
