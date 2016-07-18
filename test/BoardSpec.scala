import com.gargoylesoftware.htmlunit.javascript.host.geo.Coordinates
import org.scalatest.FlatSpec
import org.scalatest.Matchers

class BoardSpec extends FlatSpec with Matchers {

  val board = new Board

  behavior of "Board"

  it should "findPiece" in {
    val x = 5
    val y = 9
    val coodinate = Coordinate(x, y)
    val ou: Option[Piece] = board.findPiece(coodinate)
    ou.map(_.isInstanceOf[Ou] should equal(true))
  }

  it should "toCoordinate" in {
    val hu: Piece = Hu
    val huMoveRange: List[Coordinate] = board.toCoordicate(hu)
    huMoveRange.foreach {
      _.x should equal(0)
      _.y should equal(1)
    }
  }

  //指定ポイントから動くことのできる座標のリストを返す関数
  it should "findMoveCoordinates" in {
    val x = 4
    val y = 7
    val atPoint: Coordinate = Coordinate(x, y)
    val huMoveRange: List[Coordinate] = List(Coordinate(0, 1))
    val canMoveCoordinates: List[Coordinate] = board.findMoveCoodinates(atPoint, huMoveRange)
    canMoveCoordinates.foreach {
      _.x should equal(4)
      _.y should equal(7)
    }
  }

  it should "isOpponentOrFreeSpace" in {
    //yが7は歩の行
    val canMoveCoordinates: List[Coordinate] = List(Coordinate(4, 7), Coordinate(5, 7), Coordinate(4, 6))
    val pointStates = canMoveCoordinates
      .map(board.isOpponentPieceOrFreeSpace(_))
    pointStates.head should equal(false)
    pointStates.last should equal(true)
  }

  //todo これテストになっているのか？
  //様々なテストケースを用意しなければ行けない、これは結合テスト時にやるテストかもしれない
  it should "VerticalAndHorizontalInShortestMovePoints" in {
    //飛車,現在地は8,8
    val canMovePoints: List[Coordinate] = List(Coordinate(9, 8), Coordinate(7, 8), Coordinate(6, 8), Coordinate(5, 8), Coordinate(4, 8), Coordinate(3, 8))
    val verticalAndHorizontalCanMovePoints = verticalAndHorizontalInShortestMovePoints(canMovePoints)
    verticalAndHorizontalCanMovePoints should equal(List(Coordinate(9, 8), Coordinate(7, 8), Coordinate(6, 8), Coordinate(5, 8), Coordinate(4, 8), Coordinate(3, 8)))
  }

  //todo これテストになっているのか？
  it should " diagonalInShortestMovePoints" in {
    //飛車,現在地は8,8
    val canMovePoints: List[Coordinate] = List(Coordinate(9, 8), Coordinate(7, 8), Coordinate(6, 8), Coordinate(5, 8), Coordinate(4, 8), Coordinate(3, 8))
    val diagonalCanMovePoints = diagonalInShortestMovePoints(canMovePoints)
    diagonalCanMovePoints should equal(List(Coordinate(9, 8), Coordinate(7, 8), Coordinate(6, 8), Coordinate(5, 8), Coordinate(4, 8), Coordinate(3, 8)))
  }


}