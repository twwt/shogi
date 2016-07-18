package models

/**
  * Created by taishi on 7/19/16.
  */
case class Movement(up: Coordinate, down: Coordinate, left: Coordinate, right: Coordinate, upLeft: Coordinate, upRight: Coordinate, downLeft: Coordinate, downRight: Coordinate)

sealed abstract class Piece(val movement: Movement)

case object Ou extends Piece(Movement(Coordinate(0, 1), Coordinate(0, -1), Coordinate(-1, 0), Coordinate(1, 0), Coordinate(-1, 1), Coordinate(1, 1), Coordinate(-1, -1), Coordinate(1, -1)))

case object Hu extends Piece(Movement(Coordinate(0, 1), Coordinate(0, 0), Coordinate(0, 0), Coordinate(0, 0), Coordinate(0, 0), Coordinate(0, 0), Coordinate(0, 0), Coordinate(0, 0)))

