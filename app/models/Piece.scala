package models

/**
  * Created by taishi on 8/14/16.
  */
sealed abstract class MoveRange(val range: Set[Coordinate])

case object CanNotMove extends MoveRange(Set(Coordinate(0, 0)))

case object FarUp extends MoveRange(Set(Coordinate(0, 2), Coordinate(0, 0), Coordinate(0, 7), Coordinate(0, 3), Coordinate(0, 5), Coordinate(0, 8), Coordinate(0, 4), Coordinate(0, 1), Coordinate(0, 6)))

case object FarDown extends MoveRange(Set(Coordinate(0, -8), Coordinate(0, 0), Coordinate(0, -6), Coordinate(0, -2), Coordinate(0, -7), Coordinate(0, -3), Coordinate(0, -4), Coordinate(0, -1), Coordinate(0, -5)))

case object FarLeft extends MoveRange(Set(Coordinate(-1, 0)))

case object FarRight extends MoveRange(Set(Coordinate(5, 0), Coordinate(0, 0), Coordinate(4, 0), Coordinate(2, 0), Coordinate(3, 0), Coordinate(8, 0), Coordinate(6, 0), Coordinate(1, 0), Coordinate(7, 0)))

case object FarUpLeft extends MoveRange(Set(Coordinate(0,0), Coordinate(-3,3), Coordinate(-5,5), Coordinate(-4,4), Coordinate(-6,6), Coordinate(-8,8), Coordinate(-2,2), Coordinate(-1,1), Coordinate(-7,7)))

case object FarUpRight extends MoveRange(Set(Coordinate(0, 0), Coordinate(7, 7), Coordinate(6, 6), Coordinate(4, 4), Coordinate(1, 1), Coordinate(8, 8), Coordinate(2, 2), Coordinate(5, 5), Coordinate(3, 3)))

case object FarDownLeft extends MoveRange(Set(Coordinate(-1, -1), Coordinate(-5, -5), Coordinate(0, 0), Coordinate(-3, -3), Coordinate(-8, -8), Coordinate(-4, -4), Coordinate(-6, -6), Coordinate(-2, -2), Coordinate(-7, -7)))

case object FarDownRight extends MoveRange(Set(Coordinate(3, -3), Coordinate(5, -5), Coordinate(0, 0), Coordinate(6, -6), Coordinate(1, -1), Coordinate(7, -7), Coordinate(4, -4), Coordinate(8, -8), Coordinate(2, -2)))

case object NearUp extends MoveRange(Set(Coordinate(0, 1)))

case object NearDown extends MoveRange(Set(Coordinate(0, -1)))

case object NearLeft extends MoveRange(Set(Coordinate(-1, 0)))

case object NearRight extends MoveRange(Set(Coordinate(0, -1)))

case object NearUpLeft extends MoveRange(Set(Coordinate(-1, 1)))

case object NearUpRight extends MoveRange(Set(Coordinate(1, 1)))

case object NearDownLeft extends MoveRange(Set(Coordinate(-1, -1)))

case object NearDownRight extends MoveRange(Set(Coordinate(1, -1)))

case object KeimaMoveRange extends MoveRange(Set(Coordinate(-1, 2), Coordinate(1, 2)))

sealed abstract class Piece(val move: List[Direction])

case object Ou extends Piece(List(Up(NearUp.range), Down(NearDown.range), Left(NearLeft.range), Right(NearRight.range), UpLeft(NearUpLeft.range), UpRight(NearUpRight.range), DownLeft(NearDownLeft.range), DownRight(NearDownRight.range)))

case object Kaku extends Piece(List(Up(CanNotMove.range), Down(CanNotMove.range), Left(CanNotMove.range), Right(CanNotMove.range), UpLeft(FarUpLeft.range), UpRight(FarUpRight.range), DownLeft(FarDownLeft.range), DownRight(FarDownRight.range)))

case object Keima extends Piece(List(KeimaDirection(KeimaMoveRange.range)))

class Direction(val moveRange: Set[Coordinate])

case class CanNotMove(moveRanges: Set[Coordinate]) extends Direction(moveRanges)

case class Up(moveRanges: Set[Coordinate]) extends Direction(moveRanges)

case class Down(moveRanges: Set[Coordinate]) extends Direction(moveRanges)

case class Left(moveRanges: Set[Coordinate]) extends Direction(moveRanges)

case class Right(moveRanges: Set[Coordinate]) extends Direction(moveRanges)

case class UpLeft(moveRanges: Set[Coordinate]) extends Direction(moveRanges)

case class UpRight(moveRanges: Set[Coordinate]) extends Direction(moveRanges)

case class DownLeft(moveRanges: Set[Coordinate]) extends Direction(moveRanges)

case class DownRight(moveRanges: Set[Coordinate]) extends Direction(moveRanges)

case class KeimaDirection(moveRanges: Set[Coordinate]) extends Direction(moveRanges)
