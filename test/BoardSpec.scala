import models._
import org.scalatest.FlatSpec
import org.scalatest.Matchers

class BoardSpec extends FlatSpec with Matchers {

  val hu1 = Hu
  val ou = Ou
  val blackPlayer = Black
  val whitePlayer = White
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
  it should "findMoveRange" in {
    val x = 4
    val y = 7
    val atPoint: Coordinate = Coordinate(x, y)
    val hu = Hu
    val kaku = Kaku
    val ou = Ou
    //    val canMoveCoordinatesHu: List[Coordinate] = board.findMoveCoordinates(atPoint, hu, whitePlayer)
    val canMoveCoordinatesOu: List[Coordinate] = board.findMoveRange(atPoint, ou, whitePlayer)
    //    val canMoveCoordinatesKaku: List[Coordinate] = board.findMoveCoordinates(Coordinate(5, 5), kaku, whitePlayer)
    //    canMoveCoordinatesHu.foreach { c =>
    //      c.x should equal(4)
    //      c.y should equal(6)
    //    }
    //    canMoveCoordinatesKaku
  }

  it should "searchOpponentPieceOrFreeSpace" in {
    val succeesCase1 = board.searchOpponentPieceOrFreeSpace(whitePlayer, Coordinate(5, 9))
    val succeesCase2 = board.searchOpponentPieceOrFreeSpace(blackPlayer, Coordinate(5, 1))
    val failCase1 = board.searchOpponentPieceOrFreeSpace(whitePlayer, Coordinate(5, 1))
    val failCase2 = board.searchOpponentPieceOrFreeSpace(blackPlayer, Coordinate(3, 9))
    succeesCase1 should equal(false)
    succeesCase2 should equal(false)
    failCase1 should equal(true)
    failCase2 should equal(true)
  }

  it should "isNihu" in {
    board.isNihu(Coordinate(5, 3), whitePlayer) should equal(true)
    board.isNihu(Coordinate(5, 4), whitePlayer) should equal(true)
    board.isNihu(Coordinate(6, 4), whitePlayer) should equal(true)
    board.isNihu(Coordinate(4, 8), blackPlayer) should equal(false)
  }

  it should "maxCoordinateRange" in {
    board.maxCoordinateRange(List(RelativeCoordinate(0, 1)), Coordinate(5, 5))
  }

  it should "canMoveRange" in {
    val resultCoordinate = board.canMoveRange(List(Coordinate(5, 6), Coordinate(5, 7)), blackPlayer, Coordinate(5, 5))
    resultCoordinate should equal(List(Coordinate(5,6)))
  }

  //  //todo これテストになっているのか？
  //  //様々なテストケースを用意しなければ行けない、これは結合テスト時にやるテストかもしれない
  //  it should "VerticalAndHorizontalInShortestMovePoints" in {
  //    board.findMoveCoordinates()
  //
  //    //飛車,現在地は8,8
  //    val canMovePoints: List[Coordinate] = List(Coordinate(9, 8), Coordinate(7, 8), Coordinate(6, 8), Coordinate(5, 8), Coordinate(4, 8), Coordinate(3, 8))
  //    val verticalAndHorizontalCanMovePoints = verticalAndHorizontalInShortestMovePoints(canMovePoints)
  //    verticalAndHorizontalCanMovePoints should equal(List(Coordinate(9, 8), Coordinate(7, 8), Coordinate(6, 8), Coordinate(5, 8), Coordinate(4, 8), Coordinate(3, 8)))
  //  }
  //
  //  //todo これテストになっているのか？
  //  it should " diagonalInShortestMovePoints" in {
  //    //飛車,現在地は8,8
  //    val canMovePoints: List[Coordinate] = List(Coordinate(9, 8), Coordinate(7, 8), Coordinate(6, 8), Coordinate(5, 8), Coordinate(4, 8), Coordinate(3, 8))
  //    val diagonalCanMovePoints = diagonalInShortestMovePoints(canMovePoints)
  //    diagonalCanMovePoints should equal(List(Coordinate(9, 8), Coordinate(7, 8), Coordinate(6, 8), Coordinate(5, 8), Coordinate(4, 8), Coordinate(3, 8)))
  //  }


}