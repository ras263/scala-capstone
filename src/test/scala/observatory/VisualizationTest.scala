package observatory


import org.scalatest.FunSuite
import org.scalatest.prop.Checkers

trait VisualizationTest extends FunSuite with Checkers {


  test("interpolateColor with exact match test") {
    val result = Visualization.interpolateColor(List((12.0, Color(255, 255, 0))), 12.0)
    val expected = Color(255, 255, 0)
    assert(result === expected)
  }


}
