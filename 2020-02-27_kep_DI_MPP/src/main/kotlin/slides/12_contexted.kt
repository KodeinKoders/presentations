package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.ms
import kotlinx.css.properties.transition
import react.child
import react.functionalComponent
import styled.css
import styled.styledDiv
import styled.styledImg
import ws.kpres.PresentationBuilder
import ws.kpres.SlideContentProps
import ws.kpres.SlideInfos
import ws.utils.*

private val ContextBinding1 by functionalComponent<SlideContentProps> { props ->
    kotlinSourceCode("""
    bind<CoffeeMaker>() with «context«contexted<() -> Ration>().provider»«multiton«multiton» {«param« 
        (ration: () -> Ration) ->»
            CoffeeMaker(instance(), instance(), instance(), «param-line«ration»«context-param«context») 
    }«usage«
    
    // Usage
    val coffeeProvider: () -> Coffee by provider()
    val coffeeMaker: CoffeeMaker by on(coffeeProvider).instance()
  
    coffeeMaker.brew()»«result«
    
    // <Creating ElectricHeater>
    // <Creating Thermosiphon>
    // <Creating CoffeeMaker>
    // ~ ~ ~ heating ~ ~ ~
    // => => pumping => =>
    // <Creating CoffeeRation>
    // [_]P coffee [_]P
    // . . . cooling . . .
    »
    """.trimIndent()) {
        val currentState = props.state
        +"c-context" {
            lineEffectFrom(currentState, 1)
            highlightOn(currentState, 1, Palette.orange)
        }
        +"c-multiton" { lineEffectTo(currentState, 1) }
        +"c-param" { blockEffectTo(currentState, 2) }
        +"c-param-line" { lineEffectTo(currentState, 2) }
        +"c-context-param" {
            lineEffectFrom(currentState, 2)
            highlightOn(currentState, 2, Palette.orange)
        }
        +"c-usage" { blockEffectFrom(currentState, 3) }
        +"c-result" { blockEffectOn(currentState, 4) }
    }
}
private val ContextBinding2 by functionalComponent<SlideContentProps> { props ->
    styledDiv {
        css {
            display = Display.flex
            alignItems = Align.center
            height = 20.pct
        }

        styledImg(src = "images/warning.png") {
            css {
                height = 3.em
            }
        }
    }

    kotlinSourceCode("""
    // Binding
    bind<CoffeeMaker>() with contexted<() -> Ration>().«provider«provider» {...}
    // Usage
    val coffeeProvider: () -> Coffee by provider()«inject«
    val coffeeMaker1: CoffeeMaker by on(coffeeProvider).«provider«instance»()
    val coffeeMaker2: CoffeeMaker by on(coffeeProvider).«provider«instance»()
    
    »«print«println("coffeeMaker1:/$/coffeeMaker1")
    println("coffeeMaker2:/$/coffeeMaker2")
    
    »«result«/*
    <Creating ElectricHeater>
    <Creating Thermosiphon>
    <Creating CoffeeMaker>
    coffeeMaker1: o.k.d.s.c.«instance«CoffeeMaker@16c0663d»
    <Creating CoffeeMaker>
    coffeeMaker1: o.k.d.s.c.«instance«CoffeeMaker@23223dd8»
    */»
    """.trimIndent()) {
        val currentState = props.state
        +"c-provider" { highlightOnRange(currentState, 1..2, Palette.orange) }
        +"c-inject" { blockEffectFrom(currentState, 1) }
        +"c-print" { lineEffectFrom(currentState, 2) }
        +"c-result" { blockEffectFrom(currentState, 3) }
        +"c-instance" { highlightOn(currentState, 3, Palette.orange) }
    }
}
private val ContextBinding3 by functionalComponent<SlideContentProps> { props ->
    kotlinSourceCode("""
    class Application(private val _di: DI) : DIAware {
        override val di = _di«di-context«
        override val diContext = diContext(«whatever«coffeeProvider»)
        »
        val coffeeMaker: CoffeeMaker by «on-context«on(«whatever«coffeeProvider»).»instance()
        
        init {
            coffeeMaker.brew()
        }
    }
    """.trimIndent()) {
        val currentState = props.state
        +"c-di-context" { blockEffectFrom(currentState, 1) }
        +"c-whatever" { highlightOn(currentState, 1, Palette.orange) }
        +"c-on-context" { lineEffectTo(currentState, 2) }
    }
}

fun PresentationBuilder.contexted() {
    slide {
        slideTitle("[Advanced Mode]")
        slideTitle("_ context binding _")
    }
    slide(SlideInfos(5)) { child(ContextBinding1, it) }
    slide(SlideInfos(4)) { child(ContextBinding2, it) }
    slide(SlideInfos(3)) { child(ContextBinding3, it) }
}