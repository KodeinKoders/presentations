package slides

import net.kodein.pres.Slide
import net.kodein.pres.sourcecode.SourceCode
import net.kodein.pres.sourcecode.zoomed


val injection = Slide(
    name = "injection",
    stateCount = 3
) { state ->
    SourceCode(
        lang = "kotlin",
        code = """
            internal fun «rec:MyTests.»injectMocks(mocker: Mocker) {
                view = MockView(mocker)
                model = fakeModel()
            }
        """.trimIndent()
    ) {
        "rec" { zoomed(state == 1) }
    }
}
