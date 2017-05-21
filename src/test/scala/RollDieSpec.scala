import org.scalatest.{Matchers, WordSpec}

class RollDieSpec extends WordSpec with Matchers {

  def rollDie: Int = {
    val random = new scala.util.Random
    random.nextInt(6)
  }

  def rollDie(random: scala.util.Random): Int = {
    random.nextInt(6)
  }

  "RollDie" should {

    "return a number between 1 and 6" in {
      val result = rollDie
      result should be <= 6
      result should be >= 1
    }

    "return a number between 1 and 6 - inject random" in {
      val seed = 1234
      val random = new scala.util.Random(seed)

      val result = rollDie(random)

      println("result: " + result)

      result should be <= 6
      result should be >= 1
    }

  }

}