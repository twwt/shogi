package models

import scalaz._
import Scalaz._

/**
  * Created by taishi on 8/14/16.
  */

case class MoveRangeCoordinate(player: Option[Player], moveRange: List[Coordinate])

class Player(newPieceInHand: List[Piece]) {

  def movePiece(board: Board, beforeMoveCoordinate: Coordinate, afterMoveCoordinate: Coordinate): Boolean = {
    val moveRangeF = canMoveRange(beforeMoveCoordinate)(board)(_)
    board.state.board(afterMoveCoordinate.x).x(afterMoveCoordinate.y)
    board.findSpace(beforeMoveCoordinate).piece
      .map(moveRangeF).map(_.map(_.moveRange.contains(afterMoveCoordinate)).contains(true)) match {
      case Some(b) if b => true
      case _ => false
    }
  }

  def addPieceInHand(piece: Piece): Player = new Player(piece :: newPieceInHand)

  def canMoveRange(beforeMoveCoordinate: Coordinate)(board: Board)(piece: Piece): List[Direction] = {
    val distanceSort = this.distanceSort(beforeMoveCoordinate)(_)
    val toDirection = Coordinate.toDirection(beforeMoveCoordinate)(piece)(_)
    val canMoveRangeIndexF = this.moveRangeIndex(board)(_)
    this.mostFarMoveRange(beforeMoveCoordinate)(piece)
      .map(distanceSort)
      .map(canMoveRangeIndexF).flatMap(_.map(c => toDirection(c.toSet)))
    //
    //    piece match {
    //      case Keima =>
    //        sortMoveRange.map(canMoveRangeIndex).flatten
    //          .map(c => toDirection(c.toSet))
    //      case _ =>
    //        sortMoveRange.map(canMoveRangeIndex).flatten
    //          .map(c => toDirection(c.toSet))
    //    }
  }

  def mostFarMoveRange(beforeMoveCoordinate: Coordinate)(piece: Piece): List[Direction] = {
    for {
      direction <- piece.move
      movedCoordinate = direction.moveRange.map(_ + beforeMoveCoordinate)
        .filter(c => c.x < 5 && c.y < 5 && -5 < c.x && -5 < c.y)
        .filterNot(c => c.x == beforeMoveCoordinate.x && c.y == beforeMoveCoordinate.y)
    } yield {
      Coordinate.toDirection(beforeMoveCoordinate)(piece)(movedCoordinate)
    }
  }


  def distanceSort(beforeMoveCoordinate: Coordinate)(direction: Direction): List[Coordinate] =
    direction.moveRange.toList.sortBy(c => c.x.abs + c.y.abs + beforeMoveCoordinate.x.abs + beforeMoveCoordinate.y.abs)

  def moveRangeIndex(board: Board)(sortedCoordinates: List[Coordinate]): Option[List[Coordinate]] = {
    val pf: PartialFunction[Int, Int] = {
      case i if sortedCoordinates.length == 0 => 0
      case i if 0 <= i =>
        val piece: Option[Piece] = board.findSpace(sortedCoordinates(i)).piece
        if (new PieceSpace(piece, this.some).checkOwnerPlayer(this)) i - 1 else i
      case -1 => sortedCoordinates.length + 1
    }
    val moveRangeIndex = pf(coordinatesToPieces(sortedCoordinates)(board)
      .indexWhere(_.isDefined))
    sortedCoordinates.take(moveRangeIndex) match {
      case l if l.isEmpty => None
      case l if !l.isEmpty => l.some
    }
  }


  def coordinatesToPieces(coordinates: List[Coordinate])(board: Board): List[Option[Piece]] =
    coordinates.map(board.findSpace).map(_.piece)

}

case class PieceInHand(pieceInHand: List[Piece])(player: Player) {
  def exists(piece: Piece): Boolean = {
    pieceInHand.contains(piece)
  }
}

case class Black(pieceInHand: List[Piece]) extends Player(pieceInHand) {
  val reversePlayer = White
}

case class White(pieceInHand: List[Piece]) extends Player(pieceInHand) {
  val reversePlayer = Black
}
