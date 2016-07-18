import org.scalatest.{FlatSpec, Matchers}

class PlayerSpec extends FlatSpec with Matchers {

  behavior of "Player"

  val parent = new Parentt()
  val game = new Game()
  it should "movePiece" in {
    val newBoard: Board = game.parent.movePiece(game.board)
  }

  it should "takePiece" in {
    val piece: Piece = game.parent.changePlayerPiece(piece)
  }

  it should "addPiece" in {
    val newBoard:Board = game.parent.addPiece(game.board)
  }
}