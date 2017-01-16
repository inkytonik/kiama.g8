import org.scalatest.{FunSuiteLike, Matchers}

/**
 * Optimiser tests.
 */
class OptimiserTests extends FunSuiteLike with Matchers {

    import Optimiser.optimise

    test ("addition of zero is optimised away") {
        val e = Add (Num (1), Add (Num (0), Num (2)))
        optimise (e) shouldBe Add (Num (1), Num (2))
    }

    test ("multiplication by zero is optimised away") {
        val e = Mul (Num (0), Num (2))
        optimise (e) shouldBe Num (0)
    }

    test ("multiplication by one is optimised away") {
        val e = Mul (Mul (Num (1), Num (2)), Num (3))
        optimise (e) shouldBe Mul (Num (2), Num (3))
    }

    test ("a combination of + 0 and * 0 is optimised away") {
        val e = Add (Mul (Num (2), Num (0)), Num (1))
        optimise (e) shouldBe Num (1)
    }

}
