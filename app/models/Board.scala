package models

import scala.collection.immutable.IndexedSeq

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

  def findMoveCoordinates(atPoint: Coordinate, piece: Piece, player: Player): List[Coordinate] = {
    piece.movement.productIterator.map(_.asInstanceOf[Coordinate]).flatMap { c =>
      c match {
        case coordinate if coordinate.x == 0 && coordinate.y == 0 => List(atPoint)
        case coordinate if abs(c.x) == abs(c.y) => calcMovingDistance(coordinate, atPoint)
        case coordinate if abs(c.x) != abs(c.y) => calcMovingDistance(coordinate, atPoint)
      }
    }.toList.distinct.filter(_ != atPoint).filter(c => c.x <= 9 && 1 <= c.x).filter(c => c.y <= 9 && 1 <= c.y)
  }

  def calcMovingDistance(coordinate: Coordinate, atPoint: Coordinate): List[Coordinate] = {
    val resultCoordinate = Coordinate(coordinate.x + atPoint.x, coordinate.y + atPoint.y)
    println(resultCoordinate)
    val xRange: List[Int] = if (0 < resultCoordinate.x) (atPoint.x to resultCoordinate.x).toList else (resultCoordinate.x to atPoint.x).toList.reverse
    val yRange: List[Int] = if (0 < resultCoordinate.y) (atPoint.y to resultCoordinate.y).toList else (resultCoordinate.y to atPoint.y).toList.reverse
    xRange.zip(yRange).map(c => Coordinate(c._1, c._2))
  }

  def abs(n: Int): Int = if (n < 0) -n else n

  def isNihu(atPoint: Coordinate, player: Player) = {
    val verticalLine: Set[Coordinate] = (1 to 9).map {
      Coordinate(atPoint.x, _)
    }.toSet
    val onBoardHuCoordinate: Set[Coordinate] = pieces.filter(_.player == player).filter {
      _.piece match {
        case Hu => true
        case _ => false
      }
    }.map(_.coordinate)
    (verticalLine.map(_.y) intersect onBoardHuCoordinate.map(_.y)).size != 0
  }

  def searchOpponentPieceOrFreeSpace(player: Player, atPoint: Coordinate): Boolean = {
    pieces.filter(_.player != player).filter(_.coordinate == atPoint).headOption.isDefined
  }
}
