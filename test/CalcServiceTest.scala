import org.scalatest.FunSpec

class CalcServiceTest extends FunSpec {
  describe("Action of the test") {
    describe("move") {
      it("hu1 move") {
        val board = new Board()
        val x = 1
        val y = 5
;        //        Enum
        val hu1: Koma = hu1
        val shogiRule = new ShogiRule()
        val turnUser: Userr = shogiRule.getTurnUser
        //        なぜUserを渡すのか？
        //        Intは範囲が0~221345482309ぐらいまであるから0と1しか必要ないので不要。Booleanは意味的に正しくない
        //        val board: Board = hu1.move(turnUser, x, y)駒が動くより、ユーザが動かすという表現のほうが自然。
        val board = turnUser.moveAction(hu1, x, y)
        val hu1Position = board.position(hu1)
        assert(hu1Position.x === 2)
        assert(hu1Position.y === 2)
      }

      it("5 plus 5 == 10") {
        val calcService = new CalcService
        assert(calcService.add(5, 5) === 10)
      }
    }

    describe("add") {
      it("hu2 add success") {
        val user = new Userr
        val addXPoint = 4
        val addYPoint = 6
        val boardAfterAction: Option[Board] = user.add(addXPoint, addYPoint)
        assert(boardAfterAction.isDefined === true)
      }

      it("hu2 add fail") {
        val user = new Userr
        val addXPoint = 4
        val addYPoint = 3
        val boardAfterAction: Option[Board] = user.add(addXPoint, addYPoint)
        assert(boardAfterAction.isDefined === false)
      }
    }
  }
}
