package observatory

import org.scalatest.FunSuite
import org.scalatest.prop.Checkers

import Interaction2._

trait Interaction2Test extends FunSuite with Checkers {


  test("yearSelection test") {
    val layers: Seq[Layer] = availableLayers


    yearSelection(Signal(availableLayers.head), Signal(2055))
    assert(yearSelection(Signal(availableLayers.head), Signal(2055))() === 2015)
    assert(yearSelection(Signal(availableLayers.head), Signal(1945))() === 1975)
    assert(yearSelection(Signal(availableLayers.head), Signal(2005))() === 2005)
  }

  test("consistentness test") {
    val slider = Var(1200)
    val layer = Signal(Interaction2.availableLayers(1))

    val res = Interaction2.yearSelection(layer, slider)
    assert(res() == 1975)

    slider() = 1999
    assert(res() == 1999)
  }

}
