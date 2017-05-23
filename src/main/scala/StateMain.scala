import RandomGenerator.Simple
import State._

object StateMain extends App {

  type Rand[A] = State[RandomGenerator, A]

  val int: Rand[Int] = State(_.nextInt)

  def nonNegativeLessThan(upperBoundary: Int): Rand[Int] = {
    int.map(_ % upperBoundary)
  }

  def intList(count: Int): Rand[List[Int]] = {
    sequence(List.fill(count)(int))
  }

  case class Player(name: String, level: Int, weaponIds: List[Int])


  val randomPlayerDeSugared =
    int.flatMap( level =>
      nonNegativeLessThan(10).flatMap( numberOfWeapons =>
        intList(numberOfWeapons).map( weaponIds =>
          Player("Random Joe", level, weaponIds)
        )
      )
    )


  val randomPlayer = for {
    level           <- int
    numberOfWeapons <- nonNegativeLessThan(10)
    weaponIds       <- intList(numberOfWeapons)
  } yield Player("Random Joe", level, weaponIds)


  val randomGenerator = Simple(10)
  println("\n===\nPlayer\n===\n")
  println(randomPlayer.run(randomGenerator))

}
