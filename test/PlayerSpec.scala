import models.Board.{X, Y, Space, BoardState}
import models._
import org.scalatest.{Matchers, FlatSpec}

/**
  * Created by taishi on 8/4/16.
  */
class PlayerSpec extends FlatSpec with Matchers {
  behavior of "Player"

  val white = White(List(Ou))
  val black = Black(Nil)

  val ouSpace: Space = Some(Map(white -> Ou))
  val y: Y = List(ouSpace, None, None, None, None, None, None, None, None)
  val yNone: Y = List(None
    , None, None, None, None, None, None, None, None)
  val boardState: BoardState = Map(-4 -> y, -3 -> yNone, -2 -> yNone, -1 -> yNone, 0 -> yNone, 1 -> yNone, 2 -> yNone, 3 -> yNone, 4 -> yNone)
  val board = Board(boardState)

  it should "exists" in {
    val bool: Boolean = white.pieceInHand.exists(Ou)
    bool should equal(true)
  }
  it should "addPiece" in {
    val coordinate = Coordinate(1, 1)
    val piece = Hu
    val game: Game = white.addPiece(piece)(coordinate)(boardState)
    val space: Space = game.board.boardState(coordinate.x)(coordinate.y)
    space.get(white) should equal(Hu)
  }
  it should "movePiece" in {
    val moveBeforeCoordinate = Coordinate(1, 1)
    val moveAfterCoordinate = Coordinate(2, 2)
    val piece = Hu
    val game: Option[Game] = white.movePiece(moveBeforeCoordinate)(moveAfterCoordinate)(Hu)
    game.isDefined should equal(true)
  }
}
