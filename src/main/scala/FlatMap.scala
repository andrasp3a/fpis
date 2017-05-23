import CombinatorMap._
import Sequence._

object FlatMap {

  def flatMap[A,B](stateAction: Rand[A])(f: A => Rand[B]): Rand[B] = {
    randomGenerator => {
      val (randomA, newGenerator) = stateAction(randomGenerator)
      f(randomA)(newGenerator)
    }
  }

  val randomLengthRandomIntList = flatMap(nonNegativeLessThan(100)) { randomInt =>
    intList(randomInt)
  }


  // Revisit map combinator

  def _map[A,B](stateAction: Rand[A])(f: A => B): Rand[B] =
    flatMap(stateAction)(a => unit(f(a)))

}
