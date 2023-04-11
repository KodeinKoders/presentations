package slides

import net.kodein.pres.*
import net.kodein.pres.Transition
import net.kodein.pres.Transitions.fade
import net.kodein.pres.Transitions.fontGrow
import net.kodein.theme.KodeinColor
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.attributes.AttrsScope
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.css.Transitions
import org.jetbrains.compose.web.dom.*
import org.kodein.cic.css
import org.w3c.dom.HTMLHeadingElement

private fun AttrsScope<*>.heightIf(h: CSSNumeric, condition: Boolean) {
    css {
        transitions {
            "height" { duration = 500.ms }
            "opacity" { duration = 500.ms }
        }
    }
    style {
        height(if (condition) h else 0.em)
        opacity(if (condition) 1 else 0)
    }
}

val d7_engagement = listOf(
    Slide(
        name = "engagement-title",
        stateCount = 2
    ) { state ->
        P({ css { fontSize(2.em) } }) {
            Text("What can we, as KMM developers,")
            Br()
            Text("do to improve")
            Br()
            Span({ css { color(KodeinColor.orange.css) } }) {
                Span({ shownIf(state >= 1, fontGrow) }) { Text("mutual ") }
                Text("engagement")
            }
            Br()
            Span({ shownIf(state < 1, fontGrow) }) { Text("of ") }
            Span({ shownIf(state >= 1, fontGrow) }) { Text("with ") }
            Text("iOS developers?")
        }
    },
    Slide(
        name = "mutual-learn",
        stateCount = 7
    ) { state ->
        H3 { Text("Ideas to improve mutual engagement with iOS teams") }
        Ul({ css { fontSize(1.6.em) } }) {
            Li({ heightIf(1.2.em, state >= 1) }) { Text("Understand Objective-C") }
            Li({ heightIf(1.2.em, state >= 2) }) { Text("Learn Swift!") }
            Li({ heightIf(if (state < 4) 7.2.em else 2.4.em, state >= 3) }) {
                Text("Empathize with their pain points...")
                Ul({ heightIf(6.em, state == 3) }) {
                    Li { Text("No proper IDE support") }
                    Li { Text("Mandatory Android SDK") }
                    Li { Text("Difficult stack-trace debug") }
                    Li { Text("Difficult memory integration (KT-55512)") }
                    Li { Text("Complex code-test-debug loop") }
                }
                Span({ heightIf(1.2.em, state >= 4) }) { Text("...and take the time to address them") }
            }
            Li({ heightIf(1.2.em, state >= 5) }) { Text("Dissociate Multiplatform from Android") }
            Li({ heightIf(1.2.em, state >= 6) }) { Text("Find ambassadors and teach them Kotlin") }
        }
    }
).animatedWith(Animations.Move(towards = Animations.Move.Towards.Bottom))
