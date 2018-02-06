package observatory

import com.sksamuel.scrimage.{Image, Pixel}

/**
  * 3rd milestone: interactive visualization
  */
object Interaction {

  type Data = Iterable[(Location, Temperature)]

  /**
    * @param tile Tile coordinates
    * @return The latitude and longitude of the top-left corner of the tile, as per http://wiki.openstreetmap.org/wiki/Slippy_map_tilenames
    */
  def tileLocation(tile: Tile): Location = {
    tile.toLocation
  }

  /**
    * @param temperatures Known temperatures
    * @param colors Color scale
    * @param tile Tile coordinates
    * @return A 256×256 image showing the contents of the given tile
    */
  def tile(temperatures: Iterable[(Location, Temperature)], colors: Iterable[(Temperature, Color)], tile: Tile): Image = {
    import Visualization._

    val width, height = 256
    val colours = colors.toList.sortWith(_._1 < _._1)

    val pixels = (0 until width * height).par.map(
      pos => {
        val x = (pos % width).toDouble / width + tile.x
        val y = (pos / height).toDouble / height + tile.y

        pos -> interpolateColor(
          colours,
          predictTemperature(temperatures, Location(y, x))
        ).toPixel(127)
      }
    ).toList.sortBy(_._1).map(_._2)

    Image(width, height, pixels.toArray)
  }

  /**
    * Generates all the tiles for zoom levels 0 to 3 (included), for all the given years.
    * @param yearlyData Sequence of (year, data), where `data` is some data associated with
    *                   `year`. The type of `data` can be anything.
    * @param generateImage Function that generates an image given a year, a zoom level, the x and
    *                      y coordinates of the tile and the data to build the image from
    */
  def generateTiles[Data](
    yearlyData: Iterable[(Year, Data)],
    generateImage: (Year, Tile, Data) => Unit
  ): Unit = {

  }

  def generateImage(year: Year, tile: Tile, data: Data): Unit = {

  }

}
