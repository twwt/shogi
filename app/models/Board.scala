package models

<<<<<<< HEAD
import models.Board._
import scala.util.Try
import scalaz._
import Scalaz._

/** * Created by taishi on 7/19/16.  */
case class Coordinate(x: Int, y: Int) {
  def +(coordinate: Coordinate): Coordinate = Coordinate(x + coordinate.x, y + coordinate.y)

  def -(coordinate: Coordinate) = Coordinate(x - coordinate.x, y - coordinate.y)
}

case class Board(val board: BoardState) {

  def changeBoard(moveCoordinate: Coordinate)(newSpace: Space): BoardState =
    board.mapValues(_.map {
      case (yIndex, ySpace) if yIndex == moveCoordinate.y => (yIndex -> newSpace)
      case (yIndex, ySpace) => (yIndex -> ySpace)
    })

  def isMoveCoordinate(coordinateA: Coordinate)(coordinateB: Coordinate)(selfPlayer: Player): Option[Player] = {
    findSpace(coordinateB) match {
      case Some(space) if space.contains(selfPlayer) => Some(selfPlayer)
      case Some(space) if !space.contains(selfPlayer) => Some(selfPlayer.reversePlayer)
      case None => None
    }
  }

  def shortestRange(range: IndexedSeq[Coordinate])(player: Player): IndexedSeq[Space] = {
    val spaces: IndexedSeq[Space] = range.map(findSpace)
    def loop(n: Int): Int = {
      spaces.toList index(n) match {
        case Some(space) if space.contains(player) => n - 1
        case Some(space) if !space.contains(player) => n
        case _ => loop(n + 1)
      }
    }
    spaces.take(loop(0))
  }

  def canMoveRange(player: Player, moveBeforeCoordinate: Coordinate, piece: Piece): List[Space] = {
    val result: List[Space] = for {
      direction <- piece.aroundMoveRange
      l = direction.xx.size.zip(direction.yy.size)
      moveRange = l.map(a => Coordinate(a._1, a._2)).map(_ + moveBeforeCoordinate)
      r <- shortestRange(moveRange)(player)
    } yield {
      r
    }
    println(result)
    result
  }

  //
  //  def move(player: Player, moveBeforeCoordinate: Coordinate, moveAfterCoordinate: Coordinate, piece: Piece): Option[Game] = {
  //    val moveAfterCoordinates = for {
  //      direction <- piece.aroundMoveRange
  //      l = direction.x.size.zip(direction.y.size)
  //      c = l.map(a => Coordinate(a._1, a._2))
  //    } yield {
  //      c.map(_ + moveBeforeCoordinate)
  //    }.filter(動ける範囲)
  //    //
  //  }


  def findSpace(coordinate: Coordinate): Space = coordinate match {
    case c if (c.x < 9 || c.y < 9) =>
      board(coordinate.x)(coordinate.y)
  }

  def findCoordinate(space: Space): Option[Coordinate] = {
    (for {
      x <- board
      xIndex = x._1
      y = x._2
      yMap <- y
      if yMap._2 == space
    } yield {
      Coordinate(xIndex, yMap._1)
    }).toList.headOption
  }
}

object Board {
  type Space = Option[Map[Player, Piece]]
  type X = Map[Int, Y]
  type Y = Map[Int, Space]
  type BoardState = Map[Int, Y]

  def exchange[T](l: List[T])(n: Int)(exchange: T): List[T] =
    l.zipWithIndex.map { case (v, i) => if (i == n) exchange else v }

  def searchSpaceIndex(l: List[Space])(space: Space): Option[Int] =
    l.zipWithIndex.dropWhile(_._1 != space).map(_._2).headOption
}
=======
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

case class OnBoardPiece(player: Player, piece: Piece, coordinate: Coordinate)

class Board(val onBoardPieces: Set[OnBoardPiece]) {

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

  def findPiece(coordinate: Coordinate): Option[Piece] = {
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
>>>>>>> 7f9a1320c96bd4909156b39f3fceb5e8444f50d5
