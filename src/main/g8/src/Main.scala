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
        val emitter = config.emitter
        emitter.emitln ("e = " + e)
        emitter.emitln ("e tree:")
        emitter.emitln (pretty_any (e))
        emitter.emitln ("e tree pretty printed:")
        emitter.emitln (pretty (e))
        emitter.emitln ("value (e) = " + value (e))
        val o = optimise (e)
        emitter.emitln ("e optimised = " + o)
        emitter.emitln ("value (e optimised) = " + value (o))
    }

}
