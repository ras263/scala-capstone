package observatory

import java.time.LocalDate

import org.scalatest.FunSuite

trait ExtractionTest extends FunSuite {


  test("locateTemperatures test") {
    val result = Extraction.locateTemperatures(2015, "/test_stations.csv", "/1900.csv")
    val expected = Seq(
      (LocalDate.of(2015, 8, 11), Location(37.35, -78.433), 27.3),
      (LocalDate.of(2015, 12, 6), Location(37.358, -78.438), 0.0),
      (LocalDate.of(2015, 1, 29), Location(37.358, -78.438), 2.0)
    )
    assert(result === expected, "Same result isn't exptected.")
  }

  test("locationYearlyAverageRecords test") {
    val result = Extraction.locationYearlyAverageRecords(Extraction.locateTemperatures(2015, "/test_stations.csv", "/1900.csv"))
    val expected = Seq(
      (Location(37.35, -78.433), 27.3),
      (Location(37.358, -78.438), 1.0)
    )
    assert(result === expected, "Same result isn't expected.")
  }

  
}