import RandomGenerator._

object Main extends App {

  val randomGenerator = Simple(10)

  import CombinatorMap._

  println(int(randomGenerator))

  println(double(randomGenerator))



  import Sequence._

  println(randomIntAndDouble(randomGenerator))

  println(intList(10)(randomGenerator))



  import FlatMap._

  println(randomLengthRandomIntList(randomGenerator))

}