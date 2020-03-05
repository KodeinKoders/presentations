package ws.slides

import kotlinx.css.*
import react.child
import react.dom.h3
import react.dom.h4
import react.functionalComponent
import styled.*
import ws.kpres.PresentationBuilder
import ws.kpres.SlideContentProps
import ws.kpres.SlideInfos
import ws.kpres.sourceCode
import ws.utils.*

private val TestLogger by functionalComponent<SlideContentProps> { props ->
    styledDiv {
        css {
            display = Display.flex
            flexDirection = FlexDirection.column
            alignItems = Align.center
            height = 100.pct
            width = 100.pct
        }

        styledSpan {
            css {
                height = 10.pct
                width = 100.pct
            }
            +"Testing your DI container"
        }
        styledDiv {
            css {
                height = 80.pct
                width = 100.pct
            }
            kotlinSourceCode("""
    class TestLogger : CommonLogger {
        var trace: String = ""
            private set

        override fun log(msg: String) {
            trace += "/$/msg\n"
        }
    }«di«
    // Reminder
    val modularizedContainer = DI {
        importAll(thermosiphonModule, electricHeaterModule)
        
        bind() from provider { Coffee(instance()) }
        bind() from singleton { 
          CoffeeMaker(instance(), instance(), instance(), provider()) 
        }
        «exist«bind<CommonLogger>() with singleton { ConsoleLogger(instance()) }»
    }»
    """.trimIndent()) {
                +"c-di" { blockEffectFrom(props.state, 1) }
                +"c-exist" { highlightOn(props.state, 1, Palette.orange) }
            }
        }
    }
}
private val ContainerTest by functionalComponent<SlideContentProps> { props ->
    styledDiv {
        css {
            display = Display.flex
            flexDirection = FlexDirection.column
            alignItems = Align.center
            height = 100.pct
            width = 100.pct
        }

        styledSpan {
            css {
                height = 10.pct
                width = 100.pct
            }
            +"Testing your DI container"
        }
        styledDiv {
            css {
                height = 80.pct
                width = 100.pct
            }

            kotlinSourceCode("""
                class CoffeeMakerTest«di-aware-line« : DIAware» {«di-aware-block«
                    override val di: DI = underTestDI
                    »«under-test«private val underTestDI: DI = DI {
                        extend(modularizedContainer)
                        «exist«bind<CommonLogger>() with singleton { TestLogger() }»
                    }
            
                    »@Test
                    fun test_00_output() {«test«
                        val coffeeMaker: CoffeeMaker by instance()
                        val logger: CommonLogger by instance()
                        // ...
                    »}
                }«exception«
                /* DI.OverridingException: 
                        Binding bind<CommonLogger>() with ? { ? } must not  
                                override an existing binding.
                */»
                """.trimIndent()) {
                val currentState = props.state
                +"c-di-aware-line" { lineEffectFrom(currentState, 1) }
                +"c-di-aware-block" { blockEffectFrom(currentState, 1) }
                +"c-under-test" { blockEffectFrom(currentState, 2) }
                +"c-test" { blockEffectFrom(currentState, 3) }
                +"c-exist" { highlightOn(currentState, 4, Palette.orange) }
                +"c-exception" { blockEffectFrom(currentState, 4) }
            }
        }
    }

}
private val OverridingBindings by functionalComponent<SlideContentProps> { props ->
    kotlinSourceCode("""
    class CoffeeMakerTest : DIAware {«config-out«
        override val di: DI = underTestDI
        private val underTestDI: DI = DI {
            extend(modularizedContainer)
            bind<CommonLogger>(«overriding«override = true») with singleton { 
                TestLogger() 
            }
        }
        »«dot«// ...»
        @Test
        fun test_00_output() {
            val coffeeMaker: CoffeeMaker by instance()
            val logger: TestLogger by instance()«brew«
            
            coffeeMaker.brew()
    
            assertEquals('''<Creating ElectricHeater>
                        |<Creating Thermosiphon>
                        |<Creating CoffeeMaker>
                        |~ ~ ~ heating ~ ~ ~
                        |=> => pumping => =>
                        |<Creating CoffeeRation>
                        |[_]P coffee [_]P
                        |. . . cooling . . .
                        |'''.trimMargin(), 
                        «assert«logger.trace»)
        »}
    }
    """.trimIndent()) {
        val currentState = props.state
        +"c-overriding" { lineEffectFrom(currentState, 1) }
        +"c-config-out" { blockEffectTo(currentState, 2) }
        +"c-dot" { lineEffectFrom(currentState, 2) }
        +"c-brew" { blockEffectFrom(currentState, 2) }
        +"c-assert" { highlightOn(currentState, 2, Palette.orange) }
    }
}
private val BindingNotFound by functionalComponent<SlideContentProps> { props ->
    styledDiv {
        css {
            display = Display.flex
            flexDirection = FlexDirection.column
            alignItems = Align.center
            height = 100.pct
            width = 100.pct
        }

        styledSpan {
            css {
                height = 10.pct
                width = 100.pct
            }
            +"Binding Not Found"
        }
        styledDiv {
            css {
                height = 80.pct
                width = 100.pct
            }
            kotlinSourceCode("""
// Binding
val modularizedContainer = «dot«...»«binding«DI {
    importAll(thermosiphonModule, electricHeaterModule)
    
    bind() from provider { Coffee(instance()) }
    bind() from singleton { 
      CoffeeMaker(instance(), instance(), instance(), provider()) 
    }
}»
// Usage
val coffeeMaker: CoffeeMaker by instance()«exception«
/* DI.NotFoundException: 
    No binding found for bind<CommonLogger>() with ? { ? }

Registered in this DI container:
        module Common Module {
            bind<CoffeeMaker>() with singleton { CoffeeMaker }
            bind<Coffee>() with provider { Coffee }
        }
        module Electric Heater {
            bind<Heater>() with singleton { ElectricHeater }
        }
        module Thermosiphon {
            bind<Pump>() with singleton { Thermosiphon }
        }
*/»
                    """.trimIndent()) {
                val currentState = props.state
                +"c-binding" { blockEffectTo(currentState, 1) }
                +"c-dot" { lineEffectFrom(currentState, 1) }
                +"c-exception" { lineEffectFrom(currentState, 1) }
            }
        }
    }
}
private val Recursion by functionalComponent<SlideContentProps> { props ->
    styledDiv {
        css {
            display = Display.flex
            flexDirection = FlexDirection.column
            alignItems = Align.center
            height = 100.pct
            width = 100.pct
        }

        styledSpan {
            css {
                height = 10.pct
                width = 100.pct
            }
            +"Dependency recursion"
        }
        styledDiv {
            css {
                height = 80.pct
                width = 100.pct
            }
            kotlinSourceCode("""
// Use case
class A(val logger: CommonLogger)
class B(val a: A)
class TestLogger(val b: B) : CommonLogger«binding«
// Binding
bind<A>() with singleton { A(instance()) }
bind<B>() with singleton { B(instance()) }
bind<CommonLogger>() with singleton { TestLogger(instance()) }
»«exception«
/* DI.DependencyLoopException: Dependency recursion:
     bind<CommonLogger>()
    ╔╩>bind<B>()
    ║  ╚>bind<A>()
    ║    ╚>bind<CommonLogger>()
    ╚══════╝
*/»
                    """.trimIndent()) {
                val currentState = props.state
                +"c-binding" { blockEffectFrom(currentState, 1) }
                +"c-exception" { lineEffectFrom(currentState, 2) }
            }
        }
    }
}
private val Debug by functionalComponent<SlideContentProps> { props ->
    styledDiv {
        css {
            display = Display.flex
            flexDirection = FlexDirection.column
            alignItems = Align.center
            height = 100.pct
            width = 100.pct
        }

        styledSpan {
            css {
                height = 10.pct
                width = 100.pct
            }
            +"Debug your container!"
        }
        styledDiv {
            css {
                height = 80.pct
                width = 100.pct
            }
            kotlinSourceCode("""
// Binding
val modularizedContainer = «dot«...»«binding«DI {
    importAll(thermosiphonModule, electricHeaterModule)
    
    bind() from provider { Coffee(instance()) }
    bind() from singleton { 
      CoffeeMaker(instance(), instance(), instance(), provider()) 
    }
    bind<CommonLogger>() from singleton { ConsoleLogger() }
}»
// Usage
println(
    «container«modularizedContainer.container».«tree«tree».«desc«bindings.description()»
)«output«

/*
module Common Module {
    bind<CoffeeMaker>() with singleton { CoffeeMaker }
    bind<Coffee>() with provider { Coffee }
    bind<CommonLogger>() with singleton { ConsoleLogger }
}
module Electric Heater {
    bind<Heater>() with singleton { ElectricHeater }
}
module Thermosiphon {
    bind<Pump>() with singleton { Thermosiphon }
}
*/»
                    """.trimIndent()) {
                val currentState = props.state
                +"c-binding" { blockEffectTo(currentState, 4) }
                +"c-dot" { lineEffectFrom(currentState, 4) }
                +"c-container" { highlightOn(currentState, 1, Palette.orange) }
                +"c-tree" { highlightOn(currentState, 2, Palette.orange) }
                +"c-desc" { highlightOn(currentState, 3, Palette.orange) }
                +"c-output" { lineEffectFrom(currentState, 4) }
            }
        }
    }
}

fun PresentationBuilder.testAndDebug() {
    slide { slideTitle("Test and debug your DI container") }
    slide(SlideInfos(2)) { child(TestLogger, it) }
    slide(SlideInfos(5)) { child(ContainerTest, it) }
    slide(SlideInfos(3)) { child(OverridingBindings, it) }
    slide(SlideInfos(2)) { child(BindingNotFound, it) }
    slide(SlideInfos(3)) { child(Recursion, it) }
    slide(SlideInfos(5)) { child(Debug, it) }
}

