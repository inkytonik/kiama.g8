import org.junit.runner.RunWith
import org.kiama.util.RegexParserTests
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class ParserTests extends RegexParserTests with Parser {

    test("parse an integer") {
        assertParseOk ("42", start, Num (42))
    }

}
