package models

/**
  * Created by taishi on 8/14/16.
  */

class Player(newPieceInHand: List[Piece]) {
  def movePiece(board: Board, beforeMoveCoordinate: Coordinate, afterMoveCoordinate: Coordinate) = {
    val space: Space = board.findSpace(afterMoveCoordinate)
  }

  def mostMoveRange(piece: Piece, beforeMoveCoordinate: Coordinate): Unit = {
    val s: List[Direction] = piece.move.map(_.moveRange.map(_ + beforeMoveCoordinate)).map(c => Coordinate.toDirection(beforeMoveCoordinate, c, piece))
    println("mostMoveRange")
    println(s)
  }
}

case class PieceInHand(pieceInHand: List[Piece])(player: Player) {
  def exists(piece: Piece): Boolean = {
    pieceInHand.contains(piece)
  }
}

case class Black(pieceInHand: List[Piece]) extends Player(pieceInHand)

case class White(pieceInHand: List[Piece]) extends Player(pieceInHand)
