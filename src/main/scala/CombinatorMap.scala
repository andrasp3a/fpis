
import RandomGenerator._

object CombinatorMap {

  type Rand[+A] = RandomGenerator => (A, RandomGenerator)

  val int: Rand[Int] = _.nextInt

  def map[A, B](stateAction: Rand[A])(f: A => B): Rand[B] = {
    randomGenerator => {
      val (a, newGenerator) = stateAction(randomGenerator)
      (f(a), newGenerator)
    }
  }

  val double: Rand[Double] = {
    map(nonNegativeInt) { randomInt =>
      randomInt / (Int.MaxValue.toDouble + 1)
    }
  }

}