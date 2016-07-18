package models

import scala.collection.immutable.ListSet

/**
  * Created by taishi on 7/19/16.
  */

case class Coordinate(x: Int, y: Int)

case class OnBoardPiece(player: Player, piece: Piece, coordinate: Coordinate)

class Board(val pieces: Set[OnBoardPiece]) {

  def findPiece(coordinate: Coordinate): Option[Piece] = {
    pieces.filter(_.coordinate == coordinate) match {
      case c if c.size == 0 => None
      case c if c.size == 1 => Some(c.head.piece)
    }
  }

  def findMoveCoordinates(atPoint: Coordinate, movement: Movement): List[Coordinate] = {
    movement.productIterator.map(_.asInstanceOf[Coordinate]).filterNot(_ == Coordinate(0, 0)).map { c =>
      val x = atPoint.x - c.x
      val y = atPoint.y - c.y
      Coordinate(x, y)
    }.toList
  }

  def isNihu(atPoint: Coordinate, player: Player) = {
    val verticalLine: Set[Coordinate] = (1 to 9).map {
      Coordinate(atPoint.x, _)
    }.toSet
    val onBoardHuCoordinate:Set[Coordinate] = pieces.filter(_.player == player).filter {
      _.piece match {
        case Hu => true
        case _ => false
      }
    }.map(_.coordinate)
    (verticalLine.map(_.y) intersect onBoardHuCoordinate.map(_.y)).size != 0
  }

  def searchOpponentPieceOrFreeSpace(coordinate: Coordinate): Option[OnBoardPiece] = {
    pieces.filter(_.coordinate == coordinate).headOption
  }
}
