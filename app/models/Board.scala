package models

import models.Board.{BoardState}
import shapeless._

/** * Created by taishi on 7/19/16.  */
case class Coordinate(x: Int, y: Int)

case class Board(val boardState: BoardState) {
}

object Board {
  type Space = Option[Map[Player, Piece]]
  type X = Map[Int, Y]
  type Y = List[Space]
  type BoardState = Map[Int, List[Space]]
}