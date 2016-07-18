package models

import scala.collection.immutable.ListSet

/**
  * Created by taishi on 7/19/16.
  */

case class Coordinate(x: Int, y: Int)

case class OnBoardPiece(player: Player, piece: Piece, coordinate: Coordinate)

class Board(val pieces: ListSet[OnBoardPiece]) {

  def findPiece(coordinate: Coordinate): Option[Piece] = {
    pieces.filter(_.coordinate == coordinate) match {
      case c if c.size == 0 => None
      case c if c.size == 1 => Some(c.head.piece)
    }
  }

  def findMoveCoordinates(atPoint: Coordinate, movement: Movement): List[Coordinate] = {
    movement.productIterator.map(_.asInstanceOf[Coordinate]).map { c =>
      val x = atPoint.x - c.x
      val y = atPoint.y - c.y
      Coordinate(x, y)
    }.toList
  }
}
