package models

import scala.util.Try

/**
  * Created by taishi on 8/14/16.
  */

sealed case class Space(val piece: Option[Piece])

class PieceSpace(p: Option[Piece], ownerPlayer: Player) extends Space(p) {
  def checkOwnerPlayer(player: Player): Boolean = {
    player == ownerPlayer
  }
}

class FreeSpace(p: Option[Piece]) extends Space(p)

case class X(space: Map[Int, Space])

case class Board(state: Map[Int, X]) {
  def exchange(coordinate: Coordinate, piece: Piece): Space =
    state(coordinate.x).space(coordinate.y).copy(Some(piece))

  def findSpace(coordinate: Coordinate): Space =
    state(coordinate.x).space(coordinate.y)

  def findPiece(space: Space) = space.piece
}

case class Coordinate(x: Int, y: Int) {
  def +(coordinate: Coordinate): Coordinate = Coordinate(x + coordinate.x, y + coordinate.y)

  def -(coordinate: Coordinate) = Coordinate(x - coordinate.x, y - coordinate.y)
}

object Coordinate {
  //afterMoveCoordinatesはただしい値がはいっていないとだめ
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