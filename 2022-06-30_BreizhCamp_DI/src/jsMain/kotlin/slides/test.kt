package slides

import net.kodein.pres.Slide
import net.kodein.pres.sourcecode.SourceCode
import net.kodein.pres.sourcecode.lineHeight
import org.jetbrains.compose.web.dom.H4
import org.jetbrains.compose.web.dom.Text

val testing = Slide(
    name = "test",
    stateCount = 3
) { state ->
    H4 { Text("Testing with Kodein-DI") }
    SourceCode(
        lang = "kotlin",
        code = """
        «under-test:private val underTestDI = DI {
            extend(applicationContainer)
        «override:    bindSingleton(overrides = true) {
                DB.inMemory.open("personDB")
            }
        »}

        »@Test
        fun `unit testing PersonUseCase`() {
            // given...
            // when...
            // then...
        }
        """.trimIndent()) {
        "under-test" {lineHeight(state >= 1)}
        "override" {lineHeight(state >= 2)}
    }
}