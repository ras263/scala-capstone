package observatory

import com.sksamuel.scrimage.Image
import observatory.Interaction.toLocation
import observatory.Visualization.interpolateColor

/**
  * 5th milestone: value-added information visualization
  */
object Visualization2 {

  /**
    * @param point (x, y) coordinates of a point in the grid cell
    * @param d00 Top-left value
    * @param d01 Bottom-left value
    * @param d10 Top-right value
    * @param d11 Bottom-right value
    * @return A guess of the value at (x, y) based on the four known values, using bilinear interpolation
    *         See https://en.wikipedia.org/wiki/Bilinear_interpolation#Unit_Square
    */
  def bilinearInterpolation(
    point: CellPoint,
    d00: Temperature,
    d01: Temperature,
    d10: Temperature,
    d11: Temperature
  ): Temperature = {
    val a = linearInterpolation(d00, d10, point.x)
    val b = linearInterpolation(d01, d11, point.x)
    linearInterpolation(a, b, point.y)
  }

  /**
    * Compute a linear interpolation between two specific temperature values.
    * @param y0 Smaller temperature.
    * @param y1 Bigger temperature.
    * @param x Specific coordinate.
    * @return
    */
  def linearInterpolation(y0: Double, y1: Double, x: Double): Double = {
    y0 * (1 - ((x - 0) / (1 - 0))) + y1 * ((x - 0) / (1 - 0))
  }

  /**
    * @param grid Grid to visualize
    * @param colors Color scale to use
    * @param tile Tile coordinates to visualize
    * @return The image of the tile at (x, y, zoom) showing the grid using the given color scale
    */
  def visualizeGrid(
    grid: GridLocation => Temperature,
    colors: Iterable[(Temperature, Color)],
    tile: Tile
  ): Image = {
    import math.{floor, ceil}
    val width, height = 256
    val colours = colors.toList.sortWith(_._1 < _._1)

    val pixels = (0 until width * height).map(
      pos => {
        val x = (pos % width).toDouble / width + tile.x
        val y = (pos / height).toDouble / height + tile.y
        val l = toLocation(x, y, tile.zoom)

        val (latLow, latHigh) = (floor(l.lat).toInt, ceil(l.lat).toInt)
        val (lonLow, lonHigh) = (floor(l.lon).toInt, ceil(l.lon).toInt)

        pos -> interpolateColor(
          colours,
          bilinearInterpolation(
            CellPoint(l.lon - lonLow, latHigh - l.lat),
            grid(GridLocation(latHigh, lonLow)),
            grid(GridLocation(latLow, lonLow)),
            grid(GridLocation(latHigh, lonHigh)),
            grid(GridLocation(latLow, lonHigh))
          )
        ).toPixel(127)
      }
    ).toList.sortBy(_._1).map(_._2)

    Image(width, height, pixels.toArray)
  }

}
