package models

/**
  * Created by taishi on 8/14/16.
  */
object Piece {
  val far = List(-8, -7, -6, -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8)
  val farMinus = List(-8, -7, -6, -5, -4, -3, -2, -1, 0)
  val farPlus = List(0, 1, 2, 3, 4, 5, 6, 7, 8)
  val canNotMove = Set(Coordinate(0, 0))
  val farDiagonal = far.zip(far).map(t => Coordinate(t._1, t._2))
  val farUp = farPlus.map(d => Coordinate(0, d)).toSet
  val farDown = farMinus.map(d => Coordinate(0, d)).toSet
  val farLeft = farMinus.map(d => Coordinate(d, 0)).toSet
  val farRight = farPlus.map(d => Coordinate(d, 0)).toSet
  val farUpLeft = (farMinus zip farPlus).map(c => Coordinate(c._1, c._2)).toSet
  val farUpRight = (farPlus zip farPlus).map(c => Coordinate(c._1, c._2)).toSet
  val farDownLeft = (farMinus zip farMinus).map(c => Coordinate(c._1, c._2)).toSet
  val farDownRight = (farPlus zip farMinus).map(c => Coordinate(c._1, c._2)).toSet
  val nearUp = Set(Coordinate(0, 1))
  val nearDown = Set(Coordinate(0, -1))
  val nearLeft = Set(Coordinate(-1, 0))
  val nearRight = Set(Coordinate(1, 0))
  val nearUpLeft = Set(Coordinate(-1, 1))
  val nearUpRight = Set(Coordinate(1, 1))
  val nearDownLeft = Set(Coordinate(-1, -1))
  val nearDownRight = Set(Coordinate(1, -1))
  val keimaCoordinate = Set(Coordinate(-1, 2), Coordinate(1, 2))

  (Up(canNotMove), Down(canNotMove), Left(canNotMove), Right(canNotMove), UpLeft(canNotMove), UpRight(canNotMove), DownLeft(canNotMove), DownRight(canNotMove))
  val ou = (Up(nearUp), Down(nearDown), Left(nearLeft), Right(nearRight), UpLeft(nearUpLeft), UpRight(nearUpRight), DownLeft(nearDownLeft), DownRight(nearDownRight))
  val hu = (Up(nearUp), Down(canNotMove), Left(canNotMove), Right(canNotMove), UpLeft(canNotMove), UpRight(canNotMove), DownLeft(canNotMove), DownRight(canNotMove))
  val kaku = (Up(canNotMove), Down(canNotMove), Left(canNotMove), Right(canNotMove), UpLeft(farUpLeft), UpRight(farUpRight), DownLeft(farDownLeft), DownRight(farDownRight))
  val hisya = (Up(farUp), Down(farDown), Left(farLeft), Right(farRight), UpLeft(canNotMove), UpRight(canNotMove), DownLeft(canNotMove), DownRight(canNotMove))
  val keima = keimaCoordinate
}

sealed abstract class Piece(val move: List[Direction])

case object Ou extends Piece(List(Up(Piece.nearUp), Down(Piece.nearDown), Left(Piece.nearLeft), Right(Piece.nearRight), UpLeft(Piece.nearUpLeft), UpRight(Piece.nearUpRight), DownLeft(Piece.nearDownLeft), DownRight(Piece.nearDownRight)))

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
