import models.{Coordinate, _}
import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by taishi on 8/14/16.
  */
class PlayerSpec extends FlatSpec with Matchers {
  behavior of "Player"

  val white = White(Nil)
  val black = Black(Nil)
  val freeSpace = FreeSpace(None)
  val ou = PieceSpace(Some(Ou), white)
  val keima = PieceSpace(Some(Keima), white)
  val x = X(Map(-4 -> ou, -3 -> freeSpace, -2 -> freeSpace, -1 -> freeSpace, 0 -> keima, 1 -> freeSpace, 2 -> freeSpace, 3 -> freeSpace, 4 -> keima))
  val freeX = X(Map(-4 -> freeSpace, -3 -> freeSpace, -2 -> freeSpace, -1 -> freeSpace, 0 -> freeSpace, 1 -> freeSpace, 2 -> freeSpace, 3 -> freeSpace, 4 -> freeSpace))
  val boardState = Board(Map(4 -> freeX, 3 -> x, 2 -> x, 1 -> freeX, 0 -> x, -1 -> freeX, -2 -> x, -3 -> freeX, -4 -> freeX))
  val game = new Game(boardState, white, black)
  //関数をprivateにしたので一度コメントアウトにしておく
  //  it should "mostMoveRange" in {
  //    white.mostMoveRange(Coordinate(1, 1))(Kaku)
  //  }
  //  it should "directionSort" in {
  //    val c = Coordinate(1, 1)
  //    white.mostMoveRange(c)(Kaku).map(white.distanceSort(_)(c))
  //  }
  //
  it should "canMoveRangeIndex" in {
    //駒の座標は-4
    val c = Coordinate(1, 1)
    val sortedCoordinates: List[List[Coordinate]] = white.mostMoveRange(c)(Kaku).map(white.distanceSort(c)(_))
    sortedCoordinates.map(white.canMoveRangeIndex(boardState)(_))
  }
  //
  //  it should "canMoveRange" in {
  //    val c = Coordinate(1, 1)
  //    println(white.canMoveRange(c)(boardState, Kaku))
  //  }
}
