package models

import shapeless._
import syntax.sized._

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

class Board(val boardState: shapeless.Sized[List[shapeless.Sized[List[Option[Map[Player, Piece]]], shapeless.nat._9]], shapeless.nat._9]) {

  //  val ou = Ou
  //  val l: AxisLength :: AxisLength :: Player :: Option[Piece] :: HNil = _2 :: _3 :: new White :: Some(Ou) :: HNil
  //  List(l,l,l)
  //
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

  def intToNat(int: Int): Nat = {
    int match {
      case 1 => Nat._1
      case 2 => Nat._2
      case 3 => Nat._3
      case 4 => Nat._4
      case 5 => Nat._5
      case 6 => Nat._6
      case 7 => Nat._7
      case 8 => Nat._8
      case 9 => Nat._9
    }
  }

  def findPiece(coordinate: Coordinate)(implicit boardState: shapeless.Sized[List[shapeless.Sized[List[Option[Map[Player, Piece]]], shapeless.nat._9]], shapeless.nat._9]): Option[Map[Player, Piece]] = {
    val x = coordinate.x.length
    val y = coordinate.y.length
    boardState.unsized(x).unsized(y)
  }

  //atPointから動ける距離
  def findMoveRange(atPoint: Coordinate, piece: Piece, player: Player): List[Coordinate] = {
    afterMoveCoordinateFromAtPoint(piece.moveRange, atPoint)
      .filter(_ != atPoint)
      .filter(c => c.x.length <= 9 && 1 <= c.x.length)
      .filter(c => c.y.length <= 9 && 1 <= c.y.length).toList
  }

  private def afterMoveCoordinateFromAtPoint(pieceOfMoveRange: List[RelativeCoordinate], atPoint: Coordinate): Set[Coordinate] = {
    pieceOfMoveRange.flatMap { c =>
      c match {
        case coordinate if coordinate.x == 0 && coordinate.y == 0 => List(atPoint)
        case coordinate if c.x.length.abs == c.y.length.abs => afterMoveCoordinate(coordinate, atPoint)
        case coordinate if c.x.length.abs != c.y.length.abs => afterMoveCoordinate(coordinate, atPoint)
      }
    }.toSet
  }

  private def afterMoveCoordinate(coordinate: RelativeCoordinate, atPoint: Coordinate): List[Coordinate] = {
    val resultCoordinate = Coordinate(intToAxisLenght(coordinate.x.length + atPoint.x.length), intToAxisLenght(coordinate.y.length + atPoint.y.length))
    val xRange: List[Int] = if (0 < resultCoordinate.x.length) (atPoint.x.length to resultCoordinate.x.length).toList else (resultCoordinate.x.length to atPoint.x.length).toList.reverse
    val yRange: List[Int] = if (0 < resultCoordinate.y.length) (atPoint.y.length to resultCoordinate.y.length).toList else (resultCoordinate.y.length to atPoint.y.length).toList.reverse
    xRange.zip(yRange).map(c => Coordinate(intToAxisLenght(c._1), intToAxisLenght(c._2)))
  }

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

  def coordinateSum(coordinateA: Coordinate, coordinateB: Coordinate): Coordinate = {
    val x = coordinateA.x.length + coordinateB.x.length
    val y = coordinateA.y.length + coordinateB.y.length
    Coordinate(intToAxisLenght(x), intToAxisLenght(y))
  }

  def coordinateSum(coordinate: Coordinate, relativeCoordinate: RelativeCoordinate): Coordinate = {
    val x = coordinate.x.length + relativeCoordinate.x.length
    val y = coordinate.y.length + relativeCoordinate.y.length
    Coordinate(intToAxisLenght(x), intToAxisLenght(y))
  }

  def xTimesCoordinate(coordinate: RelativeCoordinate, xTimes: Int) = {
    val x = coordinate.x.length * xTimes
    val y = coordinate.y.length * xTimes
    Coordinate(intToAxisLenght(x), intToAxisLenght(y))
  }


  //  def maxCoordinateRange(distances: List[RelativeCoordinate], atPoint: Coordinate): List[Coordinate] = {
  //    distances.map(coordinateSum(atPoint, _))
  //      .filter(_.x.length <= 9)
  //      .filter(_.y.length <= 9)
  //      .sortBy(p => (p.x.abs + p.y.abs))
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
  //
  //  def searchShortestDistanceFromAtPoint(coordinate: Coordinate, atPoint: Coordinate): Coordinate = {
  //    val xDistance = coordinate.x - atPoint.x
  //    val yDistance = coordinate.y - atPoint.y
  //    Coordinate(xDistance, yDistance)
  //  }

  //  def canMoveRange(pieceOfMoveRange: List[RelativeCoordinate], selfPlayer: Player, atPoint: Coordinate): List[Coordinate] = {
  //    val up = RelativeCoordinate(0, 1)
  //    val down = RelativeCoordinate(0, -1)
  //    val left = RelativeCoordinate(-1, 0)
  //    val right = RelativeCoordinate(1, 0)
  //    val upLeft = RelativeCoordinate(1, -1)
  //    val upRight = RelativeCoordinate(1, 1)
  //    val downLeft = RelativeCoordinate(-1, -1)
  //    val downRight = RelativeCoordinate(1, -1)
  //    maxCoordinateRange(List(up, down, left, right, upLeft, upRight, downLeft, downRight), atPoint)
  //      .filter(pieceOfMoveRange.contains(_))
  //      .takeWhile(searchOpponentPieceOrFreeSpace(selfPlayer, _))
  //  }
}