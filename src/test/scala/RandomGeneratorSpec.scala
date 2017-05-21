import org.scalatest.{Matchers, WordSpec}

class RandomGeneratorSpec extends WordSpec with Matchers {

  import RandomGenerator._

  "Random Generator" should {

    "generate positive integers" in {
      val random = Simple(10)
      nonNegativeInt(random)._1 shouldEqual 3847489
    }

    "generate rectangles" in {
      val random = Simple(10)
      rectangle(random)._1 shouldEqual Rectangle(3847489, 1334288366)
    }

    "generate a list of integers" in {
      val random = Simple(10)
      intList(3)(random)._1 shouldEqual List(3847489, 1334288366, 1486862010)
    }

  }

}
