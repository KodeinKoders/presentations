package slides

import net.kodein.pres.*
import net.kodein.pres.Transitions
import net.kodein.pres.sourcecode.SourceCode
import net.kodein.theme.KodeinColor
import net.kodein.theme.compose.web.Logo
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.attributes.ATarget
import org.jetbrains.compose.web.attributes.target
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.kodein.cic.css


val d2_whatIsKMP = listOf(
    Slide(
        name = "what-is-KMP"
    ) {
        Img(
            src = "img/kmp.png"
        ) {
            css {
                maxWidth(80.percent)
                maxHeight(80.percent)
            }
        }

    },
    Slide(
        name = "reflectionless-KMP",
        stateCount = 2
    ) { state ->
        H4 {
            Text("""
                While Kotlin/JVM supports reflection, Kotlin/JS and Kotlin/Native do not.
            """)
            Br()
            Br()
            Span({
                shownIf(state >= 1, Transitions.fade)
            }) {
                Text("""
                    Therefore, Kotlin/Multiplatform does not support Reflection either.
                """)
            }
        }

    }
).animatedWith(Animations.Move(towards = Animations.Move.Towards.Bottom))
