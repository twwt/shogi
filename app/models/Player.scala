package models

import shapeless._
import shapeless.Sized
import scala.language.higherKinds
import syntax.sized._
import scalaz._
import Scalaz._

/**
  * Created by taishi on 7/19/16.
  */

sealed trait Player {

  def reversePlayer: Player

//  def movePiece(game: Game, movePieceCoodinate: Coordinate, afterMoveCoordinate: Coordinate): Option[Game] = {
//    val board = game.board
//    val selfPlayer: Player = game.player
//    val canMovePiece: Option[Boolean] =
//      board.findSpace(movePieceCoodinate)(game.board.boardState)
//        .map(p => board.findMoveRange(afterMoveCoordinate, p(selfPlayer), selfPlayer))
//        .map(_.contains(afterMoveCoordinate))
//    canMovePiece match {
//      //        SomeはPieceがBoardに存在するということ。Booleanは動けるのか動けないのか。
//      //        敵
//      case Some(piece) if piece == true => takePiece(movePieceCoodinate, afterMoveCoordinate, game)
//      //        味方
//      case Some(piece) if piece == false => None
//      //        何も置いてない
//      case None => takePiece(movePieceCoodinate, afterMoveCoordinate, game)
//    }
//  }
//
//  def takePiece(movePieceCoordinate: Coordinate, takePieceCoordinate: Coordinate, game: Game): Option[Game] = {
//    val board: Board = game.board
//    implicit val boardState: Sized[List[Sized[List[Option[Map[Player, Piece]]], nat._9]], nat._9] = game.board.boardState
//    val myTurnPlayer = game.player
//    val movePieceOfSpace: Option[Map[Player, Piece]] = board.findSpace(movePieceCoordinate)
//    //    boardから駒を消して駒を動かし、持ち駒に消した駒を追加
//    val removePieceOfSpace: Option[Map[Player, Piece]] = board.takeAtPoint(takePieceCoordinate.x, takePieceCoordinate.y)
//    val newBoardState: Sized[List[Sized[List[Option[Map[Player, Piece]]], shapeless.nat._9]], shapeless.nat._9] =
//      boardState.map(s => s.map {
//        piece => if (removePieceOfSpace == piece) movePieceOfSpace else piece
//      })
//    removePieceOfSpace match {
//      case Some(removePiece) =>
//        val newPieceInHand: PieceInHand = PieceInHand(game.pieceInHand.pieces + (myTurnPlayer -> removePiece(myTurnPlayer.reversePlayer)))
//        Some(new Game(game.player.reversePlayer, new Board(newBoardState), newPieceInHand))
//      case None => None
//    }
//  }
}

final class Black extends Player {
  def reversePlayer = new White
}

final class White extends Player {
  def reversePlayer = new Black
}