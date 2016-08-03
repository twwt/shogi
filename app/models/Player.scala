package models

import models.Player.PieceInHand

/**
  * Created by taishi on 7/19/16.
  */

sealed trait Player {
  val pieceInHand: PieceInHand

//  def reversePlayer: Player

  def exists(piece: Piece): Boolean = {
    pieceInHand(this).contains(piece)
  }
}

object Player {
  type PieceInHand = Map[Player, List[Piece]]
}

final class Black(newPieceInHand: PieceInHand) extends Player {
  val pieceInHand = newPieceInHand
//  def reversePlayer = new White
}

final class White(newPieceInHand: PieceInHand) extends Player {
  val pieceInHand = newPieceInHand

//  def reversePlayer = new Black
}