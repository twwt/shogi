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
  val y: Y = Map(-4 -> ouSpace, -3 -> None, -2 -> None, -1 -> None, 0 -> None, 1 -> None, 2 -> None, 3 -> None, 4 -> None)
  val yNone: Y = Map(-4 -> None, -3 -> None, -2 -> None, -1 -> None, 0 -> None, 1 -> None, 2 -> None, 3 -> None, 4 -> None)
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
    val space: Space = game.board.board(coordinate.x)(coordinate.y)
    space.get(white) should equal(Hu)
  }
  it should "movePiece" in {
    val game = white.movePiece(board)(Coordinate(0, 0))(Coordinate(1, 1))(Ou)

    //    val moveBeforeCoordinate = Coordinate(1, 1)
    //    val moveAfterCoordinate = Coordinate(2, 2)
    //    val piece = Hu
    //    val game: Option[Game] = white.movePiece(moveBeforeCoordinate)(moveAfterCoordinate)(Hu)
    game.isDefined should equal(true)
  }
}
