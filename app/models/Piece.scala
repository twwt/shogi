package models

/**
  * Created by taishi on 7/19/16.
  */

sealed abstract class Direction(x: RelativeRange, y: RelativeRange)

case class Up(x: RelativeRange, y: RelativeRange) extends Direction(x, y)

case class Down(x: RelativeRange, y: RelativeRange) extends Direction(x, y)

case class Left(x: RelativeRange, y: RelativeRange) extends Direction(x, y)

case class Right(x: RelativeRange, y: RelativeRange) extends Direction(x, y)

case class UpLeft(x: RelativeRange, y: RelativeRange) extends Direction(x, y)

case class UpRight(x: RelativeRange, y: RelativeRange) extends Direction(x, y)

case class DownLeft(x: RelativeRange, y: RelativeRange) extends Direction(x, y)

case class DownRight(x: RelativeRange, y: RelativeRange) extends Direction(x, y)

case class Around(up: Up, down: Down, left: Left, right: Right, upLeft: UpLeft, upRight: UpRight, downLeft: DownLeft, downRight: DownRight)

case object r_0 extends RelativeRange(List(0))

case object r_1 extends RelativeRange((1 to 1).toList)

case object r_8 extends RelativeRange((1 to 8).toList)

case object r__1 extends RelativeRange((-1 to -1).toList)

case object r__8 extends RelativeRange((-8 to -1).toList)

case class RelativeRange(range: List[Int])

case class Space(space: Option[Map[Player, Piece]])

sealed abstract class Piece(val moveRange: Around)

case object Ou extends Piece(Around(Up(r_0, r_1), Down(r_0, r__1), Left(r__1, r_0), Right(r_1, r_0), UpLeft(r__1, r_1), UpRight(r_1, r_1), DownLeft(r__1, r__1), DownRight(r_1, r_1)))

case object Hu extends Piece(Around(Up(r_0, r_1), Down(r_0, r_0), Left(r_0, r_0), Right(r_0, r_0), UpLeft(r_0, r_0), UpRight(r_0, r_0), DownLeft(r_0, r_0), DownRight(r_0, r_0)))

case object Kaku extends Piece(Around(Up(r_0, r_0), Down(r_0, r_0), Left(r_0, r_0), Right(r_0, r_0), UpLeft(r__8, r__8), UpRight(r_8, r__8), DownLeft(r__8, r_8), DownRight(r_8, r_8)))









