import models._
import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by taishi on 8/14/16.
  */
class PieceSpec extends FlatSpec with Matchers {
  behavior of "Piece"

  it should "farUp" in {
    FarUp.range should equal(Set(Coordinate(0, 2), Coordinate(0, 0), Coordinate(0, 7), Coordinate(0, 3), Coordinate(0, 5), Coordinate(0, 8), Coordinate(0, 4), Coordinate(0, 1), Coordinate(0, 6)))
  }
}