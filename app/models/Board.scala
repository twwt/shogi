package models


import models.Board._
import scalaz._
import Scalaz._

/** * Created by taishi on 7/19/16.  */
case class Coordinate(x: Int, y: Int)

case class Space(space: Option[Map[Player, Piece]])

case class X(x: Map[Int, Y])

case class Y(y: List[Space])

case class BoardState(state: Map[Int, Y])

case class Board(val board: BoardState) {

  def changeBoard(moveCoordinate: Coordinate)(space: Space): BoardState = {
    val value: List[Space] = (for {
      (xIndex, y) <- board.state
      s <- y.y
    } yield {
      if (space == s) space else s
    }).toList

    val newBoard = board.copy(board = board.state.mapValues(y => exchange(y.y)(moveCoordinate.y)(space)))
    board.state.map {
      case (xIndex, y) if xIndex == moveCoordinate.x =>
        (xIndex, exchange(y.y)(moveCoordinate.y)(space))
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

  def exchange[T](l: List[T])(n: Int)(exchange: T): List[T] =
    l.zipWithIndex.map { case (v, i) => if (i == n) exchange else v }

  def searchSpaceIndex(l: List[Space])(space: Space): Option[Int] =
    l.zipWithIndex.dropWhile(_._1 != space).map(_._2).headOption

}