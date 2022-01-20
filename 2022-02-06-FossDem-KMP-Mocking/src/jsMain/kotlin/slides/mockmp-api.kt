package slides

import net.kodein.pres.Slide
import net.kodein.pres.sourcecode.SourceCode
import net.kodein.pres.sourcecode.zoomed
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.Text


val mockmpApi = Slide(
    name = "mockmp-api",
    stateCount = 6
) { state ->
    H3 { Text("MocKMP") }

    SourceCode(
        lang = "kotlin",
        code = """
            «class:class MyTests : TestsWithMocks() {
                override fun setUpMocks() = «gen:injectMocks(mocker)»»

            «mocks:    «gen:@Mock» lateinit var view: View
                «gen:@Fake» lateinit var model: Model

                val controller by withMocks { Controller(view, model) }»

            «test:    @Test fun controllerTest() {
                    every { view.render(isAny()) } returns true
                    controller.start()
                    verify { view.render(model) }
                }»
            }

        """.trimIndent()
    ) {
        "class" { zoomed(state == 1) }
        "mocks" { zoomed(state == 2) }
        "test" { zoomed(state == 3) }
        "gen" { zoomed(state == 5) }
    }
}
