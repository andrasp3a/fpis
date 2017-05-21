
object SideEffectingRandom extends App {

  val randomGenerator = scala.util.Random

  randomGenerator.nextInt
  // -432156759

  randomGenerator.nextInt
  // 1821474173

}
