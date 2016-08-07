package models

import shapeless._

/**
  * Created by taishi on 8/4/16.
  */
object Direction {
  type AroundMoveRange = List[MoveRange]
}

abstract class Distance(val size: IndexedSeq[Int])

case object Far extends Distance(-8 to 8)

case object Near extends Distance(-1 to 1)

case object CanNotMove extends Distance(IndexedSeq(0))

sealed class MoveRange(val xx: Distance = CanNotMove, val yy: Distance = CanNotMove)

case class Up(y: Distance) extends MoveRange(yy = y)

case class Down(y: Distance) extends MoveRange(yy = y)

case class Left(x: Distance) extends MoveRange(xx = x)

case class Right(x: Distance) extends MoveRange(xx = x)

case class UpLeft(x: Distance, y: Distance) extends MoveRange(x, y)

case class UpRight(x: Distance, y: Distance) extends MoveRange(x, y)

case class DownLeft(x: Distance, y: Distance) extends MoveRange(x, y)

case class DownRight(x: Distance, y: Distance) extends MoveRange(x, y)


//object Direction {
//  type AroundMoveRange = Up[_] :: Down[_] :: Left[_] :: Right[_] :: UpLeft[_] :: UpRight[_] :: DownLeft[_] :: DownRight[_] :: HNil
//  //  type AroundMoveRange = List[Seq[Coordinate]]
//}
//
//object plus1 extends Poly1 {
//  implicit val caseInt = at[Int](_ + 1)
//}
//
//abstract class Distance[T <: Nat](val size: Sized[IndexedSeq[Int], T])
//
//case object Far extends Distance(Sized(-8, -7, -6, -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8))
//
//case object Near extends Distance(Sized(-1, 0, 1))
//
//case object CanNotMove extends Distance(Sized(0))
//
//sealed trait MoveRange
//
//case class Up[T <: Distance[_]](y: T) extends MoveRange
//
//case class Down[T <: Distance[_]](y: T) extends MoveRange
//
//case class Left[T <: Distance[_]](y: T) extends MoveRange
//
//case class Right[T <: Distance[_]](y: T) extends MoveRange
//
//case class UpLeft[T <: Distance[_]](x: T, y: T) extends MoveRange
//
//case class UpRight[T <: Distance[_]](x: T, y: T) extends MoveRange
//
//case class DownLeft[T <: Distance[_]](x: T, y: T) extends MoveRange
//
//case class DownRight[T <: Distance[_]](x: T, y: T) extends MoveRange
//
