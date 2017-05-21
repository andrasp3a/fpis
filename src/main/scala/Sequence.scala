
import CombinatorMap._

object Sequence {

  def map2[A,B,C](stateActionA: Rand[A], stateActionB: Rand[B])(f: (A, B) => C): Rand[C] = {
    randomGenerator => {
      val (randomA, newGenerator1) = stateActionA(randomGenerator)
      val (randomB, newGenerator2) = stateActionB(newGenerator1)
      (f(randomA, randomB), newGenerator2)
    }
  }

  val randomIntAndDouble: Rand[(Int, Double)] = {
    map2(int, double) { (randomInt, randomDouble) =>
      (randomInt, randomDouble)
    }
  }

  def unit[A](a: A): Rand[A] = randomGenerator => (a, randomGenerator)

  def sequence[A](stateActions: List[Rand[A]]): Rand[List[A]] = {
    val zeroElement = unit(List[A]())
    stateActions.foldRight(zeroElement) { (currentStateAction, accumulatedStateAction) =>
      map2(currentStateAction, accumulatedStateAction)(_ :: _)
    }
  }

  def intList(count: Int): Rand[List[Int]] = {
    sequence(List.fill(count)(int))
  }

}
