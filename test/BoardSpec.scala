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

  behavior of "Board"

  //中置演算子+を定義
  it should "changeBoard" in {
    board.changeBoard()
  }


}