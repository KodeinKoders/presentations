package slides

import net.kodein.pres.Slide
import net.kodein.pres.Transitions
import net.kodein.pres.shownIf
import net.kodein.pres.sourcecode.SourceCode
import net.kodein.pres.sourcecode.zoomed
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text


val kodein = listOf(
    Slide(
        name = "kodein",
        stateCount = 4
    ) { state ->
        H2 { Text("Kodein") }

        SourceCode(
            lang = "kotlin",
            """
                val di = DI {
                    bind«z1:Singleton»<DB> { openDB() }
                    bind«z1:Provider»<Controller> { controller(instance()) }
                }
                
                val controller: Controller «z2:by di.instance()»
            """.trimIndent()
        ) {
            "z1" { zoomed(state == 1) }
            "z2" { zoomed(state == 2) }
        }
    },

    Slide(
        name = "kodein-state",
        stateCount = 2
    ) { state ->
        H3 { Text("Kodein 7 supports...") }

        P {
            Text("Scoping, Modularization, Test overrides, inspection, Constructor injection, Lazy retrieval, Composition, Android, Compose (for Android & Multiplatform), and more...")
        }

        Div({ shownIf(state >= 1, Transitions.fade) }) {
            H3 { Text("Kodein WILL supports...") }

            P {
                Text("Full Swift support, Correctness verification compiler plugin.")
            }
        }
    }
)
