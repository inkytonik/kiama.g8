/**
 * AST pretty-printing.
 */
object PrettyPrinter extends org.bitbucket.inkytonik.kiama.output.PrettyPrinter {

    /**
     * Return a pretty-printed version of a node.
     */
    def layout (t : Exp) : String =
        super.layout (show (t))

    /**
     * Convert an expression node to a pretty-printing document.
     */
    def show (t : Exp) : Doc =
        t match {
            case Num (d)      => value (d)
            case Add (l, r)   => showbin (l, "+", r)
            case Mul (l, r)   => showbin (l, "*", r)
        }

    /**
     * Return a pretty-printing document for an instance of a binary expression.
     */
    def showbin (l : Exp, op : String, r : Exp) : Doc =
        parens (show (l) <+> op <+> show (r))

}
