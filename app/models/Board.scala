package models

import scala.util.Try
import scalaz._
import Scalaz._
import monocle.macros.{GenLens, Lenses}
import monocle.function.At.at
import monocle.std.map._
import monocle.std.string._
import monocle.function.all._
import monocle.std.list._
import monocle.std.map._
import monocle.Traversal

/**
  * Created by taishi on 8/14/16.
  */

sealed case class Space(val piece: Option[Piece], owner: Option[Player])

class PieceSpace(p: Option[Piece], ownerPlayer: Option[Player]) extends Space(p, ownerPlayer) {
  def checkOwnerPlayer(player: Player): Boolean = player == ownerPlayer
}

class FreeSpace(p: Option[Piece], ownerPlayer: Option[Player] = None) extends Space(p, ownerPlayer)

case class X(x: Map[Int, Space])

@Lenses case class BoardState(board: Map[Int, X])

case class Board(state: BoardState) {
  def exchange(beforeMoveCoordinate: Coordinate, afterMoveCoordinate: Coordinate, piece: Piece, turnPlayer: Player, game: Game): Game = {
    val beforeMoveSpace = state.board(beforeMoveCoordinate.x).x(beforeMoveCoordinate.y)
    beforeMoveSpace.copy(piece = None)
    val afterMoveSpace = state.board(afterMoveCoordinate.x).x(afterMoveCoordinate.y)
    val newTurnPlayer = afterMoveSpace.owner match {
      case Some(player) if turnPlayer != player => turnPlayer.addPieceInHand(afterMoveSpace.piece.get)
      case Some(player) if turnPlayer == player => player
      case _ => turnPlayer
    }
    val board = Board(BoardState(state.board.flatMap { y =>
      Map(y._1 -> X(y._2.x.map {
        case (index, space) if y._1 == afterMoveCoordinate.y && index == afterMoveCoordinate.x => (index, Space(Some(piece), turnPlayer.some))
        case (index, space) => (index, space)
      }))
    }))
    game.copy(boardState = board, turnPlayer = newTurnPlayer)
  }

  def findSpace(coordinate: Coordinate): Space = state.board(coordinate.x).x(coordinate.y)

  def findPiece(space: Space) = space.piece
}

case class Coordinate(x: Int, y: Int) {
  def +(coordinate: Coordinate): Coordinate = Coordinate(x + coordinate.x, y + coordinate.y)

  def -(coordinate: Coordinate) = Coordinate(x - coordinate.x, y - coordinate.y)
}

object Coordinate {
  def toDirection(beforeMoveCoordinate: Coordinate)(piece: Piece)(afterMoveCoordinates: Set[Coordinate]): Direction = {
    Try(afterMoveCoordinates.head - beforeMoveCoordinate match {
      case c if c == Coordinate(0, 0) => CanNotMove(Set())
      case p if piece == Keima => KeimaDirection(afterMoveCoordinates)
      case c if c.x == 0 && 1 <= c.y => Up(afterMoveCoordinates)
      case c if c.x == 0 && c.y <= 0 => Down(afterMoveCoordinates)
      case c if c.x <= -1 && c.y == 0 => Left(afterMoveCoordinates)
      case c if 1 <= c.x && 0 < c.x && c.y == 0 => Right(afterMoveCoordinates)
      case c if c.x <= -1 && 1 <= c.y => UpLeft(afterMoveCoordinates)
      case c if 1 <= c.x && 1 <= c.y => UpRight(afterMoveCoordinates)
      case c if c.x <= -1 && c.y <= 0 => DownLeft(afterMoveCoordinates)
      case c if 1 <= c.x && 0 < c.x && c.y <= 0 => DownRight(afterMoveCoordinates)
    }).getOrElse(CanNotMove(Set()))
  }
}