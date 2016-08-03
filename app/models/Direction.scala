package models
import shapeless.{Nat, Sized}

/**
  * Created by taishi on 8/4/16.
  */
object Direction {
  type AroundMoveRange = List[Seq[Coordinate]]
}

abstract class Distance[T <: Nat](val size: Sized[IndexedSeq[Int], T])
case object Far extends Distance(Sized(-8, -7, -6, -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8))
case object Near extends Distance(Sized(-1, 0, 1))
case object CanNotMove extends Distance(Sized(0))

sealed trait MoveRange
case class Up[T <: Distance[_]](x: T, y: T) extends MoveRange
case class Down[T <: Distance[_]](x: T, y: T) extends MoveRange
case class Left[T <: Distance[_]](x: T, y: T) extends MoveRange
case class Right[T <: Distance[_]](x: T, y: T) extends MoveRange
case class UpLeft[T <: Distance[_]](x: T, y: T) extends MoveRange
case class UpRight[T <: Distance[_]](x: T, y: T) extends MoveRange
case class DownLeft[T <: Distance[_]](x: T, y: T) extends MoveRange
case class DownRight[T <: Distance[_]](x: T, y: T) extends MoveRange
case class Around(up: Up, down: Down, left: Left, right: Right, upLeft: UpLeft, upRight: UpRight, downLeft: DownLeft, downRight: DownRight)
