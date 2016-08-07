<<<<<<< HEAD
import models.Board.{X, Y, Space, BoardState}
import models._
import org.scalatest.{Matchers, FlatSpec}

/**
  * Created by taishi on 8/4/16.
  */
class PlayerSpec extends FlatSpec with Matchers {
  behavior of "Player"

  val white = White(List(Ou))
  val black = Black(Nil)

  val ouSpace: Space = Some(Map(white -> Ou))
  val y: Y = Map(-4 -> ouSpace, -3 -> None, -2 -> None, -1 -> None, 0 -> None, 1 -> None, 2 -> None, 3 -> None, 4 -> None)
  val yNone: Y = Map(-4 -> None, -3 -> None, -2 -> None, -1 -> None, 0 -> None, 1 -> None, 2 -> None, 3 -> None, 4 -> None)
  val boardState: BoardState = Map(-4 -> y, -3 -> yNone, -2 -> yNone, -1 -> yNone, 0 -> yNone, 1 -> yNone, 2 -> yNone, 3 -> yNone, 4 -> yNone)
  val board = Board(boardState)

  it should "exists" in {
    val bool: Boolean = white.pieceInHand.exists(Ou)
    bool should equal(true)
  }
  it should "addPiece" in {
    val coordinate = Coordinate(1, 1)
    val piece = Hu
    val game: Game = white.addPiece(piece)(coordinate)(boardState)
    val space: Space = game.board.board(coordinate.x)(coordinate.y)
    space.get(white) should equal(Hu)
  }
  it should "movePiece" in {
    val game = white.movePiece(board)(Coordinate(0, 0))(Coordinate(1, 1))(Ou)

    //    val moveBeforeCoordinate = Coordinate(1, 1)
    //    val moveAfterCoordinate = Coordinate(2, 2)
    //    val piece = Hu
    //    val game: Option[Game] = white.movePiece(moveBeforeCoordinate)(moveAfterCoordinate)(Hu)
    game.isDefined should equal(true)
  }
}
=======
import models._
import org.scalatest.{FlatSpec, Matchers}

class PlayerSpec extends FlatSpec with Matchers {

  behavior of "Player"

  val blackPlayer = new Black
  val whitePlayer = new White
  val onBoardPiece = Set(OnBoardPiece(blackPlayer, Ou, Coordinate(5, 9)), OnBoardPiece(whitePlayer, Ou, Coordinate(5, 1)), OnBoardPiece(whitePlayer, Hu, Coordinate(5, 3)), OnBoardPiece(whitePlayer, Ou, Coordinate(5, 7)))
  val board = new Board(onBoardPiece)
  val pieceInHand: PieceInHand = PieceInHand(Map(whitePlayer -> Ou, blackPlayer -> Hu))

  val game: Game = new Game(whitePlayer, board, pieceInHand)
  it should "movePiece" in {
    val newBoard: Option[Game] = whitePlayer.movePiece(game, Coordinate(5, 9), Coordinate(4, 8))
    newBoard.isDefined should equal(true)
    newBoard.map(_.board.findPiece(Coordinate(4, 8)).get).map(_ should equal(Ou))

  }

  //  it should "takePiece" in {
  //    val board: Option[Board] = game.parent.changePlayerPiece(piece)
  //  }
  //
  it should "takePiece" in {
    val newBoard = blackPlayer.takePiece(Coordinate(5, 3), game)
    newBoard match {
      case Some(board) => board.pieceInHand.pieces(blackPlayer) should equal(Hu)
      case None => println(1)
    }
  }

  //  it should "addPiece" in {
  //    val newBoard: Option[Board] = whitePlayer.addPiece(board, Hu, Coordinate(1, 4))
  //    newBoard.isDefined should equal(true)
  //    newBoard.map(_.findPiece(Coordinate(1, 4)).get).map(_ should equal(Hu))
  //  }
}
>>>>>>> 7f9a1320c96bd4909156b39f3fceb5e8444f50d5
