package slides

import net.kodein.pres.Slide
import net.kodein.pres.Transitions.grow
import net.kodein.pres.shownIf
import net.kodein.pres.sourcecode.SourceCode
import net.kodein.pres.sourcecode.zoomed
import org.jetbrains.compose.web.css.em
import org.jetbrains.compose.web.css.fontSize
import org.jetbrains.compose.web.css.listStyleType
import org.jetbrains.compose.web.css.marginTop
import org.jetbrains.compose.web.dom.B
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.Li
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.dom.Ul
import org.kodein.cic.css


val mocKMP = listOf(
    Slide(
        name = "MocKMP",
        stateCount = 3
    ) { state ->
        H2 { Text("MocKMP") }

        SourceCode(
            lang = "kotlin",
            """
                class BreweryControllerTests : TestsWithMocks() {
                    override fun setUpMocks() = «z:injectMocks(mocker)»
                
                    «z:@Mock lateinit var» repo: BreweriesRepository
                    «z:@Fake lateinit var» brewery: Brewery
                    val controller «z:by withMocks» { BreweriesController(repo) }
    
                    @Test fun testPrintBreweryInfos() {
                        «z:every {» repo.getBrewery(isAny()) «z:} returns» brewery
                        controller.printBreweryInfos(42)
                        «z:verify {» repo.getBrewery(42) «z:}»
                    }
                }
            """.trimIndent()
        ) {
            "z" { zoomed(state == 1) }
        }

    },

    Slide(
        name = "mocKMP-state",
        stateCount = 2
    ) { state ->
        H3 { Text("MocKMP can only...") }
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
            Text("...because it works with Kotlin Multiplatform!")
        }
    }
)
