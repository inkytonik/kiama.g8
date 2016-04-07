import org.bitbucket.inkytonik.kiama.util.ParseTests

/**
 * Expression evaluator tests.
 */
class EvaluatorTests extends ParseTests {

    import org.bitbucket.inkytonik.kiama.parsing.Success
    import org.bitbucket.inkytonik.kiama.util.StringSource
    import org.scalacheck.Prop._

    val parsers = new SyntaxAnalyser (positions)

    /**
     * Parse and evaluate a term returning the result. `None` means
     * the parse failed.
     */
    def eval (term : String) : Option[Int] = {
        val source = new StringSource (term)
        parsers.parseAll (parsers.exp, source) match {
            case Success (e, in) if in.atEnd =>
                Some (Evaluator.value (e))
            case _ =>
                None
        }
    }

   /**
     * Parse and evaluate term then compare to expected result. Fail if the
     * parsing or the comparison fail.
     */
    def evalTo (term : String, result : Int) : Boolean = {
        val source = new StringSource (term)
        parsers.parseAll (parsers.exp, source) match {
            case Success (e, in) if in.atEnd =>
                Evaluator.value (e) == result
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
        eval ("123") shouldBe Some (123)
    }

    test ("precedence is handled correctly") {
        eval ("1 + 2 * 3") shouldBe Some (7)
    }

    test ("parentheses are handled correctly") {
        eval ("(1 + 2) * 3") shouldBe Some (9)
    }

}
