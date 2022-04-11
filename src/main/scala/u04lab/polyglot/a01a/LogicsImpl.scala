package u04lab.polyglot.a01a
import Logics.*
import scala.util.Random
import u04lab.code.List
import u04lab.code.List

/** solution and descriptions at https://bitbucket.org/mviroli/oop2019-esami/src/master/a01a/sol2/ */
class LogicsImpl(private val size: Int, private val boat: Int) extends Logics:

  private val failures = 5
  private val random = Random()
  private val boatRow = random.nextInt(size)
  private val boatLeftCol = random.nextInt(size - boat + 1)
  private val pushedButtons: List[(Int, Int)] = List.Nil()



  override def hit(row: Int, col: Int) =
    Result.HIT
    /*(row, col) match
    case (row, col) if(row == boatRow && col >= boatLeftCol
      && col < (boatLeftCol + size)) => append()*/


