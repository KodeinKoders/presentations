package slides

import net.kodein.pres.*
import net.kodein.pres.Transitions
import net.kodein.pres.Transitions.fade
import net.kodein.pres.sourcecode.SourceCode
import net.kodein.pres.sourcecode.zoomed
import net.kodein.theme.KodeinColor
import net.kodein.theme.compose.web.Logo
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.attributes.ATarget
import org.jetbrains.compose.web.attributes.target
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.kodein.cic.css
import utils.Dimmed
import utils.Stamp
import utils.dimmed


val d7_example = listOf(
    Slide(
        name = "mocking",
        stateCount = 7
    ) { state ->
        H4 { Text("Mocking") }
        SourceCode("kotlin", """
            class MyTest : TestsWithMocks() {
              override fun setUpMocks() = injectMocks(mocker)
            
              «m:@Mock lateinit var view: View»
              «f:@Fake lateinit var model: Model»
            
              «w:val controller by withMocks { Controller(view, model) }»
            
              @Test fun controllerTest() {
                «e:every { view.render(isAny()) } returns true»
                controller.start()
                «v:verify { view.render(model) }»
              }
            }
        """.trimIndent()) {
            "m" { zoomed(state == 1) }
            "f" { zoomed(state == 2) }
            "w" { zoomed(state == 3) }
            "e" { zoomed(state == 4) }
            "v" { zoomed(state == 5) }
        }
    },
    Slide(
        name = "MocKMP"
    ) {
        H2 { Text("MocKMP") }
        H4 {
            Text("A ")
            B({
                css {
                    color(KodeinColor.orange.css)
                }
            }) {
                Text("KOSI")
            }
            Text(" library.")
        }
        P {
            Text("Built with ")
            Img(src = "img/deezer.png") {
                css {
                    height(1.1.em)
                }
            }
        }
        P {
            A(href = "https://github.com/kosi-libs/MocKMP") {
                Text("https://github.com/kosi-libs/MocKMP")
            }
        }
    }
).animatedWith(Animations.Move(towards = Animations.Move.Towards.Bottom))
