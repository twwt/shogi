package models

/**
  * Created by taishi on 7/19/16.
  */

sealed class Player(newPieceInHand: List[Piece]) {
  val pieceInHand: PieceInHand = genPieceInHand(newPieceInHand)

  def genPieceInHand(pieceInHand: List[Piece]) = {
    new PieceInHand(newPieceInHand)(this)
  }

  //  def reversePlayer: Player
}

case class PieceInHand(pieceInHand: List[Piece])(player: Player) {
  def exists(piece: Piece): Boolean = {
    pieceInHand.contains(piece)
  }
}

case class Black(newPieceInHand:List[Piece]) extends Player(newPieceInHand)

case class White(newPieceInHand:List[Piece]) extends Player(newPieceInHand)