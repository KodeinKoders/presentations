package slides

import net.kodein.pres.Slide
import net.kodein.pres.Transitions
import net.kodein.pres.shownIf
import net.kodein.pres.sourcecode.SourceCode
import net.kodein.pres.sourcecode.fontGrow
import net.kodein.pres.sourcecode.lineHeight
import net.kodein.pres.sourcecode.zoomed
import org.jetbrains.compose.web.css.em
import org.jetbrains.compose.web.css.marginBottom
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.H4
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text


val kodein = listOf(
    Slide(
        name = "kodein",
        stateCount = 7
    ) { state ->
        H3({
            style { marginBottom(0.em) }
        }) { Text("Modularize your code") }
        H4 {
            Text("with Kodein")
        }

        SourceCode(
            lang = "kotlin",
            """
                store:class BreweryStore(val httpClient: HttpClient, val db: DB)
                «di:
                val di = DI {
                    bind«z1:Singleton»<HttpClient> { HttpClient() }
                    bind«z1:Singleton»<DB> { openDB() }
                    «z2:bind«z1:Provider»<Store> { «new-in:new(::»BreweryStore«new-out:(instance(), instance()») }»
                }
                
                val store: Store «z3:by di.instance()»
            """.trimIndent()
        ) {
            "di" { lineHeight(state >= 1) }
            "z1" { zoomed(state == 2) }
            "z2" { zoomed(state in 3..4) }
            "new-out" { fontGrow(state <= 3) }
            "new-in" { fontGrow(state >= 4) }

            "z3" { zoomed(state == 6) }
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
