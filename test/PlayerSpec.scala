import models.{Coordinate, _}
import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by taishi on 8/14/16.
  */
class PlayerSpec extends FlatSpec with Matchers {
  behavior of "Player"

  val white = White(Nil)
  val black = Black(Nil)
  val freeSpace = new FreeSpace(None)
  val ou = new PieceSpace(Some(Ou), white)
  val keima = new PieceSpace(Some(Keima), white)
  val boardState: Board =
    Board(Map(4 -> X(Map(-4 -> ou, -3 -> freeSpace, -2 -> freeSpace, -1 -> freeSpace, 0 -> freeSpace, 1 -> freeSpace, 2 -> freeSpace, 3 -> freeSpace, 4 -> keima))
      , 3 -> X(Map(-4 -> freeSpace, -3 -> freeSpace, -2 -> freeSpace, -1 -> freeSpace, 0 -> freeSpace, 1 -> freeSpace, 2 -> freeSpace, 3 -> freeSpace, 4 -> keima))
      , 2 -> X(Map(-4 -> freeSpace, -3 -> freeSpace, -2 -> freeSpace, -1 -> freeSpace, 0 -> freeSpace, 1 -> freeSpace, 2 -> freeSpace, 3 -> keima, 4 -> keima))
      , 1 -> X(Map(-4 -> freeSpace, -3 -> freeSpace, -2 -> freeSpace, -1 -> freeSpace, 0 -> freeSpace, 1 -> freeSpace, 2 -> freeSpace, 3 -> freeSpace, 4 -> keima))
      , 0 -> X(Map(-4 -> freeSpace, -3 -> freeSpace, -2 -> freeSpace, -1 -> freeSpace, 0 -> freeSpace, 1 -> freeSpace, 2 -> freeSpace, 3 -> freeSpace, 4 -> keima))
      , -1 -> X(Map(-4 -> freeSpace, -3 -> freeSpace, -2 -> freeSpace, -1 -> freeSpace, 0 -> freeSpace, 1 -> freeSpace, 2 -> freeSpace, 3 -> freeSpace, 4 -> keima))
      , -2 -> X(Map(-4 -> freeSpace, -3 -> freeSpace, -2 -> freeSpace, -1 -> freeSpace, 0 -> freeSpace, 1 -> freeSpace, 2 -> freeSpace, 3 -> freeSpace, 4 -> keima))
      , -3 -> X(Map(-4 -> freeSpace, -3 -> freeSpace, -2 -> freeSpace, -1 -> freeSpace, 0 -> freeSpace, 1 -> freeSpace, 2 -> freeSpace, 3 -> freeSpace, 4 -> keima))
      , -4 -> X(Map(-4 -> freeSpace, -3 -> freeSpace, -2 -> freeSpace, -1 -> freeSpace, 0 -> freeSpace, 1 -> freeSpace, 2 -> freeSpace, 3 -> freeSpace, 4 -> keima))))
  val game = new Game(boardState, white, black)
  val c = Coordinate(1, 1)

  it should "mostMoveRange" in {
    white.mostMoveRange(Coordinate(1, 1))(Kaku)
  }
  it should "directionSort" in {
    white.mostMoveRange(c)(Kaku).map(white.distanceSort(c)(_))
  }

  it should "canMoveRangeIndex" in {
    val sortedCoordinates: List[List[Coordinate]] = white.mostMoveRange(c)(Kaku).map(white.distanceSort(c)(_))
    sortedCoordinates.map(white.canMoveRangeIndex(boardState)(_))
  }

  it should "canMoveRange" in {
    println(white.canMoveRange(c)(boardState, Kaku))
  }

  it should "exchange" in {
    val exchangeCoordinate: Coordinate = white.canMoveRange(c)(boardState, Kaku).head.moveRange.head
    val result: Space = boardState.exchange(c, Kaku)
    result.piece should equal(Some(Kaku))
  }
}
