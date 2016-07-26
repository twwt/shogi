package models

import org.scalacheck.Prop.True
import shapeless._
import syntax.sized._

import scala.util.Try

/**
  * Created by taishi on 7/19/16.
  */

case class Coordinate(x: AxisLength, y: AxisLength)

case class PieceInHand(pieces: Map[Player, Piece])

sealed abstract class AxisLength(val length: Int)

case object _1 extends AxisLength(1)

case object _2 extends AxisLength(2)

case object _3 extends AxisLength(3)

case object _4 extends AxisLength(4)

case object _5 extends AxisLength(5)

case object _6 extends AxisLength(6)

case object _7 extends AxisLength(7)

case object _8 extends AxisLength(8)

case object _9 extends AxisLength(9)

sealed abstract class Direction(val x: RelativeRange, val y: RelativeRange)

case object Up extends Direction(r_0, r_1)

case object Down extends Direction(r_0, r__1)

case object Left extends Direction(r__1, r_0)

case object Right extends Direction(r_1, r_0)

case object UpLeft extends Direction(r_1, r__1)

case object UpRight extends Direction(r_1, r_1)

case object DownLeft extends Direction(r__1, r__1)

case object DownRight extends Direction(r__1, r_1)

class Board(val boardState: shapeless.Sized[List[shapeless.Sized[List[Option[Map[Player, Piece]]], shapeless.nat._9]], shapeless.nat._9]) {

  def takeX(takeNum: AxisLength)(implicit boardState: shapeless.Sized[List[shapeless.Sized[List[Option[Map[Player, Piece]]], shapeless.nat._9]], shapeless.nat._9]): Sized[List[Option[Map[Player, Piece]]], nat._9] = {
    boardState.unsized(takeNum.length)
  }

  def takeY(takeNum: AxisLength)(implicit boardState: shapeless.Sized[List[shapeless.Sized[List[Option[Map[Player, Piece]]], shapeless.nat._9]], shapeless.nat._9]): List[Option[Map[Player, Piece]]] = {
    boardState.unsized.map(p => p.unsized(takeNum.length))
  }

  def takeAtPoint(x: AxisLength, y: AxisLength)(implicit boardState: shapeless.Sized[List[shapeless.Sized[List[Option[Map[Player, Piece]]], shapeless.nat._9]], shapeless.nat._9]): Option[Map[Player, Piece]] = {
    boardState.unsized(x.length).unsized(y.length)
  }

  def intToAxisLenght(int: Int): AxisLength = {
    int match {
      case 1 => _1
      case 2 => _2
      case 3 => _3
      case 4 => _4
      case 5 => _5
      case 6 => _6
      case 7 => _7
      case 8 => _8
      case 9 => _9
    }
  }

  def intToRelativeRange(int: Int): RelativeRange = {
    int match {
      case 0 => r_0
      case 1 => r_1
      //      case 2 => r_2
      //      case 3 => r_3
      //      case 4 => r_4
      //      case 5 => r_5
      //      case 6 => r_6
      //      case 7 => r_7
      case 8 => r_8
      //      case 9 => r_9
      case -1 => r__1
      //      case -2 => r__2
      //      case -3 => r__3
      //      case -4 => r__4
      //      case -5 => r__5
      //      case -6 => r__6
      //      case -7 => r__7
      case -8 => r__8
      //      case -9 => r__9
    }
  }

  object upPlus1 extends Poly1 {
    implicit val caseAxisLength = at[AxisLength](c => Try(intToAxisLenght(c.length + 1)).toOption)
  }

  object downPlus1 extends Poly1 {
    implicit val caseAxisLength = at[AxisLength](c => Try(intToAxisLenght(c.length + -1)).toOption)
  }

  object leftPlus1 extends Poly1 {
    implicit val caseAxisLength = at[AxisLength](c => Try(intToAxisLenght(-1 + c.length)).toOption)
  }

  object rightPlus1 extends Poly1 {
    implicit val caseAxisLength = at[AxisLength](c => Try(intToAxisLenght(1 + c.length)).toOption)
  }

  val upPlus1All = everywhere(upPlus1)
  val downPlus1All = everywhere(downPlus1)
  val leftPlus1All = everywhere(leftPlus1)
  val rightPlus1All = everywhere(rightPlus1)

  def plus1(atPoint: Coordinate, direction: Direction) = {
    direction match {
      case Up => upPlus1All(atPoint)
      case Down => downPlus1All(atPoint)
      case Left => leftPlus1All(atPoint)
      case Right => rightPlus1All(atPoint)
      case UpLeft => upPlus1All(leftPlus1All(atPoint))
      case UpRight => upPlus1All(rightPlus1All(atPoint))
      case DownLeft => downPlus1All(leftPlus1All(atPoint))
      case DownRight => downPlus1All(rightPlus1All(atPoint))
    }
  }

  object upMinus1 extends Poly1 {
    implicit val caseAxisLength = at[AxisLength](c => Try(intToAxisLenght(c.length - 1)).toOption)
  }

  object downMinus1 extends Poly1 {
    implicit val caseAxisLength = at[AxisLength](c => Try(intToAxisLenght(c.length - -1)).toOption)
  }

  object leftMinus1 extends Poly1 {
    implicit val caseAxisLength = at[AxisLength](c => Try(intToAxisLenght(-1 - c.length)).toOption)
  }

  object rightMinus1 extends Poly1 {
    implicit val caseAxisLength = at[AxisLength](c => Try(intToAxisLenght(1 - c.length)).toOption)
  }

  val upMinus1All = everywhere(upMinus1)
  val downMinus1All = everywhere(downMinus1)
  val leftMinus1All = everywhere(leftMinus1)
  val rightMinus1All = everywhere(rightMinus1)

  def minus1(atPoint: Coordinate, direction: Direction) = {
    direction match {
      case Up => upMinus1All(atPoint)
      case Down => downMinus1All(atPoint)
      case Left => leftMinus1All(atPoint)
      case Right => rightMinus1All(atPoint)
      case UpLeft => upMinus1All(leftMinus1All(atPoint))
      case UpRight => upMinus1All(rightMinus1All(atPoint))
      case DownLeft => downMinus1All(leftMinus1All(atPoint))
      case DownRight => downMinus1All(rightMinus1All(atPoint))
    }
  }

  def coordinateMinus(atPoint: Coordinate, direction: Direction) = {
    val x = atPoint.x.length - direction.x.list.length
    val y = atPoint.x.length - direction.x.list.length
    Coordinate(intToAxisLenght(x), intToAxisLenght(y))
  }

  def findSpace(coordinate: Coordinate)(implicit boardState: shapeless.Sized[List[shapeless.Sized[List[Option[Map[Player, Piece]]], shapeless.nat._9]], shapeless.nat._9]): Option[Map[Player, Piece]] = {
    val x = coordinate.x.length - 1
    val y = coordinate.y.length - 1
    boardState.unsized(x).unsized(y)
  }

  def findPiece(coordinate: Coordinate, player: Player)(implicit boardState: shapeless.Sized[List[shapeless.Sized[List[Option[Map[Player, Piece]]], shapeless.nat._9]], shapeless.nat._9]): Option[Piece] = {
    val x = coordinate.x.length - 1
    val y = coordinate.y.length - 1
    boardState.unsized(x).unsized(y).map(_ (player))
  }

  //  //atPointから動ける距離
  //  def findMoveRange(atPoint: Coordinate, piece: Piece, player: Player): List[Coordinate] = {
  //    afterMoveCoordinateFromAtPoint(piece.moveRange, atPoint)
  //      .filter(_ != atPoint)
  //      .filter(c => c.x.length <= 9 && 1 <= c.x.length)
  //      .filter(c => c.y.length <= 9 && 1 <= c.y.length).toList
  //  }

  //  private def afterMoveCoordinateFromAtPoint(pieceOfMoveRange: List[RelativeCoordinate], atPoint: Coordinate): Set[Coordinate] = {
  //    pieceOfMoveRange.flatMap { c =>
  //      c match {
  //        case coordinate if coordinate.x == 0 && coordinate.y == 0 => List(atPoint)
  //        case coordinate if c.x.length.abs == c.y.length.abs => afterMoveCoordinate(coordinate, atPoint)
  //        case coordinate if c.x.length.abs != c.y.length.abs => afterMoveCoordinate(coordinate, atPoint)
  //      }
  //    }.toSet
  //  }

  //  private def afterMoveCoordinate(coordinate: RelativeCoordinate, atPoint: Coordinate): List[Coordinate] = {
  //    val natX = intToNat(coordinate.x.length + atPoint.x.length)
  //    val natY = intToNat(coordinate.y.length + atPoint.y.length)
  //    val resultCoordinate = Coordinate(intToAxisLenght(natX), intToAxisLenght(natY))
  //    val xRange: List[Int] = if (0 < resultCoordinate.x.length) (atPoint.x.length to resultCoordinate.x.length).toList else (resultCoordinate.x.length to atPoint.x.length).toList.reverse
  //    val yRange: List[Int] = if (0 < resultCoordinate.y.length) (atPoint.y.length to resultCoordinate.y.length).toList else (resultCoordinate.y.length to atPoint.y.length).toList.reverse
  //    xRange.zip(yRange).map(c => Coordinate(intToAxisLenght(intToNat(c._1)), intToAxisLenght(intToNat(c._2))))
  //  }

  def isNihu(atPoint: Coordinate, player: Player): Boolean = {
    val atPointX = atPoint.x.length
    val y: Sized[List[Option[Map[Player, Piece]]], nat._9] = boardState.unsized(atPointX)
    y.map(_.filter(_ (player) == Hu).isDefined).forall(_ == true)
  }

  def isEnemyPieceOrFreeSpace(selfPlayer: Player, atPoint: Coordinate): Boolean = {
    val atPointX = atPoint.x.length
    val atPointY = atPoint.y.length
    boardState.unsized(atPointX).unsized(atPointY) match {
      case None => true
      case Some(p) if p.contains(selfPlayer) == true => false
      case Some(p) if p.contains(selfPlayer) == false => true
    }
  }

  def isSelfPlayerPiece(selfPlayer: Player, atPoint: Coordinate): Boolean = {
    val atPointX = atPoint.x.length
    val atPointY = atPoint.y.length
    boardState.unsized(atPointX).unsized(atPointY) match {
      case None => false
      case Some(p) if p.contains(selfPlayer) == true => true
      case Some(p) if p.contains(selfPlayer) == false => false
    }
  }

  def coordinateAdd(coordinateA: Coordinate, coordinateB: Coordinate): Coordinate = {
    val x = intToAxisLenght(coordinateA.x.length + coordinateB.x.length)
    val y = intToAxisLenght(coordinateA.y.length + coordinateB.y.length)
    Coordinate(x, y)
  }

  def coordinateSub(coordinateA: Coordinate, coordinateB: Coordinate): Coordinate = {
    val x = intToAxisLenght(coordinateA.x.length - coordinateB.x.length)
    val y = intToAxisLenght(coordinateA.y.length - coordinateB.y.length)
    Coordinate(x, y)
  }

  def coordinateSum(coordinate: Coordinate, relativeCoordinate: RelativeCoordinate): Option[Coordinate] = {
    val xTmp = coordinate.x.length + relativeCoordinate.x.list.length
    val yTmp = coordinate.y.length + relativeCoordinate.y.list.length
    val x = xTmp match {
      case tmp if tmp <= 0 => None
      case tmp if 0 <= tmp && 9 <= tmp => None
      case tmp => Some(tmp)
    }
    val y = yTmp match {
      case tmp if tmp <= 0 => None
      case tmp if 0 <= tmp && 9 <= tmp => None
      case tmp => Some(tmp)
    }
    if (x.isDefined || y.isDefined) Some(Coordinate(intToAxisLenght(x.get), intToAxisLenght(y.get))) else None
  }

  def coordinateDistance(coordinateA: Coordinate, coordinateB: Coordinate): Int = {
    val result = (coordinateA.x.length - coordinateB.x.length) + (coordinateA.y.length - coordinateB.y.length)
    println(result)
    result
  }

  //  def xTimesCoordinate(coordinate: RelativeCoordinate, xTimes: Int) = {
  //    val x = intToNat(coordinate.x.length * xTimes)
  //    val y = intToNat(coordinate.y.length * xTimes)
  //    Coordinate(intToAxisLenght(x), intToAxisLenght(y))
  //  }

  //  def maxCoordinateRange(atPoint: Coordinate): List[Coordinate] = {
  //    val up = RelativeCoordinate(r_0, r_1)
  //    val down = RelativeCoordinate(r_0, r__1)
  //    val left = RelativeCoordinate(r__1, r_0)
  //    val right = RelativeCoordinate(r_1, r_0)
  //    val upLeft = RelativeCoordinate(r_1, r__1)
  //    val upRight = RelativeCoordinate(r_1, r_1)
  //    val downLeft = RelativeCoordinate(r__1, r__1)
  //    val downRight = RelativeCoordinate(r__1, r_1)
  //    (1 to 8).map(List(up, down, left, right, upLeft, upRight, downLeft, downRight)
  //    def aroundSearch(i: Int, list: List[Coordinate]): List[Coordinate] = {
  //      val result: List[Coordinate] = List(up, down, left, right, upLeft, upRight, downLeft, downRight).map { c =>
  //        (1 to 8).map { i =>
  //          Coordinate(intToAxisLenght(c.x.length + i), intToAxisLenght(c.x.length + i))
  //        }.filter(c => c.x.length <= 9 || c.y.length <= 9)
  //          .map(s => s)
  //      }
  //      def distance(int: Int, relativeCoordinate: RelativeCoordinate): Coordinate = {
  //        val r = RelativeCoordinate(intToRelativeRange((relativeCoordinate.x.length * int) + atPoint.x.length), intToRelativeRange((relativeCoordinate.y.length * int) + atPoint.y.length))
  //        1 match {
  //          case s if isSelfPlayerPiece(s, coordinate) =>
  //        }
  //        distance(int + 1, r)
  //      }
  //    }
  //    val nextIndex = i + 1
  //    if (i <= 9) aroundSearch(nextIndex, result) else result
  //  }

  //  def maxCoordinateRange(distances: List[RelativeCoordinate], atPoint: Coordinate): List[Coordinate] = {
  //    distances.flatMap {
  //      distance => (for {
  //        i <- (1 to 8)
  //        c = coordinateSum(xTimesCoordinate(distance, i), atPoint)
  //        if c.x <= 9 && c.y <= 9
  //      } yield {
  //        Coordinate(c.x, c.y)
  //      }).toList
  //    }.sortBy(p => (p.x.abs + p.y.abs))
  //  }

  def coordinateToDirection(atPoint: Coordinate, afterMoveCoordinate: Coordinate) = {
    val x = afterMoveCoordinate.x.length - atPoint.x.length
    val y = afterMoveCoordinate.y.length - atPoint.y.length
    val gap = (x, y)
    gap match {
      case c if c._1 == 0 && c._2 < 1 => Up
      case c if c._1 == 0 && 0 < c._2 => Down
      case c if c._1 < 0 && c._2 == 0 => Left
      case c if 0 < c._1 && c._2 == 0 => Right
      case c if c._1 < 0 && c._2 < 1 => UpLeft
      case c if 0 < c._1 && c._2 < 1 => UpRight
      case c if c._1 < 0 && 0 < c._2 => DownLeft
      case c if 0 < c._1 && 0 < c._2 => DownRight
    }
  }

  def remove(num: Int, list: List[Int]) = list diff List(num)

  def moveRange(relativeCoordinates: List[RelativeCoordinate], atPoint: Coordinate, selfPlayer: Player, index: Int = 0): List[Coordinate] = {
    //    val maxMoveRange = relativeCoordinates.filter(_ != RelativeCoordinate(r_0, r_0)).map(coordinateSum(atPoint, _))
    val xRange: List[List[Int]] = relativeCoordinates.map { c =>
      val r: List[Int] = c.x.list.map(atPoint.x.length - _).sorted
      if (r.contains(0)) ((r diff List(0)).reverse :+ (r.last + 1)) else r
      //      (if (c.x.list.length < atPoint.x.length) {
      //        ((atPoint.x.length + c.x.list.length) to atPoint.x.length).toList
      //      } else {
      //        (atPoint.x.length to atPoint.x.length + c.x.list.length).toList
      //      }).sorted.map { c =>
      //        if (c <= 0) c - 1 else c
      //      }
    }
    val yRange: List[List[Int]] = relativeCoordinates.map { c =>
      val r: List[Int] = c.y.list.map(atPoint.y.length - _).sorted
      if (r.contains(0)) ((r diff List(0)).reverse :+ (r.last + 1)) else r
      //      (if (c.y.list.length < atPoint.y.length) {
      //        ((atPoint.y.length + c.y.list.length) to atPoint.y.length).toList
      //      } else {
      //        (atPoint.y.length to atPoint.y.length + c.y.list.length).toList
      //      }).sorted.map { c =>
      //        if (c <= 0) c - 1 else c
      //      }
    }

    println(xRange)
    println(yRange)
    println(xRange.zip(yRange).map(r => r._1.zip(r._2).filter(c => c != (atPoint.x.length,atPoint.y.length)).filter(c => c._1 < 10 && c._2 < 10).filter(c => 0 < c._1 && 0 < c._2).map(c => Coordinate(intToAxisLenght(c._1), intToAxisLenght(c._2)))))
    xRange.zip(yRange).flatMap(r => r._1.zip(r._2).filter(c => c._1 != atPoint.x.length || c._1 != atPoint.y.length).filter(c => c._1 < 10 && c._2 < 10).filter(c => 0 < c._1 && 0 < c._2).map(c => Coordinate(intToAxisLenght(c._1), intToAxisLenght(c._2))))
    //    maxMoveRange.flatMap { c =>
    //      println(s"${c.x.length - atPoint.x.length},${c.y.length - atPoint.y.length}")
    //      val x: List[AxisLength] = (if (atPoint.x.length < c.x.length) (atPoint.x.length to c.x.length) else (c.x.length to atPoint.x.length)).toList.map(i => intToAxisLenght(i))
    //      val y: List[AxisLength] = (if (atPoint.y.length < c.y.length) (atPoint.y.length to c.y.length) else (c.y.length to atPoint.y.length)).toList.map(i => intToAxisLenght(i))
    //      x.zip(y).map(a => Coordinate(a._1, a._2))
    //    }.filter(_ != atPoint).filter(c => c.x.length <= 9 && c.y.length <= 9).distinct
  }

  def move(afterMoveCoordinate: Coordinate, atPoint: Coordinate, selfPlayer: Player): Coordinate = {
    val direction: Direction = coordinateToDirection(atPoint, afterMoveCoordinate)
    val result = if (atPoint.x.length < afterMoveCoordinate.x.length || atPoint.y.length < afterMoveCoordinate.y.length) {
      val space = findSpace(atPoint)(boardState)
      space match {
        case space if space.isDefined == false =>
          move(afterMoveCoordinate, plus1(atPoint, direction), selfPlayer)
        case space if space.isDefined == true =>
          if (space.get.keys.head == selfPlayer) {
            println(s"atPoint => $atPoint,direction => $direction,minus1 => ${minus1(atPoint, direction)}")
            coordinateMinus(atPoint, direction)
          } else {
            atPoint
          }
      }
    } else {
      atPoint
    }

    result
  }

  def directionMap(atPoint: Coordinate, moveRange: List[Coordinate]): Map[Direction with Product with Serializable, List[Coordinate]] = {
    moveRange.map(println)
    val result = moveRange.groupBy(coordinateToDirection(atPoint, _))
    println(result)
    result
  }

  //  def searchShortestDistanceFromAtPoint(longestCoordinate: Coordinate, atPoint: Coordinate): Coordinate = {
  //    val xDistance = intToNat(longestCoordinate.x.length - atPoint.x.length)
  //    val yDistance = intToNat(longestCoordinate.y.length - atPoint.y.length)
  //    val resultCoordinate = Coordinate(intToAxisLenght(xDistance), intToAxisLenght(yDistance))
  //    coordinateSum(resultCoordinate, atPoint)
  //  }

  val isMoveDirection: PartialFunction[RelativeCoordinate, Boolean] = {
    case c: RelativeCoordinate if c.x.list.length == 0 && c.y.list.length == 0 => false
    case _ => true
  }


  //  def canMoveRange(piece: Piece, selfPlayer: Player, atPoint: Coordinate): List[Coordinate] = {
  //    val moveCoordinates: List[Coordinate] = moveRange(piece.moveRange.filter(isMoveDirection), atPoint)
  //    moveCoordinates.map(findSpace(_)).map {
  //      case space if space.isDefined == false =>
  //    }
  //  }
}