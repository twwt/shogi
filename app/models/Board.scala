package models

import models.Coordinate.{X, Y}
import shapeless.{Nat, Sized}

/** * Created by taishi on 7/19/16.  */
case class Coordinate(x: X, y: Y)

object Coordinate {
  type X = shapeless.Sized[scala.collection.immutable.IndexedSeq[Int], shapeless.nat._9]
  type Y = shapeless.Sized[scala.collection.immutable.IndexedSeq[Int], shapeless.nat._9]
}