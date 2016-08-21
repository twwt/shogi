package models

import scalaz._
import Scalaz._

/**
  * Created by taishi on 8/14/16.
  */

class Player(newPieceInHand: List[Piece]) {
  def movePiece(board: Board, beforeMoveCoordinate: Coordinate, afterMoveCoordinate: Coordinate): Boolean = {
    val moveRange = canMoveRange(beforeMoveCoordinate)(board)(_)
    board.findSpace(beforeMoveCoordinate).piece
      .map(moveRange).map(_.map(_.moveRange.contains(afterMoveCoordinate)))
    match {
      case Some(b) => b.contains(true)
      case None => false
    }
  }

  def canMoveRange(beforeMoveCoordinate: Coordinate)(board: Board)(piece: Piece): List[Direction] = {
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
    val pf: PartialFunction[Int, Option[Int]] = {
      case i if sortedCoordinates.length == 0 => None
      case i if 0 <= i =>
        val pieceSpace: PieceSpace = new PieceSpace(board.findSpace(sortedCoordinates(i)).piece, this)
        if (pieceSpace.checkOwnerPlayer(this)) (i - 1).some else i.some
      case -1 => (sortedCoordinates.length - 1).some
    }
    (pf(coordinatesToPieces(sortedCoordinates)(board)
      .indexWhere(_.isDefined))).map(i => sortedCoordinates.take(i + 1))
  }


  def coordinatesToPieces(coordinates: List[Coordinate])(board: Board): List[Option[Piece]] =
    coordinates.map(board.findSpace).map(_.piece)
}

case class PieceInHand(pieceInHand: List[Piece])(player: Player) {
  def exists(piece: Piece): Boolean = {
    pieceInHand.contains(piece)
  }
}

case class Black(pieceInHand: List[Piece]) extends Player(pieceInHand)

case class White(pieceInHand: List[Piece]) extends Player(pieceInHand)
