package models

/**
  * Created by taishi on 8/14/16.
  */

class Player(newPieceInHand: List[Piece]) {
  def movePiece(board: Board, beforeMoveCoordinate: Coordinate, afterMoveCoordinate: Coordinate) = {
    val space: Space = board.findSpace(afterMoveCoordinate)
  }

  def mostMoveRange(beforeMoveCoordinate: Coordinate)(piece: Piece): List[Direction] = {
    piece.move.map(_.moveRange.map(_ + beforeMoveCoordinate)).map(c => Coordinate.toDirection(beforeMoveCoordinate, c, piece))
  }

  def distanceSort(direction: Direction)(beforeMoveCoordinate: Coordinate): List[Coordinate] = {
    direction.moveRange.toList.sortBy(c => c.x.abs + c.y.abs + beforeMoveCoordinate.x.abs + beforeMoveCoordinate.y.abs)
    //    direction match {
    //      case _: CanNotMove => CanNotMove(d)
    //      case _: Up => Up(d)
    //      case _: Down => Down(d)
    //      case _: Left => Left(d)
    //      case _: Right => Right(d)
    //      case _: UpLeft => UpLeft(d)
    //      case _: UpRight => UpRight(d)
    //      case _: DownLeft => DownLeft(d)
    //      case _: DownRight => DownRight(d)
    //      case _: KeimaDirection => KeimaDirection(d)
    //    }
  }
}

case class PieceInHand(pieceInHand: List[Piece])(player: Player) {
  def exists(piece: Piece): Boolean = {
    pieceInHand.contains(piece)
  }
}

case class Black(pieceInHand: List[Piece]) extends Player(pieceInHand)

case class White(pieceInHand: List[Piece]) extends Player(pieceInHand)
