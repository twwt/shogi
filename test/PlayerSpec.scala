import models._
import org.scalatest.{FlatSpec, Matchers}
import sun.jvm.hotspot.memory.Space

/**
  * Created by taishi on 8/14/16.
  */
class PlayerSpec extends FlatSpec with Matchers {
  behavior of "Player"

  val freeSpace = FreeSpace(None)
  val space = WhiteSpace(Some(Ou))
  val x = X()
  val boardState = BoardState()
  val game = new Game()
  it should "movePiece" in {

  }
}
