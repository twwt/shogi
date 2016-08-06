package models

import models.Board.BoardState
import models.Board.Space


/**
  * Created by taishi on 7/19/16.
  */

sealed class Player(newPieceInHand: List[Piece]) {
  val pieceInHand: PieceInHand = genPieceInHand(newPieceInHand)

  def movePiece(beforeCoordinate: Coordinate)(afterCoordinate: Coordinate)(piece: Piece) = {

  }

  def genPieceInHand(pieceInHand: List[Piece]) = {
    new PieceInHand(newPieceInHand)(this)
  }

  def addPiece(piece: Piece)(addPieceCoordinate: Coordinate)(boardState: BoardState): Game = {
    val newPieceInHand = subtractPieceInHand(piece)
    val changeAfterSpace: Space = Some(Map(this -> piece))
    val changeAfterSpaces: BoardState =
      boardState.map { xSpaces =>
        if (xSpaces._1 == addPieceCoordinate.x) {
          (xSpaces._1 -> Board.exchange(xSpaces._2)(addPieceCoordinate.y)(changeAfterSpace))
        } else xSpaces
      }
    Game(this, Board(changeAfterSpaces), newPieceInHand)
  }

  def subtractPieceInHand(piece: Piece): PieceInHand = {
    val removePieceInHand = pieceInHand.pieceInHand diff List(piece)
    PieceInHand(removePieceInHand)(this)
  }

  //  def reversePlayer: Player
}

case class PieceInHand(pieceInHand: List[Piece])(player: Player) {
  def exists(piece: Piece): Boolean = {
    pieceInHand.contains(piece)
  }
}

case class Black(newPieceInHand: List[Piece]) extends Player(newPieceInHand)

case class White(newPieceInHand: List[Piece]) extends Player(newPieceInHand)