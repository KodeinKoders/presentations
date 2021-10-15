package slides

import net.kodein.pres.Slide
import net.kodein.pres.Transitions.fade
import net.kodein.pres.shownIf
import net.kodein.pres.sourcecode.*
import net.kodein.pres.util.d
import net.kodein.theme.compose.pres.KodeinAttrs
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.css.em
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.dom.*

val compilerPlugin = listOf(
    Slide(
        name = "generated-interface-usage",
        config = { KodeinAttrs(workInProgress = true) },
        stateCount = 6
    ) { state ->
        SourceCode(
            lang = "kotlin",
            code = """
                «itf:interface Dependencies {
                    fun httpClient(base: String): HttpClient
                    fun api(): API
                }
                »
                val module = Module«mdeps:.of<Dependencies>»("app") {
                    bindFactory { base: String -> HttpClient(base) }
                    bindSingleton { UserAPI(«instOut:«instIn:instance(base = API_BASE)»»«mdeps:httpClient(arg = API_BASE)») }
                }

                «com:// Completion check!
                »val deps: «di:DI»«deps:Dependencies» = DI«deps:.of<Dependencies>» {
                    import(module)
                }
                
            """.trimIndent()
        ) {
            "itf" { lineHeight(state >= 1) }
            "di" { fontGrow(state < 2) }
            "deps" { fontGrow(state >= 2) }
            "com" { lineHeight(state >= 3) }
            "instIn" { zoomed(state == 4) }
            "instOut" { fontGrow(state < 5) }
            "mdeps" { fontGrow(state >= 5) }
        }
    },
    Slide(
        name = "generated-interface-plugin",
        config = { KodeinAttrs(workInProgress = true) },
        stateCount = 2
    ) { state ->
        H3 { Text("A compiler plugin to generate retrival interfaces...") }
        Ul({
            css {
                d("li") {
                    padding(0.3.em, 0.em)
                }
            }
        }) {
            Li {
                Text("...allows ")
                B { Text("completeness and correctness") }
                Text(" checks...")
                Br()
                Text("...which can be performed in a ")
                B { Text("simple unit test") }
                Text("!")
            }
            Li {
                Text("...makes retrieval ")
                B { Text("semantic") }
                Text("!")
            }
            Li({
                shownIf(state >= 1, fade)
            }) {
                Text("...needs a ")
                B { Text("compiler plugin API") }
                Text(" ")
                Small { Text("(current unstable API? KSP?)") }
                Text(".")
            }
            Li({
                shownIf(state >= 1, fade)
            }) {
                Text("...needs ")
                B { Text("context receivers") }
                Text(" ")
                Small { Text("(KEEP-259)") }
                Text(" to expose a workable API")
            }
        }
    }
)