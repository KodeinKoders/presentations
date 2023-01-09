package slides

import net.kodein.pres.Animations
import net.kodein.pres.Slide
import net.kodein.pres.Transitions
import net.kodein.pres.animatedWith
import net.kodein.pres.shownIf
import net.kodein.pres.sourcecode.SourceCode
import net.kodein.theme.compose.pres.KodeinAttrs
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.H4
import org.jetbrains.compose.web.dom.Li
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.dom.Ul
import utils.plus

val goals = Slide(
    name = "goals",
    config = { KodeinAttrs(workInProgress = true) },
    stateCount = 3
) { state ->
    H2 { Text("What do we need?") }
    H4 {
        Ul {
            Li({ shownIf(state > 0, Transitions.fade) }) { Text("readable and typed API") }
            Li({ shownIf(state > 1, Transitions.fade) }) { Text("ability to check the dependency graph") }
        }
    }
} + listOf(
    Slide(
        name = "init:context",
    ) {
        SourceCode(
            lang = "kotlin", code = """
            interface AppDependencies {
                fun breweriesService(basUrl: String): BreweriesService 
                fun breweriesController(): BreweriesController 
            }
        """.trimIndent()
        )
    },
    Slide(
        name = "init:annotation",
    ) {
        SourceCode(
            lang = "kotlin", code = """
            @Retention(AnnotationRetention.SOURCE)
            @Target(AnnotationTarget.CLASS)
            @MustBeDocumented
            public annotation class Resolved
        """.trimIndent()
        )
    },
).animatedWith(Animations.Move(towards = Animations.Move.Towards.Bottom))

