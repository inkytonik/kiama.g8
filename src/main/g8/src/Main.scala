import org.kiama.util.ParsingREPL

/**
 * A top-level read-eval-print loop.  Reads a simple arithmetic expression
 * and prints it, its value and variants.  E.g.
 *
 * exp> 0 + 4 * 1
 * e = Add(Num(0),Mul(Num(4),Num(1)))
 * e tree:
 * Add (Num (0), Mul (Num (4), Num (1)))
 * e tree pretty printed:
 * (0 + (4 * 1))
 * value (e) = 4
 * e optimised = Num(4)
 * value (e optimised) = 4
 */
object Main extends ParsingREPL[Exp] with Parser {

    import org.kiama.util.REPLConfig
    import Evaluator.value
    import PrettyPrinter.{pretty, pretty_any}
    import Optimiser.optimise

    val banner =
        """Enter expressions using numbers, addition and multiplication.
          |  e.g., (1 + 2) * 3 or 0 + 4 * 1
          |""".stripMargin

    override def prompt () = "exp> "

    /**
     * Print the expression as a value, as a tree, pretty printed.
     * Print its value. Optimise it and then print the optimised
     * expression and its value.
     */
    override def process (e : Exp, config : REPLConfig) {
        val output = config.output
        output.emitln ("e = " + e)
        output.emitln ("e tree:")
        output.emitln (pretty_any (e))
        output.emitln ("e tree pretty printed:")
        output.emitln (pretty (e))
        output.emitln ("value (e) = " + value (e))
        val o = optimise (e)
        output.emitln ("e optimised = " + o)
        output.emitln ("value (e optimised) = " + value (o))
    }

}
