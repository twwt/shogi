import models._
import org.scalatest.FlatSpec
import org.scalatest.Matchers
import shapeless._
import shapeless.Sized

class BoardSpec extends FlatSpec with Matchers {

  val hu1 = Hu
  val ou = Ou
  val blackPlayer = new Black
  val whitePlayer = new White
  val space1: Option[Map[Player, Ou.type]] =
    Some(Map(whitePlayer -> Ou))

  val space2: Option[Map[Player, Hu.type]] =
    Some(Map(blackPlayer -> Hu))

  val boardY: Sized[List[Option[Map[Player, Piece]]], nat._9] =
    Sized[List](space1, None, None, None, None, None, space2, None, None)

  val boardY2: Sized[List[Option[Map[Player, Piece]]], nat._9] =
    Sized[List](None, None, None, None, None, None, None, None, None)

  val boardY3: Sized[List[Option[Map[Player, Piece]]], nat._9] =
    Sized[List](None, None, space1, None, None, None, None, None, None)

  val boardState: Sized[List[Sized[List[Option[Map[Player, Piece]]], nat._9]], nat._9] =
    Sized[List](boardY, boardY2, boardY3, boardY2, boardY2, boardY2, boardY2, boardY2, boardY2)
  val board: Board = new Board(boardState)
  implicit val nowBoardState = board.boardState

  behavior of "Board"

  it should "findPiece" in {
    val x = board.intToAxisLenght(1)
    val y = board.intToAxisLenght(1)
    val coordinate = Coordinate(x, y)
    val ou: Option[Piece] = board.findPiece(coordinate, whitePlayer)
    ou should equal(Some(Ou))
  }

  it should "findSpace" in {
    val x = board.intToAxisLenght(1)
    val y = board.intToAxisLenght(1)
    val coordinate = Coordinate(x, y)
    val space: Option[Map[Player, Piece]] = board.findSpace(coordinate)
    space should equal(Some(Map(whitePlayer -> Ou)))
  }

  it should "isMoveDirection" in {
    val ouMoveRange: List[RelativeCoordinate] = Ou.moveRange.filter(board.isMoveDirection)
    ouMoveRange should equal(List(RelativeCoordinate(r_0, r_1), RelativeCoordinate(r_0, r__1), RelativeCoordinate(r__1, r_0), RelativeCoordinate(r_1, r_0), RelativeCoordinate(r__1, r_1), RelativeCoordinate(r_1, r_1), RelativeCoordinate(r__1, r__1), RelativeCoordinate(r_1, r__1)))
    val huMoveRange: List[RelativeCoordinate] = Hu.moveRange.filter(board.isMoveDirection)
    huMoveRange should equal(List(RelativeCoordinate(r_0, r_1)))
  }

  it should "moveRange" in {
    val result = board.moveRange(Kaku.moveRange, Coordinate(_5, _5), whitePlayer)
    result should equal(List(Coordinate(_1, _1), Coordinate(_3, _3), Coordinate(_4, _4), Coordinate(_5, _5), Coordinate(_6, _6), Coordinate(_7, _7), Coordinate(_8, _8), Coordinate(_9, _9)))
//    val result2 = board.moveRange(Hu.moveRange, Coordinate(_5, _5), whitePlayer)
//    result2 should equal(List(Coordinate(_5, _4)))
  }

  //  it should "coordinateToDirection" in {
  //    val result = board.coordinateToDirection(Coordinate(_2,))
  //    result should equal(List(Coordinate(_2, _2), Coordinate(_3, _3), Coordinate(_4, _4), Coordinate(_5, _5), Coordinate(_6, _6), Coordinate(_7, _7), Coordinate(_8, _8), Coordinate(_9, _9)))
  //  }

//  it should "move" in {
//    val kakuMoveRange = board.moveRange(Kaku.moveRange, Coordinate(_2, _2), whitePlayer)
//    board.directionMap(Coordinate(_2, _2), kakuMoveRange)
//    kakuMoveRange should equal(List(Coordinate(_2, _2), Coordinate(_3, _3), Coordinate(_4, _4), Coordinate(_5, _5), Coordinate(_6, _6), Coordinate(_7, _7), Coordinate(_8, _8), Coordinate(_9, _9)))
//    val result = kakuMoveRange.map(board.move(_, Coordinate(_1, _1), whitePlayer))
//    result should equal(List(Coordinate(_2, _2)))
//  }

  it should "coordinateDistance" in {
    board.coordinateDistance(Coordinate(_5, _5), Coordinate(_1, _1))
    board.coordinateDistance(Coordinate(_9, _9), Coordinate(_1, _1))
  }

}