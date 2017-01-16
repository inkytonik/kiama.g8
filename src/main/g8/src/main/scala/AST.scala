/**
 * Node of abstract syntax tree.
 */
abstract class Exp

/**
 * Numeric literal expression.
 */
case class Num (i : Int) extends Exp

/**
 * Addition of two expressions.
 */
case class Add (l : Exp, r : Exp) extends Exp

/**
 * Multiplication of two expressions.
 */
case class Mul (l : Exp, r : Exp) extends Exp
