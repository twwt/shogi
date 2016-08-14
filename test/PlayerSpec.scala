import models._
import org.scalatest.{FlatSpec, Matchers}
import sun.jvm.hotspot.memory.Space

/**
  * Created by taishi on 8/14/16.
  */
class PlayerSpec extends FlatSpec with Matchers {
  behavior of "Player"

  val white = White(Nil)
  val black = Black(Nil)
  val freeSpace = FreeSpace(None)
  val ou = WhiteSpace(Some(Ou))
  val x = X(List(ou, freeSpace, freeSpace, freeSpace, freeSpace, freeSpace, freeSpace, freeSpace, freeSpace))
  val freeX = X(List(freeSpace, freeSpace, freeSpace, freeSpace, freeSpace, freeSpace, freeSpace, freeSpace, freeSpace))
  val boardState = BoardState(List(x, freeX, freeX, freeX, freeX, freeX, freeX, freeX))
  val game = new Game(boardState, white, black)
  it should "movePiece" in {
    game.turnPlayer
  }
}
