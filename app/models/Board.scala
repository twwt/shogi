package models

import models.Board._

/** * Created by taishi on 7/19/16.  */
case class Coordinate(x: Int, y: Int) {
  def +(coordinate: Coordinate): Coordinate = Coordinate(x + coordinate.x, y + coordinate.y)

  def -(coordinate: Coordinate) = Coordinate(x - coordinate.x, y - coordinate.y)
}

case class Board(val board: BoardState) {

  def changeBoard(moveCoordinate: Coordinate)(space: Space): BoardState = {
    board.map {
      case (xIndex, y) if xIndex == moveCoordinate.x =>
        (xIndex, Map(moveCoordinate.y -> exchange(y(moveCoordinate.y))(moveCoordinate.y)(space)))
      case (xIndex, y) => (xIndex, y)
    }
  }

  def isMoveCoordinate(coordinateA: Coordinate)(coordinateB: Coordinate)(selfPlayer: Player): Option[Player] = {
    findSpace(coordinateB) match {
      case Some(space) if space.isDefinedAt(selfPlayer) => Some(selfPlayer)
      case Some(space) if !space.isDefinedAt(selfPlayer) => Some(selfPlayer.reversePlayer)
      case None => None
    }
  }

  def shortestRange(range: IndexedSeq[Coordinate])(player: Player): IndexedSeq[Space] = {
    val spaces: IndexedSeq[Space] = range.map(findSpace)
    def loop(n: Int): Int = {
      spaces(n) match {
        case Some(space) if space.contains(player) => n - 1
        case Some(space) if !space.contains(player) => n
        case _ => loop(n + 1)
      }
    }
    spaces.take(loop(0))
  }

  def move(player: Player, moveBeforeCoordinate: Coordinate, piece: Piece): List[IndexedSeq[Space]] = {
    val result = for {
      direction <- piece.aroundMoveRange
      l = direction.xx.size.zip(direction.yy.size)
      cList = l.map(a => Coordinate(a._1, a._2))
    } yield {
      val moveRange = cList.map(_ + moveBeforeCoordinate)
      shortestRange(moveRange)(player)
    }
    result.map(println)
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


  def findSpace(coordinate: Coordinate): Space = {
    println(board)
    println(coordinate)
    println(board(0))
    board(coordinate.x)(coordinate.y)
  }

  def findCoordinate(space: Space): Option[Coordinate] = {
    (for {
      x <- board
      xIndex = x._1
      y = x._2
      yMap <- y
    } yield {
      val yIndex: Int = yMap._2.indexOf(space)
      Coordinate(xIndex, yIndex)
    }).toList.headOption
  }
}

object Board {
  type Space = Option[Map[Player, Piece]]
  type X = Map[Int, Y]
  type Y = Map[Int, List[Space]]
  type BoardState = Map[Int, Y]

  def exchange[T](l: List[T])(n: Int)(exchange: T): List[T] =
    l.zipWithIndex.map { case (v, i) => if (i == n) exchange else v }

  def searchSpaceIndex(l: List[Space])(space: Space): Option[Int] =
    l.zipWithIndex.dropWhile(_._1 != space).map(_._2).headOption
}
