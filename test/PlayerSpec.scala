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
  val ou = WhiteSpace(Some(Ou))
  val keima = WhiteSpace(Some(Keima))
  val x = X(Map(-4 -> ou, -3 -> freeSpace, -2 -> freeSpace, -1 -> freeSpace, 0 -> keima, 1 -> freeSpace, 2 -> keima, 3 -> freeSpace, 4 -> freeSpace))
  val freeX = X(Map(-4 -> freeSpace, -3 -> freeSpace, -2 -> freeSpace, -1 -> freeSpace, 0 -> freeSpace, 1 -> freeSpace, 2 -> freeSpace, 3 -> freeSpace, 4 -> freeSpace))
  val boardState = Board(Map(-4 -> freeX, -3 -> freeX, -2 -> freeX, -1 -> freeX, 0 -> x, 1 -> freeX, 2 -> x, 3 -> freeX, 4 -> freeX))
  val game = new Game(boardState, white, black)
  it should "mostMoveRange" in {
    white.mostMoveRange(Coordinate(1, 1))(Kaku)
  }
//  it should "directionSort" in {
//    val c = Coordinate(1, 1)
//    white.mostMoveRange(c)(Kaku).map(white.distanceSort(_)(c))
//  }
//
//  it should "canMoveRangeIndex" in {
//    val c = Coordinate(1, 1)
//    val sortedCoordinates: List[List[Coordinate]] = white.mostMoveRange(c)(Kaku).map(white.distanceSort(_)(c))
//    sortedCoordinates.map(println)
////    white.mostMoveRange(c)(Kaku).map(println)
//    sortedCoordinates.map(white.canMoveRangeIndex(boardState, _)).map(println)
//  }
}
