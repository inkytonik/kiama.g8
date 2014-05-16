object Optimiser {

    import org.kiama.rewriting.Rewriter.{attempt, bottomup, rewrite, rule}

    def optimise (e : Exp) : Exp =
        rewrite (optimiser) (e)

    /**
     * Try to optimise every expression in a tree. Do it bottom
     * up so higher up tries get the advantage of things done
     * at lower levels.
     */
    lazy val optimiser =
        bottomup (attempt (simplifier))

    /**
     * Simplify an expression using simple equivalences.
     */
    lazy val simplifier =
        rule[Exp] {
            case Add (Num (0), e)     => e
            case Add (e, Num (0))     => e
            case Mul (Num (1), e)     => e
            case Mul (e, Num (1))     => e
            case Mul (z @ Num (0), _) => z
            case Mul (_, z @ Num (0)) => z
        }

}
