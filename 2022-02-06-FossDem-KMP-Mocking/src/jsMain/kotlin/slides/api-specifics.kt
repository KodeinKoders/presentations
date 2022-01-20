package slides

import net.kodein.pres.Slide
import net.kodein.pres.Transitions.fade
import net.kodein.pres.shownIf
import net.kodein.pres.sourcecode.SourceCode
import net.kodein.pres.sourcecode.fontGrow
import net.kodein.pres.sourcecode.lineHeight
import org.jetbrains.compose.web.dom.*


val apiSpecifics = listOf(

    Slide(
        name = "every",
        stateCount = 4
    ) { state ->
        SourceCode(
            lang = "kotlin",
            code = """
                every { controller.start() }«jr: just Runs»«ru: returns Unit»«rp: runs { Helper.start() }»
            """.trimIndent()
        ) {
            "jr" { fontGrow(state < 1) }
            "ru" { fontGrow(state == 1 || state >= 3) }
            "rp" { fontGrow(state == 2) }
        }

        Br {}
        Br {}
        Div({ shownIf(state >= 3, fade) }) {
            H4 { Text("No relaxed mode!") }
            P { Text("Everything must be explicit") }
        }
    },

    Slide(
        name = "verify",
        stateCount = 6
    ) { state ->
        SourceCode(
            lang = "kotlin",
            code = """
                verify(«e:exhaustive =»«e1: true»«e2: false»«o:, ordered =»«o1: true»«o2: false») {
                «c:    controller.start()
                    controller.stop()
                »}
            """.trimIndent()
        ) {
            "c" { lineHeight(state >= 2) }

            "e" { fontGrow(state >= 1) }
            "e1" { fontGrow(state in 1..2) }
            "e2" { fontGrow(state >= 3) }

            "o" { fontGrow(state in 4..5) }
            "o1" { fontGrow(state == 4) }
            "o2" { fontGrow(state >= 5) }
        }
    }
)