package models

import scalaz._
import Scalaz._

/**
  * Created by taishi on 7/19/16.
  */

sealed trait Player {
  def movePiece(game: Game, movePieceCoodinate: Coordinate, afterMoveCoordinate: Coordinate): Option[Game] = {
    val board = game.board
    val selfPlayer: Player = game.player
    val canMovePiece: Option[Boolean] =
      board.findPiece(movePieceCoodinate)
        .map(board.findMoveRange(afterMoveCoordinate, _, selfPlayer))
        .map(_.contains(afterMoveCoordinate))
    canMovePiece match {
      //        SomeはPieceがBoardに存在するということ。Booleanは動けるのか動けないのか。
      //        敵
      case Some(piece) if piece == true => takePiece(afterMoveCoordinate, game)
      //        味方
      case Some(piece) if piece == false => None
      //        何も置いてない
      case None => takePiece(afterMoveCoordinate, game)
    }
  }

  //todo privateをつける
  def takePiece(takePieceCoordinate: Coordinate, game: Game): Option[Game] = {
    val board = game.board
    //    boardから駒を消して、持ち駒に消した駒を追加
    val removePiece: Option[OnBoardPiece] = board.pieces.filter(_.coordinate == takePieceCoordinate).headOption
    val newOnBoardPiece: Option[Set[OnBoardPiece]] = removePiece.map(board.pieces - _)
    val newPieceInHand: Option[Map[Player, Piece]] = removePiece.map(p => game.pieceInHand.pieces + (game.player -> p.piece))
    if (newOnBoardPiece.isDefined && newPieceInHand.isDefined) {
      val nextPlayer: Player = game.player match {
        case _: Black => new White
        case _: White => new Black
      }
      Some(new Game(nextPlayer, new Board(newOnBoardPiece.get), PieceInHand(newPieceInHand.get)))
    } else {
      None
    }
  }
}

final class Black extends Player

final class White extends Player
