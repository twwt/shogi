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
  val space1: Option[Map[Player, Piece]] =
    Some(Map(whitePlayer, Ou))

  val space2: Option[Map[Player, Piece]] =
    Some(Map(blackPlayer, Hu))

  val boardY: Sized[List[Option[Map[Player, Piece]]], nat._9] =
    Sized[List](space1, None, None, None, None, None, space2, None, None)

  val boardY2: Sized[List[Option[Map[Player, Piece]]], nat._9] =
    Sized[List](None, None, None, None, None, None, None, None, None)

  val boardState: Sized[List[Sized[List[Option[Map[Player, Piece]]], nat._9]], nat._9] =
    Sized[List](boardY, boardY2, boardY2, boardY2, boardY2, boardY2, boardY2, boardY2, boardY2)
  val board: Board = new Board(boardState)
  implicit val nowBoardState = board.boardState

  behavior of "Board"

  it should "findPiece" in {
    val x = board.intToAxisLenght(1)
    val y = board.intToAxisLenght(1)
    val coodinate = Coordinate(x, y)
    val ou: Option[Map[Player, Piece]] = board.findPiece(coodinate)
    ou.get should equal(Ou)
  }

  //指定ポイントから動くことのできる座標のリストを返す関数

}