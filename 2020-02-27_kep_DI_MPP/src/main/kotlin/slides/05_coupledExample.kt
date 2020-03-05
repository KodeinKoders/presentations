package ws.slides

import kotlinx.css.Color
import kotlinx.css.color
import react.child
import react.functionalComponent
import ws.kpres.PresentationBuilder
import ws.kpres.SlideContentProps
import ws.kpres.SlideInfos
import ws.utils.*

private val Slide1 by functionalComponent<SlideContentProps> { props ->
    kotlinSourceCode("""
    class CoffeeMaker(«main« ... )»«out«
            private val logger: CommonLogger,
            private val heater: Heater,
            private val pump: Pump,
            private var ration: () -> Ration
    ) {
        fun brew() {
            heater.on()
    
            val ration = ration()
            pump.pumpWater()
            logger.log("[_]P /$/{ration.name()} [_]P")
    
            heater.off()
        }
    }»
    «main«
    fun main() {
        val coffeeMaker = 
                CoffeeMaker(${compileError(1)})
        
        coffeeMaker.brew()
    }»
    """.trimIndent())
    {
        val currentState = props.state
        +"c-out" { lineEffect(currentState, 0..0) }
        +"c-main" { lineEffectFrom(currentState, 1) }
        +"c-err1" { orangeHighlight(currentState, 2) }
    }
}
private val Slide2 by functionalComponent<SlideContentProps> { props ->
    kotlinSourceCode("""
    «out«class CoffeeMaker( ... )
    
    »fun main() {
        val coffeeMaker = 
                CoffeeMaker(ConsoleLogger(), 
                            ElectricHeater(${compileError(1)}), 
                            Thermosiphon(${compileError(1)}), 
                            { Coffee(${compileError(1)}) })
                                        
        coffeeMaker.brew()
    }
    """.trimIndent())
    {
        +"c-out" { lineEffect(props.state, 0..0) }
        +"c-err1" { orangeHighlight(props.state, 1) }
    }
}
private val Slide3 by functionalComponent<SlideContentProps> { props ->
    kotlinSourceCode("""
    fun main() {
        val logger = ConsoleLogger()
        val heater = ElectricHeater(${compileError(1)})
        val pump = Thermosiphon(${compileError(1)})
        val coffee = { Coffee(${compileError(1)}) }
        
        val coffeeMaker = 
                CoffeeMaker(«in«logger,heater»,
                            «in«pump, coffee») 
                            
        coffeeMaker.brew()
    }
    """.trimIndent())
    {
        +"c-in" { orangeHighlight(props.state, 0) }
        +"c-err1" { orangeHighlight(props.state, 0) }
    }
}
private val Slide4 by functionalComponent<SlideContentProps> { props ->
    kotlinSourceCode("""
    fun main() {
        val logger = ConsoleLogger()
        val heater = ElectricHeater(«logger«logger»)
        val pump = Thermosiphon(«logger«logger, heater»)
        val coffee = { Coffee(«logger«logger») }
    
        val coffeeMaker = 
                CoffeeMaker(logger, heater, 
                            pump, coffee)
        
        «brew«// MAKE ME MY DAMN COFFEE!!!»
        coffeeMaker.brew()
    }
    """.trimIndent()) {
        +"c-logger" {
            color = if (props.state == 0) Palette.orange.color else Color.black
        }
        +"c-brew" { lineEffectFrom(props.state, 0) }
    }
}
private val Slide5 by functionalComponent<SlideContentProps> { props ->
    kotlinSourceCode("""
    «bind«@Injectable »class CoffeeMaker(
            «njct«@Inject »private val logger: CommonLogger,
            «njct«@Inject »private val heater: Heater,
            «njct«@Inject »private val pump: Pump,
            «njct«@Inject »private var ration: () -> Ration
    )
    
    «bind«@Injectable »class ConsoleLogger: CommonLogger { ... }
    
    «bind«@Injectable »class ElectricHeater(
            «njct«@Inject »private val log: CommonLogger
    ) : Heater { ... }
    
    «bind«@Injectable »class Thermosiphon(
            «njct«@Inject »private val log: CommonLogger,
            «njct«@Inject »private val heater: Heater
    ) : Pump { ... }
    """.trimIndent()) {
        +"c-bind" { lineEffectFrom(props.state, 1) }
        +"c-njct" { lineEffectFrom(props.state, 2) }
    }
}
private val Slide6 by functionalComponent<SlideContentProps> { props ->
    kotlinSourceCode("""
    fun main() {
        @Inject val coffeeMaker: CoffeeMaker
        
        // Ah! That smells good!
        coffeeMaker.brew()
    }
    """.trimIndent())
}

fun PresentationBuilder.coupledCode() {
    fun fastTransition(i: Int) = SlideInfos(
            stateCount = i,
            inTransitionDuration = 0
    )

    slide { slideTitle("Coupled architecture") }
    slide(fastTransition(3)) { child(Slide1, it) }
    slide(fastTransition(2)) { child(Slide2, it) }
    slide(fastTransition(1)) { child(Slide3, it) }
    slide(fastTransition(1)) { child(Slide4, it) }
    slide(SlideInfos(3)) { child(Slide5, it) }
    slide { child(Slide6, it) }
}