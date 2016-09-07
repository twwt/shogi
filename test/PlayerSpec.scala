import models.{Coordinate, _}
import org.scalatest.{FlatSpec, Matchers}
import scalaz._
import Scalaz._

/**
  * Created by taishi on 8/14/16.
  */
class PlayerSpec extends FlatSpec with Matchers {
  behavior of "Player"

  val white = White(Nil)
  val black = Black(Nil)
  val freeSpace = new FreeSpace(None)
  val ou = new PieceSpace(Some(Ou), white.some)
  val ou2 = new PieceSpace(Some(Ou), black.some)
  val kaku = new PieceSpace(Some(Kaku), white.some)
  val keima = new PieceSpace(Some(Keima), white.some)
  val boardState: Board =
    Board(BoardState(Map(4 -> X(Map(-4 -> ou, -3 -> ou2, -2 -> freeSpace, -1 -> freeSpace, 0 -> freeSpace, 1 -> freeSpace, 2 -> freeSpace, 3 -> freeSpace, 4 -> keima))
      , 3 -> X(Map(-4 -> freeSpace, -3 -> freeSpace, -2 -> freeSpace, -1 -> freeSpace, 0 -> freeSpace, 1 -> freeSpace, 2 -> freeSpace, 3 -> freeSpace, 4 -> keima))
      , 2 -> X(Map(-4 -> freeSpace, -3 -> freeSpace, -2 -> freeSpace, -1 -> freeSpace, 0 -> freeSpace, 1 -> freeSpace, 2 -> freeSpace, 3 -> keima, 4 -> keima))
      , 1 -> X(Map(-4 -> freeSpace, -3 -> freeSpace, -2 -> freeSpace, -1 -> freeSpace, 0 -> freeSpace, 1 -> freeSpace, 2 -> freeSpace, 3 -> freeSpace, 4 -> keima))
      , 0 -> X(Map(-4 -> freeSpace, -3 -> freeSpace, -2 -> freeSpace, -1 -> freeSpace, 0 -> freeSpace, 1 -> freeSpace, 2 -> freeSpace, 3 -> freeSpace, 4 -> keima))
      , -1 -> X(Map(-4 -> freeSpace, -3 -> freeSpace, -2 -> freeSpace, -1 -> freeSpace, 0 -> freeSpace, 1 -> freeSpace, 2 -> freeSpace, 3 -> freeSpace, 4 -> keima))
      , -2 -> X(Map(-4 -> freeSpace, -3 -> freeSpace, -2 -> freeSpace, -1 -> freeSpace, 0 -> freeSpace, 1 -> freeSpace, 2 -> freeSpace, 3 -> freeSpace, 4 -> keima))
      , -3 -> X(Map(-4 -> freeSpace, -3 -> freeSpace, -2 -> freeSpace, -1 -> freeSpace, 0 -> freeSpace, 1 -> freeSpace, 2 -> freeSpace, 3 -> freeSpace, 4 -> keima))
      , -4 -> X(Map(-4 -> freeSpace, -3 -> freeSpace, -2 -> freeSpace, -1 -> freeSpace, 0 -> freeSpace, 1 -> freeSpace, 2 -> freeSpace, 3 -> freeSpace, 4 -> keima)))))
  val game = new Game(boardState, white, black)
  val c = Coordinate(1, 1)

  it should "mostFarMoveRange" in {
    val result = white.mostFarMoveRange(Coordinate(1, 1))(Kaku)
    result should equal(List(CanNotMove(Set()), CanNotMove(Set()), CanNotMove(Set()), CanNotMove(Set()), UpLeft(Set(Coordinate(0, 2), Coordinate(-2, 4), Coordinate(-1, 3))), UpRight(Set(Coordinate(4, 4), Coordinate(2, 2), Coordinate(3, 3))), DownLeft(Set(Coordinate(-1, -1), Coordinate(0, 0), Coordinate(-3, -3), Coordinate(-4, -4), Coordinate(-2, -2))), DownRight(Set(Coordinate(4, -2), Coordinate(2, 0), Coordinate(3, -1)))))
  }
  it should "directionSort" in {
    white.mostFarMoveRange(c)(Kaku).map(white.distanceSort(c)(_)) should equal(List(List(), List(), List(), List(), List(Coordinate(0, 2), Coordinate(-1, 3), Coordinate(-2, 4)), List(Coordinate(2, 2), Coordinate(3, 3), Coordinate(4, 4)), List(Coordinate(0, 0), Coordinate(-1, -1), Coordinate(-2, -2), Coordinate(-3, -3), Coordinate(-4, -4)), List(Coordinate(2, 0), Coordinate(3, -1), Coordinate(4, -2))))
  }

  it should "canMoveRangeIndex" in {
    val sortedCoordinates: List[List[Coordinate]] = white.mostFarMoveRange(c)(Kaku).map(white.distanceSort(c)(_))
    sortedCoordinates.map(white.moveRangeIndex(boardState)(_)) should equal(List(None, None, None, None, Some(List(Coordinate(0, 2), Coordinate(-1, 3))), Some(List(Coordinate(2, 2), Coordinate(3, 3))), Some(List(Coordinate(0, 0), Coordinate(-1, -1), Coordinate(-2, -2), Coordinate(-3, -3), Coordinate(-4, -4))), Some(List(Coordinate(2, 0), Coordinate(3, -1), Coordinate(4, -2)))))
  }

  it should "canMoveRange" in {
    white.canMoveRange(c)(boardState)(Kaku) should equal(List(UpLeft(Set(Coordinate(0, 2), Coordinate(-1, 3))), UpRight(Set(Coordinate(2, 2), Coordinate(3, 3))), DownLeft(Set(Coordinate(-1, -1), Coordinate(0, 0), Coordinate(-3, -3), Coordinate(-4, -4), Coordinate(-2, -2))), DownRight(Set(Coordinate(2, 0), Coordinate(3, -1), Coordinate(4, -2)))))
  }

  it should "exchange freeSpace" in {
    val result: Game = boardState.exchange(Coordinate(1, 1), Coordinate(2, 2), Kaku, white, game)
    result.boardState.state.board(1).x(1) should equal(Space(None, None))
  }
  it should "exchange afterMove" in {
    val result: Game = boardState.exchange(Coordinate(1, 1), Coordinate(2, 2), Kaku, white, game)
    result.boardState.state.board(2).x(2) should equal(Space(Some(Kaku), white.some))
  }
  it should "exchange pieceInHand" in {
    val result: Game = boardState.exchange(Coordinate(1, 1), Coordinate(4, 4), Kaku, black, game)
    result.turnPlayer.newPieceInHand should equal(List(Keima))
  }
  it should "isMovePiece Fail" in {
    val result = white.isMovePiece(boardState, Coordinate(4, -4), Coordinate(3, -2))
    result should equal(false)
  }
  it should "isMovePiece Success" in {
    val result = white.isMovePiece(boardState, Coordinate(4, -4), Coordinate(3, -3))
    result should equal(true)
  }
  it should "isMovePiece Fail 2" in {
    //相手ユーザーの駒を動かそうとしてみる
    val result = white.isMovePiece(boardState, Coordinate(4, -3), Coordinate(3, -3))
    result should equal(false)
  }
}
