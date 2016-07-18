package models

/**
  * Created by taishi on 7/19/16.
  */
case class Movement(up: Int, down: Int, left: Int, right: Int, upLeft: Int, upRight: Int, downLeft: Int, downRight: Int)

sealed abstract class Piece(val movement: Movement)

case object Ou extends Piece(Movement(1, 1, 1, 1, 1, 1, 1, 1))

