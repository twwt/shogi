package models

/**
  * Created by taishi on 8/14/16.
  */

abstract class Player(newPieceInHand: List[Piece])

case class PieceInHand(pieceInHand: List[Piece])(player: Player) {
  def exists(piece: Piece): Boolean = {
    pieceInHand.contains(piece)
  }
}

case class Black(pieceInHand: List[Piece]) extends Player(pieceInHand)

case class White(pieceInHand: List[Piece]) extends Player(pieceInHand)
