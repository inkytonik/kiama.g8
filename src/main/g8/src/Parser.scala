 trait Parser extends org.kiama.util.ParserUtilities {

    lazy val parser =
        phrase (exp)

    lazy val exp : PackratParser[Exp] =
        exp ~ ("+" ~> term) ^^ Add |
        term

    lazy val term : PackratParser[Exp] =
        term ~ ("*" ~> factor) ^^ Mul |
        factor

    lazy val factor : PackratParser[Exp] =
        integer | "(" ~> exp <~ ")"

    lazy val integer =
        "[0-9]+".r ^^ (s => Num (s.toInt))

}
