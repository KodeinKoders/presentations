package slides

import net.kodein.pres.Animations
import net.kodein.pres.Slide
import net.kodein.pres.Transitions
import net.kodein.pres.animatedWith
import net.kodein.pres.sourcecode.SourceCode
import net.kodein.pres.sourcecode.fontGrow
import net.kodein.pres.sourcecode.lineHeight
import net.kodein.pres.sourcecode.shownIf
import org.jetbrains.compose.web.dom.Br
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.Text

val testing = listOf(
    Slide(
        name = "step1:howto-test",
    ) {
        H2 {
            Text("How to test our")
            Br {}
            Text("dependency graph?")
        }
    },
    Slide(
        name = "step1:resolver",
    ) {
        SourceCode(
            lang = "kotlin", code = """
            public interface DIResolver {
                public fun check()
            }
        """.trimIndent()
        )
    },
    Slide(
        name = "step1:implement",
    ) {
        SourceCode(
            lang = "kotlin", code = """
            @Resolved
            interface AppDependencies : DIResolver {
                fun breweriesService(«f-param:basUrl: String»): BreweriesService 
                fun breweriesController(): BreweriesController 
            }
        """.trimIndent()
        )
    },
    Slide(
        name = "step1:check",
        stateCount = 4
    ) { state ->
        SourceCode(
            lang = "kotlin", code = """
            /* Generated in build/generated/ksp */
            class GeneratedAppDependencies(val di: DI): AppDependencies { 
                // accessors
                «override:    override fun check() {«req1:        require(di.hasFactory<String, BreweriesService>()) {
                       "Missing binding for [BreweriesService] ..."
                    }»«req2:        require(di.hasFactory<Unit, BreweriesController>()) {
                       "Missing binding for [BreweriesController] ..."
                    }»    } 
            »}
        """.trimIndent()
        ) {
            "override" { lineHeight(state > 0) }
            "req1" { lineHeight(state > 1) }
            "req2" { lineHeight(state > 2) }
        }
    },
    Slide(
        name = "step1:test",
        stateCount = 5
    ) { state ->
        SourceCode(
            lang = "kotlin", code = """
            /* In your code */
            val di = DI {
                bindFactory { url: String -> BreweriesService(url)  }
                bindSingleton { BreweriesController(instance())  }
            }
            «test:
            @Test
            fun `check dependency graph`() «eq:=»
                «eq:di.newAppDependencies()»«check:.check()»
            »«error:
            /* 
            java.lang.IllegalArgumentException: 
                Missing binding for [BreweriesService] + [String argument] 
                    while checking AppDependencies consistency.
            */»
        """.trimIndent()
        ) {
            "test" { lineHeight(state > 0 )}
            "eq" { shownIf(state > 1, Transitions.fade )}
            "check" { fontGrow(state > 2 )}
            "error" { lineHeight(state > 3 )}
        }
    },
).animatedWith(Animations.Move(towards = Animations.Move.Towards.Bottom))