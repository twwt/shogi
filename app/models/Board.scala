package models

import shapeless._
import scala.collection.immutable.ListMap
import scala.language.higherKinds

/**
  * Created by taishi on 7/19/16.
  */

case class Coordinate(x: AxisLength, y: AxisLength)

case class PieceInHand(pieces: Map[Player, Piece])

sealed abstract class AxisLength(val length: Int)

case object _0 extends AxisLength(0)

case object _1 extends AxisLength(1)

case object _2 extends AxisLength(2)

case object _3 extends AxisLength(3)

case object _4 extends AxisLength(4)

case object _5 extends AxisLength(5)

case object _6 extends AxisLength(6)

case object _7 extends AxisLength(7)

case object _8 extends AxisLength(8)

case object _9 extends AxisLength(9)

class Board(implicit val board: shapeless.Sized[List[shapeless.Sized[List[Option[Map[Player, Piece]]], shapeless.nat._9]], shapeless.nat._9]) {

  def takeX(takeNum: Int)(implicit boardState: shapeless.Sized[List[shapeless.Sized[List[Option[Map[Player, Piece]]], shapeless.nat._9]], shapeless.nat._9]) = {
    boardState(takeNum)
  }

  def takeY(takeNum: Int)(implicit boardState: shapeless.Sized[List[shapeless.Sized[List[Option[Map[Player, Piece]]], shapeless.nat._9]], shapeless.nat._9]) = {
    boardState.map(p => p(takeNum))
  }

  def intToAxisLenght(int: Int): AxisLength = {
    int match {
      case 0 => _0
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

  def findPiece(coordinate: Coordinate)(implicit boardState: shapeless.Sized[List[shapeless.Sized[List[Option[Map[Player, Piece]]], shapeless.nat._9]], shapeless.nat._9]): Option[Map[Player, Piece]] = {
    val x = coordinate.x.length
    val y = coordinate.y.length
    boardState(x)(y)
  }


  def findPiece(coordinate: Coordinate): Option[Piece] = {
    val x: Int = coordinate.x.length
    val y: Int = coordinate.y.length
    onBoardPieces.map()
    onBoardPieces.filter(_.coordinate == coordinate) match {
      case c if c.size == 0 => None
      case c if c.size == 1 => Some(c.head.piece)
    }
  }

  def findMoveRange(atPoint: Coordinate, piece: Piece, player: Player): List[Coordinate] = {
    afterMoveCoordinateFromAtPoint(piece.moveRange, atPoint)
      .distinct.filter(_ != atPoint)
      .filter(c => c.x <= 9 && 1 <= c.x)
      .filter(c => c.y <= 9 && 1 <= c.y)
  }

  private def afterMoveCoordinateFromAtPoint(pieceOfMoveRange: List[RelativeCoordinate], atPoint: Coordinate): List[Coordinate] = {
    pieceOfMoveRange.flatMap { c =>
      c match {
        case coordinate if coordinate.x == 0 && coordinate.y == 0 => List(atPoint)
        case coordinate if c.x.abs == c.y.abs => afterMoveCoordinate(coordinate, atPoint)
        case coordinate if c.x.abs != c.y.abs => afterMoveCoordinate(coordinate, atPoint)
      }
    }
  }

  private def afterMoveCoordinate(coordinate: RelativeCoordinate, atPoint: Coordinate): List[Coordinate] = {
    val resultCoordinate = Coordinate(intToAxisLenght(coordinate.x + atPoint.x.length), intToAxisLenght(coordinate.y + atPoint.y.length))
    val xRange: List[Int] = if (0 < resultCoordinate.x.length) (atPoint.x.length to resultCoordinate.x.length).toList else (resultCoordinate.x.length to atPoint.x.length).toList.reverse
    val yRange: List[Int] = if (0 < resultCoordinate.y.length) (atPoint.y.length to resultCoordinate.y.length).toList else (resultCoordinate.y.length to atPoint.y.length).toList.reverse
    xRange.zip(yRange).map(c => Coordinate(intToAxisLenght(c._1), intToAxisLenght(c._2)))
  }

  def isNihu(atPoint: Coordinate, player: Player) = {
    val verticalLine: Set[Coordinate] = (1 to 9).map {
      Coordinate(atPoint.x, _)
    }.toSet
    val onBoardHuCoordinate: Set[Coordinate] = onBoardPieces.filter(_.player == player).filter {
      _.piece match {
        case Hu => true
        case _ => false
      }
    }.map(_.coordinate)
    (verticalLine.map(_.y) intersect onBoardHuCoordinate.map(_.y)).size != 0
  }

  def searchOpponentPieceOrFreeSpace(selfPlayer: Player, atPoint: Coordinate): Boolean = {
    onBoardPieces.filter(_.coordinate == atPoint).headOption match {
      case Some(p) if p.player != selfPlayer => true
      case Some(p) if p.player == selfPlayer => false
      case None => true
    }
  }

  def coordinateSum(coordinateA: Coordinate, coordinateB: Coordinate): Coordinate = {
    val x = coordinateA.x + coordinateB.x
    val y = coordinateA.y + coordinateB.y
    Coordinate(x, y)
  }

  def xTimesCoordinate(coordinate: RelativeCoordinate, xTimes: Int) = {
    val x = coordinate.x * xTimes
    val y = coordinate.y * xTimes
    Coordinate(x, y)
  }

  def maxCoordinateRange(distances: List[RelativeCoordinate], atPoint: Coordinate): List[Coordinate] = {
    distances.flatMap {
      distance => (for {
        i <- (1 to 8)
        c = coordinateSum(xTimesCoordinate(distance, i), atPoint)
        if c.x <= 9 && c.y <= 9
      } yield {
        Coordinate(c.x, c.y)
      }).toList
    }.sortBy(p => (p.x.abs + p.y.abs))
  }

  def searchShortestDistanceFromAtPoint(coordinate: Coordinate, atPoint: Coordinate): Coordinate = {
    val xDistance = coordinate.x - atPoint.x
    val yDistance = coordinate.y - atPoint.y
    Coordinate(xDistance, yDistance)
  }

  def canMoveRange(pieceOfMoveRange: List[RelativeCoordinate], selfPlayer: Player, atPoint: Coordinate): List[Coordinate] = {
    val up = RelativeCoordinate(0, 1)
    val down = RelativeCoordinate(0, -1)
    val left = RelativeCoordinate(-1, 0)
    val right = RelativeCoordinate(1, 0)
    val upLeft = RelativeCoordinate(1, -1)
    val upRight = RelativeCoordinate(1, 1)
    val downLeft = RelativeCoordinate(-1, -1)
    val downRight = RelativeCoordinate(1, -1)
    maxCoordinateRange(List(up, down, left, right, upLeft, upRight, downLeft, downRight), atPoint)
      .filter(pieceOfMoveRange.contains(_))
      .takeWhile(searchOpponentPieceOrFreeSpace(selfPlayer, _))
  }
}