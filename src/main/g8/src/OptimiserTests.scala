import org.scalatest.FunSuiteLike

/**
 * Optimiser tests.
 */
class OptimiserTests extends Parser with FunSuiteLike {

    import Optimiser.optimise

    test ("addition of zero is optimised away") {
        val e = Add (Num (1), Add (Num (0), Num (2)))
        assertResult (Add (Num (1), Num (2))) (optimise (e))
    }

    test ("multiplication by zero is optimised away") {
        val e = Mul (Num (0), Num (2))
        assertResult (Num (0)) (optimise (e))
    }

    test ("multiplication by one is optimised away") {
        val e = Mul (Mul (Num (1), Num (2)), Num (3))
        assertResult (Mul (Num (2), Num (3))) (optimise (e))
    }

    test ("a combination of + 0 and * 0 is optimised away") {
        val e = Add (Mul (Num (2), Num (0)), Num (1))
        assertResult (Num (1)) (optimise (e))
    }

}
