package models

import models.Board.{BoardState, X, Y}
import shapeless._

/** * Created by taishi on 7/19/16.  */
case class Coordinate(x: X, y: Y)

class Board(val boardState: BoardState) {

}

object Board {
  type X = shapeless.Sized[scala.collection.immutable.IndexedSeq[Int], shapeless.nat._9]
  type Y = shapeless.Sized[scala.collection.immutable.IndexedSeq[Int], shapeless.nat._9]
  type Space = Option[Map[Player, Piece]]
  type BoardState = Sized[List[Sized[List[Space], nat._9]], nat._9]
}