package models

import scalaz._
import Scalaz._

/**
  * Created by taishi on 7/19/16.
  */

sealed trait Player {
  def movePiece(game: Game, movePieceCoodinate: Coordinate, afterMoveCoordinate: Coordinate): Boolean = {
    val board = game.board
    val selfPlayer = game.player
    val canMovePiece: Option[Boolean] =
      board.findPiece(movePieceCoodinate)
        .map(board.findMoveRange(afterMoveCoordinate, _, selfPlayer))
        .map(_.contains(afterMoveCoordinate))
    val result: Boolean = canMovePiece match {
//        SomeはPieceがBoardに存在するということ。Booleanは動けるのか動けないのか。
//        敵
      case Some(piece) if piece == true => true
//        味方
      case Some(piece) if piece == false => false
//        何も置いてない
      case None => true
    }
  }
}

final class Black extends Player

final class White extends Player
