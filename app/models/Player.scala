package models

/**
  * Created by taishi on 7/19/16.
  */

sealed trait Player {
  def movePiece(coordinate: Coordinate, player: Player): Unit = {

  }
}

case object Black extends Player

case object White extends Player
