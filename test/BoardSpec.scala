import models._
import org.scalatest.FlatSpec
import org.scalatest.Matchers

class BoardSpec extends FlatSpec with Matchers {

  val hu1 = Hu
  val ou = Ou
  val player1 = Black
  val player2 = White
  val onBoardPiece = Set(OnBoardPiece(player1,Ou,Coordinate(5,9)),OnBoardPiece(player2,Ou,Coordinate(5,1)))
  val board = new Board(onBoardPiece)

  behavior of "Board"

  it should "findPiece" in {
    val x = 5
    val y = 9
    val coodinate = Coordinate(x, y)
    val ou: Option[Piece] = board.findPiece(coodinate)
    ou.get should equal(Ou)
  }

  //  it should "toCoordinate" in {
  //    val hu: Piece = Hu
  //    val huMoveRange: List[Coordinate] = board.toCoordicate(hu)
  //    huMoveRange.foreach {
  //      _.x should equal(0)
  //      _.y should equal(1)
  //    }
  //  }

  //指定ポイントから動くことのできる座標のリストを返す関数
  it should "findMoveCoordinates" in {
    val x = 4
    val y = 7
    val atPoint: Coordinate = Coordinate(x, y)
    val huMoveRange = Hu.movement
    val canMoveCoordinates: List[Coordinate] = board.findMoveCoordinates(atPoint, huMoveRange)
    canMoveCoordinates.foreach { c =>
      c.x should equal(4)
      c.y should equal(6)
    }
  }

  it should "searchOpponentPieceOrFreeSpace" in {
    val succeesCase1 = board.searchOpponentPieceOrFreeSpace(Coordinate(5,9))
    val succeesCase2 = board.searchOpponentPieceOrFreeSpace(Coordinate(5,1))
    val failCase = board.searchOpponentPieceOrFreeSpace(Coordinate(2,9))
    succeesCase1.isDefined should equal(true)
    succeesCase2.isDefined should equal(true)
    failCase.isDefined should equal(false)
  }

//  //todo これテストになっているのか？
//  //様々なテストケースを用意しなければ行けない、これは結合テスト時にやるテストかもしれない
//  it should "VerticalAndHorizontalInShortestMovePoints" in {
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