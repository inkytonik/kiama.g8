import org.kiama.util.RegexParserTests

class ParserTests extends RegexParserTests with Parser {

    test("parse an integer") {
        assertParseOk ("42", parser, Num (42))
    }

}
