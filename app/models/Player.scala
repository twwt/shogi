package models

/**
  * Created by taishi on 7/19/16.
  */

sealed trait Player {
//  def movePiece(game: Game, movePieceCoodinate: Coordinate, moveCoordinate: Coordinate): Unit = {
//    val piece: Option[Piece] = game.board.findPiece(coordinate)
//
//  }
}

final class Black extends Player

final class White extends Player
