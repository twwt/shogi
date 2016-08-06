/**
  * Created by taishi on 8/1/16.
  */

import models.Board._
import models._
import org.scalatest.FlatSpec
import org.scalatest.Matchers
import shapeless._
import shapeless.Sized

class BoardSpec extends FlatSpec with Matchers {

  val white = White(List(Ou))
  val black = Black(Nil)

  val ouSpace: Space = Some(Map(white -> Ou))
  val y: Y = List(ouSpace, None, None, None, None, None, None, None, None)
  val yNone: Y = List(None
    , None, None, None, None, None, None, None, None)
  val boardState: BoardState = Map(-4 -> y, -3 -> yNone, -2 -> yNone, -1 -> yNone, 0 -> yNone, 1 -> yNone, 2 -> yNone, 3 -> yNone, 4 -> yNone)
  val board = Board(boardState)
  val spaceB: Space = Some(Map(black -> Hu))

  behavior of "Board"

  it should "findCoordinate" in {
    val newCoordinate: Coordinate = board.findCoordinate(ouSpace)
    newCoordinate should equal(Coordinate(-4, 0))
  }

  it should "changeBoard" in {
    val newState: BoardState = board.changeBoard(ouSpace)(spaceB)
    val spaceBCoordinate: Coordinate = board.findCoordinate(spaceB)
    val result: Space = newState(spaceBCoordinate.x)(spaceBCoordinate.y)
    result should equal(ouSpace)
  }


}