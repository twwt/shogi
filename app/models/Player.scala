package models

import scalaz._
import Scalaz._

/**
  * Created by taishi on 8/14/16.
  */

class Player(newPieceInHand: List[Piece]) {
  def movePiece(board: Board, beforeMoveCoordinate: Coordinate, afterMoveCoordinate: Coordinate) = {
    val space: Space = board.findSpace(afterMoveCoordinate)
  }

  def canMoveRange(beforeMoveCoordinate: Coordinate)(board: Board, piece: Piece): List[Direction] = {
    val distanceSort = this.distanceSort(beforeMoveCoordinate)(_)
    val toDirection = Coordinate.toDirection(beforeMoveCoordinate)(piece)(_)
    val canMoveRangeIndex = this.canMoveRangeIndex(board)(_)
    piece match {
      case Keima =>
        this.mostMoveRange(beforeMoveCoordinate)(piece)
          .map(distanceSort)
          .map(c => toDirection(c.toSet))
      case _ =>
        this.mostMoveRange(beforeMoveCoordinate)(piece)
          .map(distanceSort)
          .map(canMoveRangeIndex).flatten
          .map(c => toDirection(c.toSet))
    }
  }

  def mostMoveRange(beforeMoveCoordinate: Coordinate)(piece: Piece): List[Direction] = {
    for {
      direction <- piece.move
      movedCoordinate = direction.moveRange.map(_ + beforeMoveCoordinate)
        .filter(c => c.x < 5 && c.y < 5 && -5 < c.x && -5 < c.y)
      toDirectionMoveCoordinate = Coordinate.toDirection(beforeMoveCoordinate)(piece)(movedCoordinate)
    } yield {
      toDirectionMoveCoordinate
    }
  }


  def distanceSort(beforeMoveCoordinate: Coordinate)(direction: Direction): List[Coordinate] = {
    direction.moveRange.toList.filter(_ != beforeMoveCoordinate).sortBy(c => c.x.abs + c.y.abs + beforeMoveCoordinate.x.abs + beforeMoveCoordinate.y.abs)
  }

  def canMoveRangeIndex(board: Board)(sortedCoordinates: List[Coordinate]): Option[List[Coordinate]] = {
    (sortedCoordinates
      .map(board.findSpace)
      .map(board.findPiece)
      .indexWhere(_.isDefined) match {
      case i if sortedCoordinates.length == 0 => None
      case i if 0 <= i =>
        board.findSpace(sortedCoordinates(i)) match {
          case s: PieceSpace if s.checkOwnerPlayer(this) => (i - 1).some
          case s: PieceSpace if !s.checkOwnerPlayer(this) => i.some
        }
      case -1 => (sortedCoordinates.length - 1).some
    }).map(sortedCoordinates.take(_))
  }
}

case class PieceInHand(pieceInHand: List[Piece])(player: Player) {
  def exists(piece: Piece): Boolean = {
    pieceInHand.contains(piece)
  }
}

case class Black(pieceInHand: List[Piece]) extends Player(pieceInHand)

case class White(pieceInHand: List[Piece]) extends Player(pieceInHand)
