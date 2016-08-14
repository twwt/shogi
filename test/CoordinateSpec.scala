import models._
import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by taishi on 8/14/16.
  */
class CoordinateSpec extends FlatSpec with Matchers {
  behavior of "Coordinate"

  val coordinate = Coordinate(1, 1)
  it should "toDirection" in {
    println(Coordinate.toDirection(coordinate, Set(Coordinate(0, 1))))
  }
  it should "toDirection Up" in {
    Coordinate.toDirection(coordinate, Set(Coordinate(1, 2))).isInstanceOf[Up] should equal(true)
  }
  it should "toDirection Down" in {
    Coordinate.toDirection(coordinate, Set(Coordinate(1, 0))).isInstanceOf[Down] should equal(true)
  }
  it should "toDirection Left" in {
    Coordinate.toDirection(coordinate, Set(Coordinate(0, 1))).isInstanceOf[Left] should equal(true)
  }
  it should "toDirection Right" in {
    Coordinate.toDirection(coordinate, Set(Coordinate(2, 1))).isInstanceOf[Right] should equal(true)
  }
  it should "toDirection UpLeft" in {
    Coordinate.toDirection(coordinate, Set(Coordinate(0, 2))).isInstanceOf[UpLeft] should equal(true)
  }
  it should "toDirection UpRight" in {
    Coordinate.toDirection(coordinate, Set(Coordinate(2, 4))).isInstanceOf[UpRight] should equal(true)
  }
  it should "toDirection DownLeft" in {
    Coordinate.toDirection(coordinate, Set(Coordinate(0, 0))).isInstanceOf[DownLeft] should equal(true)
  }
  it should "toDirection DownRight" in {
    Coordinate.toDirection(coordinate, Set(Coordinate(2, 0))).isInstanceOf[DownRight] should equal(true)
  }
}
