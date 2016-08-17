package models

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
    this.mostMoveRange(beforeMoveCoordinate)(piece)
      .map(distanceSort)
      .map(c => toDirection(Set(this.canMoveRangeIndex(board, c))))
  }

  private def mostMoveRange(beforeMoveCoordinate: Coordinate)(piece: Piece): List[Direction] = {
    for {
      direction <- piece.move
      movedCoordinate = direction.moveRange.map(_ + beforeMoveCoordinate)
        .filter(c => c.x < 5 && c.y < 5 && -5 < c.x && -5 < c.y)
      toDirectionMoveCoordinate = Coordinate.toDirection(beforeMoveCoordinate)(piece)(movedCoordinate)
    } yield {
      toDirectionMoveCoordinate
    }
  }


  private def distanceSort(beforeMoveCoordinate: Coordinate)(direction: Direction): List[Coordinate] = {
    direction.moveRange.toList.filter(_ != beforeMoveCoordinate).sortBy(c => c.x.abs + c.y.abs + beforeMoveCoordinate.x.abs + beforeMoveCoordinate.y.abs)
  }

  private def canMoveRangeIndex(board: Board, sortedCoordinates: List[Coordinate]): Coordinate = {
    val i = sortedCoordinates.map(board.findSpace).map(board.findPiece).indexWhere(_.isDefined)
    val index: Int = board.findSpace(sortedCoordinates(i)) match {
      case s: PieceSpace if s.checkOwnerPlayer(this) => i - 1
      case s: PieceSpace if !s.checkOwnerPlayer(this) => i
    }
    sortedCoordinates(index)
  }
}

case class PieceInHand(pieceInHand: List[Piece])(player: Player) {
  def exists(piece: Piece): Boolean = {
    pieceInHand.contains(piece)
  }
}

case class Black(pieceInHand: List[Piece]) extends Player(pieceInHand)

case class White(pieceInHand: List[Piece]) extends Player(pieceInHand)
