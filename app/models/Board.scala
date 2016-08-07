package models

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
