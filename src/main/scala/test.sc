import scala.collection.immutable


object test {
  "24.5".toDouble

  val a: immutable.IndexedSeq[(Int, Int)] = for {
    j <- (-89 to 90).reverse
    i <- -180 to 179
  } yield {
    //println(s"$i : $j")
    (i, j)
  }
  //test1.foreach(println(_))


}