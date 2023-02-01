package slides

import net.kodein.pres.Animations
import net.kodein.pres.Slide
import net.kodein.pres.animatedWith
import net.kodein.pres.sourcecode.SourceCode
import net.kodein.pres.sourcecode.fontGrow
import net.kodein.pres.sourcecode.lineHeight
import net.kodein.pres.sourcecode.zoomed

val typed = listOf(
    Slide(
        name = "step1:annotate",
        stateCount = 3,
    ) { state ->
        SourceCode(
            lang = "kotlin", code = """
            @Resolved
            interface AppDependencies {
                «f:// This is a factory (String) -> BreweriesService»
                fun breweriesService(«f-param:basUrl: String»): BreweriesService 
                «p:// This is a provider (or singleton)»
                fun breweriesController(): BreweriesController 
            }
        """.trimIndent()
        ) {
            "f" { fontGrow(state > 0) }
            "p" { fontGrow(state > 1) }
        }
    },
    Slide(
        name = "step1:generate",
        stateCount = 4,
    ) { state ->
        SourceCode(
            lang = "kotlin", code = """
        /* Generated in build/generated/ksp */
        class GeneratedAppDependencies(val di: DI): AppDependencies {
            // This is a factory (String) -> BreweriesService
            fun breweriesService(«f-param:basUrl: String»): BreweriesService «f-eq:=»«f-return:       di.direct.instance<BreweriesService>(«f-param:arg = baseUrl») 
            »    // This is a provider (or singleton)
            fun breweriesController(): BreweriesController «p-eq:=»«p-return:       di.direct.instance<BreweriesController>() 
        »}
    """.trimIndent()
        ) {
            "f-eq" { fontGrow(state > 0) }
            "f-return" { lineHeight(state > 0) }
            "f-param" { zoomed(state == 2) }
            "p-eq" { fontGrow(state > 2) }
            "p-return" { lineHeight(state > 2) }
        }
    },
    Slide(
        name = "step1:use",
        stateCount = 7,
    ) { state ->
        SourceCode(
            lang = "kotlin", code = """
            /* Generated in build/generated/ksp */
            class GeneratedAppDependencies(val di: DI): AppDependencies { ... }
            «typed:
            /* In your code */
            val di = DI {
            «bind:    bindFactory { url: String -> BreweriesService(url)  }
                bindSingleton { BreweriesController(instance())  }
            »}
            
            «deps:val deps: AppDependencies = «new-in:di.newAppDependencies()»«new-out:GeneratedAppDependencies(di)»
            »«ctrl:
            val controller: BreweriesController = deps.breweriesController()
            »»
        """.trimIndent()
        ) {
            "typed" { lineHeight(state > 0) }
            "bind" { lineHeight(state > 1) }
            "deps" { lineHeight(state > 2) }
            "ctrl" { lineHeight(state > 3) }
            "new-out" { fontGrow(state <= 4) }
            "new-in" { fontGrow(state > 4) }
            "new-in" { zoomed(state > 5) }
        }
    }
).animatedWith(Animations.Move(towards = Animations.Move.Towards.Bottom))
