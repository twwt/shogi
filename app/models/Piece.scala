package models

/**
  * Created by taishi on 7/19/16.
  */
sealed abstract class Piece(val moveRange: List[RelativeCoordinate])

case object Ou extends Piece(List(RelativeCoordinate(0, 1), RelativeCoordinate(0, -1), RelativeCoordinate(-1, 0), RelativeCoordinate(1, 0), RelativeCoordinate(-1, 1), RelativeCoordinate(1, 1), RelativeCoordinate(-1, -1), RelativeCoordinate(1, -1)))

case object Hu extends Piece(List(RelativeCoordinate(0, 1), RelativeCoordinate(0, 0), RelativeCoordinate(0, 0), RelativeCoordinate(0, 0), RelativeCoordinate(0, 0), RelativeCoordinate(0, 0), RelativeCoordinate(0, 0), RelativeCoordinate(0, 0)))

case object Kaku extends Piece(List(RelativeCoordinate(0, 0), RelativeCoordinate(0, 0), RelativeCoordinate(0, 0), RelativeCoordinate(0, 0), RelativeCoordinate(-8, 8), RelativeCoordinate(8, 8), RelativeCoordinate(-8, -8), RelativeCoordinate(8, -8)))

//CoordicateがあるのになぜRelativeCoordinate？Coordinateは絶対的な座標、RelativeCoordinate
//なぜMoveRangeを作ったのか？productIteratorでloopさせたかったから

case class RelativeCoordinate(x: Int, y: Int)

//pieceInHandの引数にwhitePieceとblackPiece作ったが２にするとループしにくいし２つである必要はないので１つにまとめる。
//case class PieceInHand(whitePiece: Map[White, List[Piece]], blackPiece: Map[Black, List[Piece]])
case class PieceInHand(pieces: Map[Player, Piece])

//
//case class RelativeCoordinatex: Int, y: Int)
//
//case class RelativeCoordinate(x: Int, y: Int)
//
//case class RelativeCoordinate(x: Int, y: Int)
//
//case class RelativeCoordinate(x: Int, y: Int)
//
//case class RelativeCoordinate(x: Int, y: Int)
//
//case class RelativeCoordinate(x: Int, y: Int)
//
//case class RelativeCoordinate(x: Int, y: Int)
//
//case class RelativeCoordinate(x: Int, y: Int)
//
//sealed abstract class Piece(val movement: MoveRange)
//
//case object Ou extends Piece(RelativeCoordinate0, 1), RelativeCoordinate(0, -1), RelativeCoordinate(-1, 0), RelativeCoordinate(1, 0), RelativeCoordinate(-1, 1), RelativeCoordinate(1, 1), RelativeCoordinate(-1, -1), RelativeCoordinate(1, -1)))
//
//case object Hu extends Piece(RelativeCoordinate0, 1), RelativeCoordinate(0, 0), RelativeCoordinate(0, 0), RelativeCoordinate(0, 0), RelativeCoordinate(0, 0), RelativeCoordinate(0, 0), RelativeCoordinate(0, 0), RelativeCoordinate(0, 0)))
//
//case object Kaku extends Piece(RelativeCoordinate0, 0), RelativeCoordinate(0, 0), RelativeCoordinate(0, 0), RelativeCoordinate(0, 0), RelativeCoordinate(-8, 8), RelativeCoordinate(8, 8), RelativeCoordinate(-8, -8), RelativeCoordinate(8, -8)))
//
