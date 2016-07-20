package models

import scala.collection.immutable.IndexedSeq

/**
  * Created by taishi on 7/19/16.
  */

case class Coordinate(x: Int, y: Int)

case class OnBoardPiece(player: Player, piece: Piece, coordinate: Coordinate)

class Board(val pieces: Set[OnBoardPiece]) {

  def findPiece(coordinate: Coordinate): Option[Piece] = {
    pieces.filter(_.coordinate == coordinate) match {
      case c if c.size == 0 => None
      case c if c.size == 1 => Some(c.head.piece)
    }
  }

  def findMoveRange(atPoint: Coordinate, piece: Piece, player: Player): List[Coordinate] = {
    afterMovePointfromAtPoint(piece.movement, atPoint)
      .distinct.filter(_ != atPoint)
      .filter(c => c.x <= 9 && 1 <= c.x)
      .filter(c => c.y <= 9 && 1 <= c.y)
  }

  def afterMovePointfromAtPoint(pieceOfMoveRange: MoveRange, atPoint: Coordinate): List[Coordinate] = {
    pieceOfMoveRange.productIterator.map(_.asInstanceOf[Coordinate]).flatMap { c =>
      c match {
        case coordinate if coordinate.x == 0 && coordinate.y == 0 => List(atPoint)
        case coordinate if c.x.abs == c.y.abs => afterMoveCoordinate(coordinate, atPoint)
        case coordinate if c.x.abs != c.y.abs => afterMoveCoordinate(coordinate, atPoint)
      }
    }.toList
  }

  def afterMoveCoordinate(coordinate: Coordinate, atPoint: Coordinate): List[Coordinate] = {
    //    val resultCoordinate = coordinate.x + atPoint.x, coordinate.y + atPoint.y)
    //    val xRange: List[Int] = if (0 < resultCoordinateRange.x) (atPoint.x to resultCoordinate.x).toList else (resultCoordinate.x to atPoint.x).toList.reverse
    //    val yRange: List[Int] = if (0 < resultCoordinateRange.y) (atPoint.y to resultCoordinate.y).toList else (resultCoordinate.y to atPoint.y).toList.reverse
    val resultCoordinate = Coordinate(coordinate.x + atPoint.x, coordinate.y + atPoint.y)
    //    println(resultCoordinate)
    val xRange: List[Int] = if (0 < resultCoordinate.x) (atPoint.x to resultCoordinate.x).toList else (resultCoordinate.x to atPoint.x).toList.reverse
    val yRange: List[Int] = if (0 < resultCoordinate.y) (atPoint.y to resultCoordinate.y).toList else (resultCoordinate.y to atPoint.y).toList.reverse
    xRange.zip(yRange).map(c => Coordinate(c._1, c._2))
  }

  def isNihu(atPoint: Coordinate, player: Player) = {
    val verticalLine: Set[Coordinate] = (1 to 9).map {
      Coordinate(atPoint.x, _)
    }.toSet
    val onBoardHuCoordinate: Set[Coordinate] = pieces.filter(_.player == player).filter {
      _.piece match {
        case Hu => true
        case _ => false
      }
    }.map(_.coordinate)
    (verticalLine.map(_.y) intersect onBoardHuCoordinate.map(_.y)).size != 0
  }

  def searchOpponentPieceOrFreeSpace(player: Player, atPoint: Coordinate): Boolean = {
    pieces.filter(_.player != player).filter(_.coordinate == atPoint).headOption.isDefined
  }

  def coordinateSum(coordinateA: Coordinate, coordinateB: Coordinate): Coordinate = {
    val x = coordinateA.x + coordinateB.x
    val y = coordinateA.y + coordinateB.y
    Coordinate(x, y)
  }

  def xTimesCoordinate(coordinate: Coordinate, xTimes: Int) = {
    val x = coordinate.x * xTimes
    val y = coordinate.y * xTimes
    Coordinate(x, y)
  }

  def maxCoordinateRange(distances: List[Coordinate], atPoint: Coordinate): List[Coordinate] = {
    val s = distances.map {
      distance => (for {
        i <- (1 to 8)
        c = coordinateSum(xTimesCoordinate(distance, i), atPoint)
        if c.x <= 9 && c.y <= 9
      } yield {
        Coordinate(c.x, c.y)
      }).toList
    }
    //    println(s)
    s
  }


  def searchShortestDistanceFromAtPoint(coordinate: Coordinate, atPoint: Coordinate): Coordinate = {
    val xDistance = coordinate.x - atPoint.x
    val yDistance = coordinate.y - atPoint.y
    Coordinate(xDistance, yDistance)
  }

  def canMoveRange(pieceOfMoveRange: List[Coordinate], player: Player, atPoint: Coordinate): List[List[Coordinate]] = {

    val sort = (c: Coordinate) => c match {
      case c.x
    }

    0, 1), atPoint) //現在地点から上端までのリスト
    .filter(pieceOfMoveRange.contains(_)) //上方向の移動範囲だけ抽出する
      .sortBy(_.y) //近い順に並べる？
      .takeWhile(searchOpponentPieceOrFreeSpace(Black, _)) //敵かフリースペースがtrueな限りtakeする

    val up = Coordinate(0, 1)
    val down = Coordinate(0, -1)
    val left = Coordinate(-1, 0)
    val right = Coordinate(1, 0)
    val upLeft = Coordinate(1, -1)
    val upRight = Coordinate(1, 1)
    val downLeft = Coordinate(-1, -1)
    val downRight = Coordinate(1, -1)
    maxCoordinateRange(List(up, down, left, right, upLeft, upRight, downLeft, downRight), atPoint)
      .filter(pieceOfMoveRange.contains(_))
    List(upRange, downRange, leftRange, rightRange, upLeftRange, upRightRange, downLeftRange, downRightRange)
  }

  def
}