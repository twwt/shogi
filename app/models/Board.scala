package models

/**
  * Created by taishi on 7/19/16.
  */

case class Coordinate(x: Int, y: Int)

case class OnBoardPiece(player: Player, piece: Piece, coordinate: Coordinate)

class Board(val pieces: List[OnBoardPiece]) {
  
}
