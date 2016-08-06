package models

import models.Board._
import scalaz._
import Scalaz._

/** * Created by taishi on 7/19/16.  */
case class Coordinate(x: Int, y: Int)

case class Board(val boardState: BoardState) {

  def changeBoard(moveCoordinate: Coordinate)(space: Space): BoardState = {
    boardState.map {
      case (xIndex, y) if xIndex == moveCoordinate.x => (xIndex, exchange(y)(moveCoordinate.y)(space))
      case (xIndex, y) => (xIndex, y)
    }
  }

  def isMoveCoordinate(coordinateA: Coordinate)(coordinateB: Coordinate)(player: Player): Boolean = {
    findSpace(coordinateB) match {
      case Some(space) => space.contains(player)
      case None => true
    }
  }

  def findSpace(coordinate: Coordinate): Space = {
    boardState(coordinate.x)(coordinate.y)
  }

  def findCoordinate(space: Space): Option[Coordinate] = {
    (for {
      x <- boardState
      xIndex = x._1
      yIndex = x._2.indexOf(space)
      if yIndex != -1
    } yield {
      Coordinate(xIndex, yIndex)
    }).toList.headOption
  }
}

object Board {
  type Space = Option[Map[Player, Piece]]
  type X = Map[Int, Y]
  type Y = List[Space]
  type BoardState = Map[Int, Y]

  def exchange[T](l: List[T])(n: Int)(exchange: T): List[T] =
    l.zipWithIndex.map { case (v, i) => if (i == n) exchange else v }

  def searchSpaceIndex(l: List[Space])(space: Space): Option[Int] =
    l.zipWithIndex.dropWhile(_._1 != space).map(_._2).headOption

}