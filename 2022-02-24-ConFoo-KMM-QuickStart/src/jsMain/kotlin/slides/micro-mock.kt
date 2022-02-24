package slides

import net.kodein.pres.Slide
import net.kodein.pres.Transitions.grow
import net.kodein.pres.shownIf
import net.kodein.pres.sourcecode.SourceCode
import net.kodein.pres.sourcecode.zoomed
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.css.em
import org.jetbrains.compose.web.css.fontSize
import org.jetbrains.compose.web.css.listStyleType
import org.jetbrains.compose.web.css.marginTop
import org.jetbrains.compose.web.dom.*


val microMock = listOf(
    Slide(
        name = "micro-mock",
        stateCount = 3
    ) { state ->
        H2 { Text("Micro-Mock") }

        SourceCode(
            lang = "kotlin",
            """
                class UserControllerTests : TestsWithMocks() {
                    override fun setUpMocks() = «z:injectMocks(mocker)»
                
                    «z:@Mock lateinit var» repo: UsersRepository
                    «z:@Fake lateinit var» user: User
                    val controller «z: by withMocks» { UsersController(repo) }
    
                    @Test fun testPrintUserInfos() {
                        «z:every {» repo.getUser(isAny()) «z:} returns» user
                        controller.printUserInfos(42)
                        «z:verify {» repo.getUser(42) «z:}»
                    }
                }
            """.trimIndent()
        ) {
            "z" { zoomed(state == 1) }
        }

    },

    Slide(
        name = "micro-mock-state",
        stateCount = 2
    ) { state ->
        H3 { Text("Micro-Mock can only...") }
        Ul({
            css {
                fontSize(1.5.em)
                listStyleType("none")
                marginTop(0.em)
            }
        }) {
            Li {
                Text("...mock ")
                B { Text("interfaces") }
                Text(".")
            }
            Li {
                Text("...fake ")
                B { Text("concrete data trees") }
                Text(".")
            }
        }
        H3({
            shownIf(state >= 1, grow)
        }) {
            Text("...because it works with Kotlin/Multiplatform!")
        }
    }
)
