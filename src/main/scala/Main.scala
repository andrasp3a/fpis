import RandomGenerator._

object Main extends App {

  val randomGenerator = Simple(10)

  import CombinatorMap._

  println("\n===\nmap\n===\n")

  println(int(randomGenerator))

  println(double(randomGenerator))



  import Sequence._

  println("\n===\nsequence\n===\n")

  println(randomIntAndDouble(randomGenerator))

  println(intList(10)(randomGenerator))



  import FlatMap._

  println("\n===\nflatmap\n===\n")

  println(randomLengthRandomIntList(randomGenerator))

}