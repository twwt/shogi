import models._
import org.scalatest.{FlatSpec, Matchers}

class PlayerSpec extends FlatSpec with Matchers {

  behavior of "Player"

  val blackPlayer = Black
  val whitePlayer = White
  val onBoardPiece = Set(OnBoardPiece(blackPlayer, Ou, Coordinate(5, 9)), OnBoardPiece(whitePlayer, Ou, Coordinate(5, 1)), OnBoardPiece(whitePlayer, Hu, Coordinate(5, 3)), OnBoardPiece(whitePlayer, Ou, Coordinate(5, 7)))
  val board = new Board(onBoardPiece)
  val pieceInHand = List(Ou)

  val game = new Game(whitePlayer,board,pieceInHand)
  it should "movePiece" in {
    val newBoard: Board = game.parent.movePiece(game.board)
  }

  it should "takePiece" in {
    val piece: Piece = game.parent.changePlayerPiece(piece)
  }

  it should "addPiece" in {
    val newBoard: Board = game.parent.addPiece(game.board)
  }
}