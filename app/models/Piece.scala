package models

/**
  * Created by taishi on 8/14/16.
  */
abstract class Direction(val moveRange: Set[Coordinate])

case class Up(moveRanges: Set[Coordinate]) extends Direction(moveRanges)

case class Down(moveRanges: Set[Coordinate]) extends Direction(moveRanges)

case class Left(moveRanges: Set[Coordinate]) extends Direction(moveRanges)

case class Right(moveRanges: Set[Coordinate]) extends Direction(moveRanges)

case class UpLeft(moveRanges: Set[Coordinate]) extends Direction(moveRanges)

case class UpRight(moveRanges: Set[Coordinate]) extends Direction(moveRanges)

case class DownLeft(moveRanges: Set[Coordinate]) extends Direction(moveRanges)

case class DownRight(moveRanges: Set[Coordinate]) extends Direction(moveRanges)

case class Keima(moveRanges: Set[Coordinate]) extends Direction(moveRanges)

class Piece {
  val ou =
    List(Up(nearUp), Down(nearDown), Left(nearLeft), Right(nearRight), UpLeft(nearUpLeft), UpRight(nearUpRight), DownLeft(nearDownLeft), DownRight(nearDownRight)) 
}
