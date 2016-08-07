package models

<<<<<<< HEAD
import models.Direction._
import shapeless.{Sized, HNil}

//
///**
//  * Created by taishi on 7/19/16.
//  */
//
//case class Direction(x: RelativeRange, y: RelativeRange) {
//  def apply(atPoint:Coordinate) = {
//  }
//}
//
//object Direction {
//  type Space = Option[Map[Player, Piece]]
//
//  def apply(x: RelativeRange, y: RelativeRange) = {
//    x match {
//      case r_0 if
//    }
//  }
//
//  def go(count:Int,endCount:Option[Int],coordinates: List[Coordinate]) = {
//
//    endCount match {
//      case Some(endCount) if endCount == count => coordinate
//      case Some(endCount) if endCount != count =>
//
//        go(count + 1,Some(endCount + 1),)
//
//    }
//  }
//}
//
//case class Coordinate()
//
//case object r_0 extends RelativeRange(List(0))  
//case object r_1 extends RelativeRange((1 to 1).toList) 
//case object r_8 extends RelativeRange((1 to 8).toList)  
//case object r__1 extends RelativeRange((-1 to -1).toList)  
//case object r__8 extends RelativeRange((-8 to -1).toList) 
//
//
//
//case class Up(x: RelativeRange, y: RelativeRange) extends Direction(x, y)
//
//case class Down(x: RelativeRange, y: RelativeRange) extends Direction(x, y)
//
//case class Left(x: RelativeRange, y: RelativeRange) extends Direction(x, y)
//
//case class Right(x: RelativeRange, y: RelativeRange) extends Direction(x, y)
//
//case class UpLeft(x: RelativeRange, y: RelativeRange) extends Direction(x, y)
//
//case class UpRight(x: RelativeRange, y: RelativeRange) extends Direction(x, y)
//
//case class DownLeft(x: RelativeRange, y: RelativeRange) extends Direction(x, y)
//
//case class DownRight(x: RelativeRange, y: RelativeRange) extends Direction(x, y)
//
//case class Around(up: Up, down: Down, left: Left, right: Right, upLeft: UpLeft, upRight: UpRight, downLeft: DownLeft, downRight: DownRight)
//
//case object r_0 extends RelativeRange(List(0))
//
//case object r_1 extends RelativeRange((1 to 1).toList)
//
//case object r_8 extends RelativeRange((1 to 8).toList)
//
//case object r__1 extends RelativeRange((-1 to -1).toList)
//
//case object r__8 extends RelativeRange((-8 to -1).toList)
//
//case class RelativeRange(range: List[Int])
//
//case class Space(space: Option[Map[Player, Piece]])
//

case class Piece(val aroundMoveRange: AroundMoveRange) {
  //  def searchMoveRange = {
  //    val plus1All = everywhere(plus1)
  //    println(plus1All(moveRange))
  //  }
}

//object Ou extends Piece(Up(Near) :: Down(Near) :: Left(Near) :: Right(Near) :: UpLeft(Near, Near) :: UpRight(Near, Near) :: DownLeft(Near, Near) :: DownRight(Near, Near) :: HNil)
//
//object Hu extends Piece(Up(Near) :: Down(CanNotMove) :: Left(CanNotMove) :: Right(CanNotMove) :: UpLeft(CanNotMove, CanNotMove) :: UpRight(CanNotMove, CanNotMove) :: DownLeft(CanNotMove, CanNotMove) :: DownRight(CanNotMove, CanNotMove) :: HNil)

object Ou extends Piece(List(Up(Near), Down(Near), Left(Near), Right(Near), UpLeft(Near, Near), UpRight(Near, Near), DownLeft(Near, Near), DownRight(Near, Near)))
object Hu extends Piece(List(Up(Near), Down(CanNotMove), Left(CanNotMove), Right(CanNotMove), UpLeft(CanNotMove, CanNotMove), UpRight(CanNotMove, CanNotMove), DownLeft(CanNotMove, CanNotMove), DownRight(CanNotMove, CanNotMove)))

//
//case object Hu extends Piece(Around(Up(r_0, r_1), Down(r_0, r_0), Left(r_0, r_0), Right(r_0, r_0), UpLeft(r_0, r_0), UpRight(r_0, r_0), DownLeft(r_0, r_0), DownRight(r_0, r_0)))
//
//case object Kaku extends Piece(Around(Up(r_0, r_0), Down(r_0, r_0), Left(r_0, r_0), Right(r_0, r_0), UpLeft(r__8, r__8), UpRight(r_8, r__8), DownLeft(r__8, r_8), DownRight(r_8, r_8)))
//
//
//
//
//
//
//
//
//
=======
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
>>>>>>> 7f9a1320c96bd4909156b39f3fceb5e8444f50d5
//
