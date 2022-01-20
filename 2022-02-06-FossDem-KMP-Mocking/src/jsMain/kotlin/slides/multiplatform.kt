package slides

import net.kodein.pres.Slide
import net.kodein.pres.Transitions
import net.kodein.pres.Transitions.fade
import net.kodein.pres.Transitions.fontGrow
import net.kodein.pres.shownIf
import net.kodein.pres.sourcecode.SourceCode
import org.jetbrains.compose.web.dom.B
import org.jetbrains.compose.web.dom.Li
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.dom.Ul


val multiplatform = Slide(
    name = "multiplatform",
    stateCount = 7
) { state ->
    SourceCode(
        lang = "kotlin",
        code = """
            plugins {
                kotlin("multiplatform")
                id("org.kodein.mock.mockmp") version "1.0.1"
            }
        """.trimIndent()
    )

    Ul {
        Li({ shownIf(state >= 1, fade) }) { Text("Applies the KSP Gradle plugin.") }
        Li({ shownIf(state >= 2, fade) }) {
            Text("Adds the MocKMP processor KSP dependency")
            B({ shownIf(state >= 5, fontGrow) }) { Text(" to JVM sources") }
            Text(".")
        }
        Li({ shownIf(state >= 3, fade) }) { Text("Adds the MocKMP runtime implementation dependency.") }
        Li({ shownIf(state >= 4, fade) }) { Text("Adds the MocKMP helper implementation dependency.") }
        Li({ shownIf(state >= 6, fade) }) { Text("Configures all test source set to use generated sources.") }
    }
}