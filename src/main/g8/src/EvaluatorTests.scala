import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite
import org.scalatest.prop.Checkers

/**
 * Expression evaluator tests.
 */
@RunWith(classOf[JUnitRunner])
class EvaluatorTests extends Parser with FunSuite with Checkers {

    import Evaluator.value
    import org.scalacheck.Prop._

    /**
     * Parse and evaluate term then compare to result. Fail if any the parsing
     * or the comparison fail.
     */
    def expectEval (term : String, result : Int) {
        parseAll (start, term) match {
            case Success (e, in) if in.atEnd =>
                expect (result) (value (e))
            case Success (_, in) =>
                fail ("extraneous input at " + in.pos + ": " + term)
            case f =>
                fail ("parse failure: " + f)
        }
    }

   /**
     * Parse and evaluate term then compare to expected result. Fail if the
     * parsing or the comparison fail.
     */
    def evalTo (term : String, result : Int) : Boolean = {
        parseAll (start, term) match {
            case Success (e, in) if in.atEnd =>
                value (e) == result
            case _ =>
                false
        }
    }

    test ("an integer leaf evaluates to itself") {
        check ((i : Int) => (i >= 0) ==> evalTo (i.toString, i))
    }

    test ("an addition of two integers evaluates to the sum") {
        check ((i : Int, j : Int) =>
            ((i >= 0) && (j >= 0)) ==> {
                val s = i.toString + " + " + j.toString
                evalTo (s, i + j)
            })
    }

    test ("a multiplication of two integers evaluates to the product") {
        check ((i : Int, j : Int) =>
            ((i >= 0) && (j >= 0)) ==> {
                val s = i.toString + " * " + j.toString
                evalTo (s, i * j)
            })
    }

    test ("a number evaluates to itself value") {
        expectEval ("123", 123)
    }

    test ("precedence is handled correctly") {
        expectEval ("1 + 2 * 3", 7)
    }

    test ("parentheses are handled correctly") {
        expectEval ("(1 + 2) * 3", 9)
    }

}
