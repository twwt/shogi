package models

/**
  * Created by taishi on 7/19/16.
  */
case class MoveRange(up: Coordinate, down: Coordinate, left: Coordinate, right: Coordinate, upLeft: Coordinate, upRight: Coordinate, downLeft: Coordinate, downRight: Coordinate)



sealed abstract class Piece(val movement: MoveRange)

case object Ou extends Piece(MoveRange(Coordinate(0, 1), Coordinate(0, -1), Coordinate(-1, 0), Coordinate(1, 0), Coordinate(-1, 1), Coordinate(1, 1), Coordinate(-1, -1), Coordinate(1, -1)))

case object Hu extends Piece(MoveRange(Coordinate(0, 1), Coordinate(0, 0), Coordinate(0, 0), Coordinate(0, 0), Coordinate(0, 0), Coordinate(0, 0), Coordinate(0, 0), Coordinate(0, 0)))

case object Kaku extends Piece(MoveRange(Coordinate(0, 0), Coordinate(0, 0), Coordinate(0, 0), Coordinate(0, 0), Coordinate(-8, 8), Coordinate(8, 8), Coordinate(-8, -8), Coordinate(8, -8)))


//
//
//case class MoveRange(up: Up, down: Down, left: Left, right: Right, upLeft: UpLeft, upRight: UpRight, downLeft: DownLeft, downRight: DownRight)


//
//case class Up(x: Int, y: Int)
//
//case class Down(x: Int, y: Int)
//
//case class Left(x: Int, y: Int)
//
//case class Right(x: Int, y: Int)
//
//case class UpLeft(x: Int, y: Int)
//
//case class UpRight(x: Int, y: Int)
//
//case class DownLeft(x: Int, y: Int)
//
//case class DownRight(x: Int, y: Int)
//
//sealed abstract class Piece(val movement: MoveRange)
//
//case object Ou extends Piece(MoveRange(Up(0, 1), Down(0, -1), Left(-1, 0), Right(1, 0), UpLeft(-1, 1), UpRight(1, 1), DownLeft(-1, -1), DownRight(1, -1)))
//
//case object Hu extends Piece(MoveRange(Up(0, 1), Down(0, 0), Left(0, 0), Right(0, 0), UpLeft(0, 0), UpRight(0, 0), DownLeft(0, 0), DownRight(0, 0)))
//
//case object Kaku extends Piece(MoveRange(Up(0, 0), Down(0, 0), Left(0, 0), Right(0, 0), UpLeft(-8, 8), UpRight(8, 8), DownLeft(-8, -8), DownRight(8, -8)))
//
