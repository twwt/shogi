package models

/**
  * Created by taishi on 7/19/16.
  */
sealed abstract class Piece(val moveRange: List[RelativeCoordinate])

case object Ou extends Piece(List(RelativeCoordinate(r_0, r_1), RelativeCoordinate(r_0, r__1), RelativeCoordinate(r__1, r_0), RelativeCoordinate(r_1, r_0), RelativeCoordinate(r__1, r_1), RelativeCoordinate(r_1, r_1), RelativeCoordinate(r__1, r__1), RelativeCoordinate(r_1, r_1)))

case object Hu extends Piece(List(RelativeCoordinate(r_0, r_1), RelativeCoordinate(r_0, r_0), RelativeCoordinate(r_0, r_0), RelativeCoordinate(r_0, r_0), RelativeCoordinate(r_0, r_0), RelativeCoordinate(r_0, r_0), RelativeCoordinate(r_0, r_0), RelativeCoordinate(r_0, r_0)))

case object Kaku extends Piece(List(RelativeCoordinate(r_0, r_0), RelativeCoordinate(r_0, r_0), RelativeCoordinate(r_0, r_0), RelativeCoordinate(r_0, r_0), RelativeCoordinate(r__8, r_8), RelativeCoordinate(r_8, r_8), RelativeCoordinate(r__8, r__8), RelativeCoordinate(r_8, r_8)))

sealed abstract class RelativeRenge(val length: Int)

case object r_0 extends RelativeRenge(0)

case object r_1 extends RelativeRenge(1)

case object r_2 extends RelativeRenge(2)

case object r_3 extends RelativeRenge(3)

case object r_4 extends RelativeRenge(4)

case object r_5 extends RelativeRenge(5)

case object r_6 extends RelativeRenge(6)

case object r_7 extends RelativeRenge(7)

case object r_8 extends RelativeRenge(8)

case object r_9 extends RelativeRenge(9)

case object r__1 extends RelativeRenge(-1)

case object r__2 extends RelativeRenge(-2)

case object r__3 extends RelativeRenge(-3)

case object r__4 extends RelativeRenge(-4)

case object r__5 extends RelativeRenge(-5)

case object r__6 extends RelativeRenge(-6)

case object r__7 extends RelativeRenge(-7)

case object r__8 extends RelativeRenge(-8)

case object r__9 extends RelativeRenge(-9)

case class RelativeCoordinate(x: RelativeRenge, y: RelativeRenge)