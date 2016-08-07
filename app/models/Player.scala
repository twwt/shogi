package models

<<<<<<< HEAD
import models.Board.BoardState
import models.Board.Space

=======
import scalaz._
import Scalaz._
>>>>>>> 7f9a1320c96bd4909156b39f3fceb5e8444f50d5

/**
  * Created by taishi on 7/19/16.
  */

<<<<<<< HEAD
sealed class Player(newPieceInHand: List[Piece]) {
  type -->[A, B] = PartialFunction[A, B]

  val pieceInHand: PieceInHand = genPieceInHand(newPieceInHand)

  def movePiece(board: Board)(beforeCoordinate: Coordinate)(afterCoordinate: Coordinate)(piece: Piece): Option[Game] = {
    val canMoveRange: List[Space] = board.canMoveRange(this, beforeCoordinate, piece)
    println(canMoveRange)
    println(afterCoordinate)
    if (canMoveRange.contains(afterCoordinate)) {
      val newBoard = new Board(board.changeBoard(afterCoordinate)(Some(Map(this -> piece))))
      Some(new Game(this, newBoard, pieceInHand))
    } else None
  }

  val pf: Space --> Boolean = {
    case Some(s) if s.contains(this) => false
    case Some(s) if !s.contains(this) => true
    case None => true
  }

  def genPieceInHand(pieceInHand: List[Piece]) = {
    new PieceInHand(newPieceInHand)(this)
  }

  def addPiece(piece: Piece)(addPieceCoordinate: Coordinate)(boardState: BoardState): Game = {
    val newPieceInHand = subtractPieceInHand(piece)
    val changeAfterSpace: Space = Some(Map(this -> piece))
    val changeAfterSpaces: BoardState = boardState.mapValues(_.map {
      case (yIndex, ySpace) if yIndex == addPieceCoordinate.y => (yIndex -> changeAfterSpace)
      case (yIndex, ySpace) => (yIndex -> ySpace)
    })
    Game(this, Board(changeAfterSpaces), newPieceInHand)
  }

  def subtractPieceInHand(piece: Piece): PieceInHand = {
    val removePieceInHand = pieceInHand.pieceInHand diff List(piece)
    PieceInHand(removePieceInHand)(this)
  }

  val reversePlayer: Player = this
}

case class PieceInHand(pieceInHand: List[Piece])(player: Player) {
  def exists(piece: Piece): Boolean = {
    pieceInHand.contains(piece)
  }
}

case class Black(newPieceInHand: List[Piece]) extends Player(newPieceInHand)

case class White(newPieceInHand: List[Piece]) extends Player(newPieceInHand)
=======
sealed trait Player {
  def movePiece(game: Game, movePieceCoodinate: Coordinate, afterMoveCoordinate: Coordinate): Option[Game] = {
    val board = game.board
    val selfPlayer: Player = game.player
    val canMovePiece: Option[Boolean] =
      board.findPiece(movePieceCoodinate)
        .map(board.findMoveRange(afterMoveCoordinate, _, selfPlayer))
        .map(_.contains(afterMoveCoordinate))
    canMovePiece match {
      //        SomeはPieceがBoardに存在するということ。Booleanは動けるのか動けないのか。
      //        敵
      case Some(piece) if piece == true => takePiece(afterMoveCoordinate, game)
      //        味方
      case Some(piece) if piece == false => None
      //        何も置いてない
      case None => takePiece(afterMoveCoordinate, game)
    }
  }

  //todo privateをつける
  def takePiece(takePieceCoordinate: Coordinate, game: Game): Option[Game] = {
    val board = game.board
    //    boardから駒を消して、持ち駒に消した駒を追加
    val removePiece: Option[OnBoardPiece] = board.pieces.filter(_.coordinate == takePieceCoordinate).headOption
    val newOnBoardPiece: Option[Set[OnBoardPiece]] = removePiece.map(board.pieces - _)
    val newPieceInHand: Option[Map[Player, Piece]] = removePiece.map(p => game.pieceInHand.pieces + (game.player -> p.piece))
    if (newOnBoardPiece.isDefined && newPieceInHand.isDefined) {
      val nextPlayer: Player = game.player match {
        case _: Black => new White
        case _: White => new Black
      }
      Some(new Game(nextPlayer, new Board(newOnBoardPiece.get), PieceInHand(newPieceInHand.get)))
    } else {
      None
    }
  }
}

final class Black extends Player

final class White extends Player
>>>>>>> 7f9a1320c96bd4909156b39f3fceb5e8444f50d5
