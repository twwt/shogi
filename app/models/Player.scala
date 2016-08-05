package models

import models.Board.BoardState
import models.Board.Space
import shapeless._
import ops.nat.Diff._


/**
  * Created by taishi on 7/19/16.
  */

sealed class Player(newPieceInHand: List[Piece]) {
  val pieceInHand: PieceInHand = genPieceInHand(newPieceInHand)

  def genPieceInHand(pieceInHand: List[Piece]) = {
    new PieceInHand(newPieceInHand)(this)
  }

  def addPiece(piece: Piece)(addPieceCoordinate: Coordinate)(boardState: BoardState): Board = {
    val changeAfterSpace: Space = Some(Map(this -> piece))
    val changeBeforeSpace: Space = boardState(addPieceCoordinate.x)(addPieceCoordinate.y)
    val changeAfterSpaces: BoardState =
      boardState.map { xSpaces =>
        if (xSpaces._1 == addPieceCoordinate.x) {
          (xSpaces._1 -> exchange(xSpaces._2)(addPieceCoordinate.y)(changeAfterSpace))
        } else xSpaces
      }
    Board(changeAfterSpaces)
  }

  def exchange[T](l: List[T])(n: Int)(exchange: T): List[T] = {
    l.zipWithIndex.map { a =>
      if (a._2 == n) exchange else a._1
    }
  }

  def removePieceInHand(piece: Piece): PieceInHand = {
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