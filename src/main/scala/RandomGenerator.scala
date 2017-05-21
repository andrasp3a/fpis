
trait RandomGenerator {

  def nextInt: (Int, RandomGenerator)

}

object RandomGenerator {


  case class Simple(seed: Long) extends RandomGenerator {
    def nextInt: (Int, RandomGenerator) = {
      val newSeed = (seed * 0x5DEECE66DL + 0xBL) & 0xFFFFFFFFFFFFL
      val nextGenerator = Simple(newSeed)
      val n = (newSeed >>> 16).toInt
      (n, nextGenerator)
    }
  }

  def nonNegativeInt(random: RandomGenerator): (Int, RandomGenerator) = {
    val (i, newGenerator) = random.nextInt
    (Math.abs(i), newGenerator)
  }

  case class Rectangle(width: Int, height: Int)

  def rectangle(random: RandomGenerator): (Rectangle, RandomGenerator) = {
    val (randomInt1, newGenerator1) = nonNegativeInt(random)
    val (randomInt2, newGenerator2) = nonNegativeInt(newGenerator1)
    (Rectangle(randomInt1, randomInt2), newGenerator2)
  }

  def intList(count: Int)(random: RandomGenerator): (List[Int], RandomGenerator) = {
    if (count == 0) {
      (List(), random)
    } else {
      val (randomInt, newGenerator1) = random.nextInt
      val (randomIntList, newGenerator2) = intList(count - 1)(newGenerator1)
      (randomInt :: randomIntList, newGenerator2)
    }
  }

}