//package models
//
///** * Created by taishi on 7/19/16.  */
//case class Coordinate(x: AxisLength, y: AxisLength) {
//  def plus1(direction: Direction, xCoordinate: Coordinate, yCoordinate: Coordinate): Coordinate
//
//  def minus1(direction: Direction, x: Coordinate, y: Coordinate): Coordinate
//}
//
//sealed abstract class AxisLength(val length: Int)
//
//case object _0 extends AxisLength(0)
//
//case object _1 extends AxisLength(1)
//
//case object _2 extends AxisLength(2)
//
//case object _3 extends AxisLength(3)
//
//case object _4 extends AxisLength(4)
//
//case object __1 extends AxisLength(-1)
//
//case object __2 extends AxisLength(-2)
//
//case object __3 extends AxisLength(-3)
//
//case object __4 extends AxisLength(-4)
//
//class Board(val boardState: List[List[Coordinate]])