package models

/**
  * Created by taishi on 8/14/16.
  */

sealed trait Space

case class WhiteSpace(piece: Option[Piece]) extends Space

case class BlackSpace(piece: Option[Piece]) extends Space

case class FreeSpace(piece: Option[Piece]) extends Space

case class X(space: List[Space])

case class Board(state: List[X]) {
  def findSpace(coordinate: Coordinate): Space =
    state(coordinate.x).space(coordinate.y)
}

case class Coordinate(x: Int, y: Int) {
  def +(coordinate: Coordinate): Coordinate = Coordinate(x + coordinate.x, y + coordinate.y)

  def -(coordinate: Coordinate) = Coordinate(x - coordinate.x, y - coordinate.y)
}

object Coordinate {
  //afterMoveCoordinatesはただしい値がはいっていないとだめ
  def toDirection(beforeMoveCoordinate: Coordinate, afterMoveCoordinates: Set[Coordinate], piece: Piece): Direction = {
    afterMoveCoordinates.head - beforeMoveCoordinate match {
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
    }
  }
}