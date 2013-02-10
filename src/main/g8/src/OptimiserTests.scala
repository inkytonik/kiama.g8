import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite

/**
 * Optimiser tests.
 */
@RunWith(classOf[JUnitRunner])
class OptimiserTests extends Parser with FunSuite {

    import Optimiser.optimise

    test ("addition of zero is optimised away") {
        val e = Add (Num (1), Add (Num (0), Num (2)))
        expectResult (Add (Num (1), Num (2))) (optimise (e))
    }

    test ("multiplication by zero is optimised away") {
        val e = Mul (Num (0), Num (2))
        expectResult (Num (0)) (optimise (e))
    }

    test ("multiplication by one is optimised away") {
        val e = Mul (Mul (Num (1), Num (2)), Num (3))
        expectResult (Mul (Num (2), Num (3))) (optimise (e))
    }

    test ("a combination of + 0 and * 0 is optimised away") {
        val e = Add (Mul (Num (2), Num (0)), Num (1))
        expectResult (Num (1)) (optimise (e))
    }

}
