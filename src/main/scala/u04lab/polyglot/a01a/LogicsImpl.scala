package u04lab.polyglot.a01a
import Logics.*
import scala.util.Random

import u04lab.code.List.*
import u04lab.code.List

/** solution and descriptions at https://bitbucket.org/mviroli/oop2019-esami/src/master/a01a/sol2/ */
class LogicsImpl(private val size: Int, private val boat: Int) extends Logics:

  private val failures = 5
  private val random = Random()
  private val boatRow = random.nextInt(size)
  private val boatLeftCol = random.nextInt(size - boat + 1)
  private var pushedButtons: List[(Int, Int)] = List.Nil()
  private var tries = 0



  override def hit(row: Int, col: Int) =
    if(row == boatRow && col >= boatLeftCol && col <= (boatLeftCol + size)) then
      pushedButtons = append(pushedButtons, List.Cons((row, col), Nil()))
      length(pushedButtons) match
        case n if (n == boat) => Result.WON
        case _ => Result.HIT
    else
      tries = tries.+(1)
      tries match
        case n if (n == failures) => Result.LOST
        case _ => Result.MISS

    /*(row, col) match
    case (row, col) if(row == boatRow && col >= boatLeftCol
      && col < (boatLeftCol + size)) => append()*/


