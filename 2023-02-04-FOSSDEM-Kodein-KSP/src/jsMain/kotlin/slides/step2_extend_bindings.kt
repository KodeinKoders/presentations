package slides

import net.kodein.pres.Animations
import net.kodein.pres.Slide
import net.kodein.pres.animatedWith
import net.kodein.pres.sourcecode.SourceCode
import net.kodein.pres.sourcecode.fontGrow
import net.kodein.pres.sourcecode.lineHeight
import net.kodein.pres.sourcecode.zoomed
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.Text

val extendedBindings = listOf(
    Slide(
        name = "binding-title",
    ) {
        H2 { Text("One more thing...") }
    },
    Slide(
        name = "binding-ctx",
    ) {
        SourceCode(
            lang = "kotlin",
            code = """
            @Resolved
            interface AppDependencies : DIResolver {
                fun breweriesService(): BreweriesService 
                fun breweriesUseCase(): BreweriesUseCase 
                fun breweriesController(): BreweriesController 
            }
            """.trimIndent()
        )
    },
    Slide(
        name = "extended-binding",
        stateCount = 8
    ) { state ->
        SourceCode(
            lang = "kotlin",
            code = """
            «z3:@Resolved»
            interface AppDependencies : DIResolver { ... }

            /* Binding declaration */
            val «z3:«val-out:di»«val-in:appDependencies» = DI«of:.ofAppDependencies» {»«ctx:«z3:
                // 'this' «ctx-out:needs to know about AppDependencies»«ctx-in:: DI & AppDependencies»
                »»    bindSingleton { BreweriesService() }
                bindSingleton { BreweriesUseCase(«z1:«gen:instance«arg:<BreweriesService>»()»»«z2:«typed:breweriesServices()»») }
                bindSingleton { BreweriesController(«z1:«gen:instance«arg:<BreweriesUseCase>»()»»«z2:«typed:breweriesUseCase()»») }
            }
            «test:
            /* Test */
            appDependencies.check()
            »«use:
            /* In app */
            appDependencies.breweriesController()
            """.trimIndent()
        ) {
            "gen" { fontGrow(state < 3) }
            "z1" { zoomed(state in 1..2) }
            "arg" { fontGrow(state > 1) }
            "typed" { fontGrow(state > 2) }
            "z2" { zoomed(state == 3) }
            "ctx" { lineHeight(state > 3) }
            "itf" { lineHeight(state <= 4) }
            "z3" { zoomed(state == 5) }
            "of" { fontGrow(state > 4) }
            "val-out" { fontGrow(state <= 4) }
            "val-in" { fontGrow(state > 4) }
            "ctx-out" { fontGrow(state <= 4) }
            "ctx-in" { fontGrow(state > 4) }
            "test" { fontGrow(state > 5) }
            "use" { fontGrow(state > 6) }
        }
    },
).animatedWith(Animations.Move(towards = Animations.Move.Towards.Bottom))
