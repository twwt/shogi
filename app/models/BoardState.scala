package models

/**
  * Created by taishi on 8/14/16.
  */

sealed trait Space

case class EnemySpace(piece: Option[Piece]) extends Space

case class MySpace(piece: Option[Piece]) extends Space

case class FreeSpace(piece: Option[Piece]) extends Space

case class X(space: List[Space])

case class BoardState(state: List[X])