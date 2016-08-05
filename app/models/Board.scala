package models

import shapeless._
import models.Board._

/** * Created by taishi on 7/19/16.  */
case class Coordinate(x: Int, y: Int)

case class Board(val boardState: BoardState) {

  def changeBoard(spaceA: Space)(spaceB: Space)(player: Player): BoardState = {
    val spaceB: Space = boardState(coordinateB.x)(coordinateB.y)
    boardState.mapValues(exchange(_)(coordinateA.y)(spaceB))
  }

  def isMoveCoordinate(coordinateA: Coordinate)(coordinateB: Coordinate)(player: Player): Boolean = {
    findSpace(coordinateB) match {
      case Some(space) if space.contains(player) == true => false
      case Some(space) if space.contains(player) == false => true
      case None => true
    }
  }

  def findSpace(coordinate: Coordinate): Space = {
    boardState(coordinate.x)(coordinate.y)
  }

  def findCoordinate(space: Space) = {
    boardState.map
  }
}

}

object Board {
  type Space = Option[Map[Player, Piece]]
  type X = Map[Int, Y]
  type Y = List[Space]
  type BoardState = Map[Int, List[Space]]

  def exchange[T](l: List[T])(n: Int)(exchange: T): List[T] =
    l.zipWithIndex.map { case (v, i) => if (i == n) exchange else v }

  def searchSpaceIndex(l: List[Space])(space: Space): Option[Int] =
    l.zipWithIndex.dropWhile(_._1 != space).map(_._2).headOption

}