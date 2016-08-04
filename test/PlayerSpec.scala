import models._
import org.scalatest.{Matchers, FlatSpec}

/**
  * Created by taishi on 8/4/16.
  */
class PlayerSpec extends FlatSpec with Matchers {
  behavior of "Player"

  val white = White(List(Ou))
  val black = Black(Nil)

  it should "exists" in {
    val bool: Boolean = white.pieceInHand.exists(Ou)
    bool should equal(true)
  }
  it should "addPiece" in {
    val board: Board = white.addPiece(piece)(coordinate)(boardState)
    val space: Space = board.findSpace(coordinate)
    board.flatten.contains(space) should equal(true)
  }
}
