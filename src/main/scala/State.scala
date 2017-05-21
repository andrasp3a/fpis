import State._


case class State[S, +A](run: S => (A, S)) {

  def flatMap[B](f: A => State[S, B]): State[S, B] = State(currentState => {
    val (a, newState) = run(currentState)
    f(a).run(newState)
  })

  def map[B](f: A => B): State[S, B] = {
    flatMap(a => unit(f(a)))
  }

  def map2[B,C](stateActionB: State[S, B])(f: (A, B) => C): State[S, C] = {
    flatMap(a => stateActionB.map(b => f(a, b)))
  }
}

object State {

  type Rand[A] = State[RandomGenerator, A]

  def unit[S, A](a: A): State[S, A] =
    State(currentSate => (a, currentSate))

  def sequence[S, A](stateActions: List[State[S, A]]): State[S, List[A]] = {
    val zeroElement = unit[S, List[A]](List.empty)
    stateActions.foldRight(zeroElement) { (currentStateAction, accumulatedStateAction) =>
      currentStateAction.map2(accumulatedStateAction)(_ :: _)
    }
  }

}