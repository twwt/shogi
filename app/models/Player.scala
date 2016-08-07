package models

import models.Board.BoardState
import models.Board.Space


/**
  * Created by taishi on 7/19/16.
  */

sealed class Player(newPieceInHand: List[Piece]) {
  type -->[A, B] = PartialFunction[A, B]

  val pieceInHand: PieceInHand = genPieceInHand(newPieceInHand)

  def movePiece(board: Board)(beforeCoordinate: Coordinate)(afterCoordinate: Coordinate)(piece: Piece): Option[Game] = {
    val canMoveRange: List[Space] = board.canMoveRange(this, beforeCoordinate, piece)
    println(canMoveRange)
    println(afterCoordinate)
    if (canMoveRange.contains(afterCoordinate)) {
      val newBoard = new Board(board.changeBoard(afterCoordinate)(Some(Map(this -> piece))))
      Some(new Game(this, newBoard, pieceInHand))
    } else None
  }

  val pf: Space --> Boolean = {
    case Some(s) if s.contains(this) => false
    case Some(s) if !s.contains(this) => true
    case None => true
  }

  def genPieceInHand(pieceInHand: List[Piece]) = {
    new PieceInHand(newPieceInHand)(this)
  }

  def addPiece(piece: Piece)(addPieceCoordinate: Coordinate)(boardState: BoardState): Game = {
    val newPieceInHand = subtractPieceInHand(piece)
    val changeAfterSpace: Space = Some(Map(this -> piece))
    val changeAfterSpaces: BoardState = boardState.mapValues(_.map {
      case (yIndex, ySpace) if yIndex == addPieceCoordinate.y => (yIndex -> changeAfterSpace)
      case (yIndex, ySpace) => (yIndex -> ySpace)
    })
    Game(this, Board(changeAfterSpaces), newPieceInHand)
  }

  def subtractPieceInHand(piece: Piece): PieceInHand = {
    val removePieceInHand = pieceInHand.pieceInHand diff List(piece)
    PieceInHand(removePieceInHand)(this)
  }

  val reversePlayer: Player = this
}

case class PieceInHand(pieceInHand: List[Piece])(player: Player) {
  def exists(piece: Piece): Boolean = {
    pieceInHand.contains(piece)
  }
}

case class Black(newPieceInHand: List[Piece]) extends Player(newPieceInHand)

case class White(newPieceInHand: List[Piece]) extends Player(newPieceInHand)
