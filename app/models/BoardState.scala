package models

/**
  * Created by taishi on 8/14/16.
  */

sealed trait Space

case class WhiteSpace(piece: Option[Piece]) extends Space

case class BlackSpace(piece: Option[Piece]) extends Space

case class FreeSpace(piece: Option[Piece]) extends Space

case class X(space: List[Space])

case class BoardState(state: List[X])

case class Coordinate(x: Int, y: Int) {
  def +(coordinate: Coordinate): Coordinate = Coordinate(x + coordinate.x, y + coordinate.y)

  def -(coordinate: Coordinate) = Coordinate(x - coordinate.x, y - coordinate.y)
}