package observatory

import org.scalatest.FunSuite
import org.scalatest.prop.Checkers

import scala.collection.concurrent.TrieMap

trait InteractionTest extends FunSuite with Checkers {

  import Interaction.{tileLocation}

  /*test("tileLocation test") {
    val tile = Tile(0, 0, 1)
    val location = tileLocation(tile)
  }*/

}
