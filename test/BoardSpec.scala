import models._
import org.scalatest.FlatSpec
import org.scalatest.Matchers

class BoardSpec extends FlatSpec with Matchers {

  val hu1 = Hu
  val ou = Ou
  val blackPlayer = new Black
  val whitePlayer = new White
  val onBoardPiece = Set(OnBoardPiece(blackPlayer, Ou, Coordinate(5, 9)), OnBoardPiece(whitePlayer, Ou, Coordinate(5, 1)), OnBoardPiece(whitePlayer, Hu, Coordinate(5, 3)), OnBoardPiece(whitePlayer, Ou, Coordinate(5, 7)))
  val board = new Board(onBoardPiece)

  behavior of "Board"

  it should "findPiece" in {
    val x = 5
    val y = 9
    val coodinate = Coordinate(x, y)
    val ou: Option[Piece] = board.findPiece(coodinate)
    ou.get should equal(Ou)
  }

  //指定ポイントから動くことのできる座標のリストを返す関数
  
}