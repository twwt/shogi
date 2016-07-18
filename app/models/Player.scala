package models

/**
  * Created by taishi on 7/19/16.
  */

sealed trait Player {
}

case object Black extends Player
case object White extends Player
