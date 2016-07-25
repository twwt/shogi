import models._
import org.scalatest.{FlatSpec, Matchers}

class PlayerSpec extends FlatSpec with Matchers {

  behavior of "Player"

//  val blackPlayer = new Black
//  val whitePlayer = new White
//  val onBoardPiece = Set(OnBoardPiece(blackPlayer, Ou, Coordinate(5, 9)), OnBoardPiece(whitePlayer, Ou, Coordinate(5, 1)), OnBoardPiece(whitePlayer, Hu, Coordinate(5, 3)), OnBoardPiece(whitePlayer, Ou, Coordinate(5, 7)))
//  val board = new Board(onBoardPiece)
//  val pieceInHand: PieceInHand = PieceInHand(Map(whitePlayer -> Ou, blackPlayer -> Hu))
//
//  val game: Game = new Game(whitePlayer, board, pieceInHand)
//  it should "movePiece" in {
//    val newBoard: Option[Game] = whitePlayer.movePiece(game, Coordinate(5, 9), Coordinate(4, 8))
//    newBoard.isDefined should equal(true)
//    newBoard.map(_.board.findPiece(Coordinate(4, 8)).get).map(_ should equal(Ou))
//
//  }

  //  it should "takePiece" in {
  //    val board: Option[Board] = game.parent.changePlayerPiece(piece)
  //  }
  //
//  it should "takePiece" in {
//    val newBoard = blackPlayer.takePiece(Coordinate(5, 3), game)
//    newBoard match {
//      case Some(board) => board.pieceInHand.pieces(blackPlayer) should equal(Hu)
//      case None => println(1)
//    }
//  }

  //  it should "addPiece" in {
  //    val newBoard: Option[Board] = whitePlayer.addPiece(board, Hu, Coordinate(1, 4))
  //    newBoard.isDefined should equal(true)
  //    newBoard.map(_.findPiece(Coordinate(1, 4)).get).map(_ should equal(Hu))
  //  }
}