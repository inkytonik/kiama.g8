import org.bitbucket.inkytonik.kiama.util.ParseTests

class SyntaxAnalyserTests extends ParseTests {

    import org.bitbucket.inkytonik.kiama.util.Positions

    val positions = new Positions
    val parsers = new SyntaxAnalyser(positions)

    test("parse an integer") {
        parsers.exp ("42") should parseTo[Exp](Num (42))
    }

}
