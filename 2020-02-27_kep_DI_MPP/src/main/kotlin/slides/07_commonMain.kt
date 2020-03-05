package ws.slides

import kotlinx.css.Color
import kotlinx.css.color
import kotlinx.css.properties.ms
import kotlinx.css.properties.transition
import react.child
import react.functionalComponent
import ws.kpres.Fade
import ws.kpres.PresentationBuilder
import ws.kpres.SlideContentProps
import ws.kpres.SlideInfos
import ws.utils.*

private val SimpleDISlide1 by functionalComponent<SlideContentProps> { props ->
    kotlinSourceCode("""
    val simpleContainer = DI {
        «bind-1««with«bind<CommonLogger>() with»«from«bind() from» singleton { ConsoleLogger() }»
        «bind-2««with«bind<Pump>() with»«from«bind() from» singleton { 
            Thermosiphon(${compileError(1)}) 
        }»
        «bind-3««with«bind<Heater>() with»«from«bind() from» singleton { ElectricHeater(${compileError(1)}) }»
        «bind-4««with«bind<Ration>() with»«from«bind() from» provider { Coffee(${compileError(1)}) }»
        «bind-5«
        «with«bind<CoffeeMaker>() with»«from«bind() from» singleton { 
            CoffeeMaker(${compileError(1)}) 
        }»
    }
    """.trimIndent()) {
        val currentState = props.state
        +"c-bind-1" { blockEffectFrom(currentState, 1) }
        +"c-bind-2" { blockEffectFrom(currentState, 2) }
        +"c-bind-3" { blockEffectFrom(currentState, 3) }
        +"c-bind-4" { blockEffectFrom(currentState, 4) }
        +"c-bind-5" { blockEffectFrom(currentState, 5) }
        +"c-with" { lineEffectTo(currentState, 6) }
        +"c-from" { lineEffectFrom(currentState, 6) }
        +"c-err1" { orangeHighlight(currentState, 7) }
    }
}
private val SimpleDISlide2 by functionalComponent<SlideContentProps> { props ->
    kotlinSourceCode("""
    val simpleContainer = DI {
        bind() from singleton { ConsoleLogger() }
        bind() from singleton { 
          Thermosiphon(${compileError(1)}«instance«instance()», ${compileError(1)}«instance«instance()») 
        }
        bind() from singleton { ElectricHeater(${compileError(1)}«instance«instance()») }
        bind() from «provider«provider» { Coffee(${compileError(1)}«instance«instance()») }
        
        bind() from singleton { 
          CoffeeMaker(${compileError(1)}«instance«instance()», ${compileError(1)}«instance«instance()», ${compileError(1)}«instance«instance()», ${compileError(1)}«instance«instance()») 
        }
    }
    """.trimIndent()) {
        val currentState = props.state
        +"c-err1" { orangeHighlight(currentState, 0) }
        +"c-instance" {
            lineEffectFrom(currentState, 1)
            color = if (currentState == 1) Palette.orange.color else Color.black
        }
    }
}

private val DIModulesSlide1 by functionalComponent<SlideContentProps> { props ->
    kotlinSourceCode("""
    val thermosiphonModule = DI.Module("Thermosiphon")«dot« { ... }»«pump« {
        bind() from singleton { 
            Thermosiphon(instance(), instance())
        }
    }
    """.trimIndent()) {
        val currentState = props.state
        + "c-pump" { blockEffectOn(currentState, 1)}
        + "c-dot" { lineEffectFrom(currentState, 2)}
    }
}
private val DIModulesSlide2 by functionalComponent<SlideContentProps> { props ->
    kotlinSourceCode("""
    val thermosiphonModule = DI.Module("Thermosiphon") { ... }
    val electricHeaterModule = DI.Module("Electric Heater")«dot« { ... }»«heater« {
        bind<Heater>() with singleton { ElectricHeater(instance()) }
    }»
    """.trimIndent()) {
        val currentState = props.state
        + "c-heater" { blockEffectOn(currentState, 1)}
        + "c-dot" { lineEffectFrom(currentState, 2)}
    }
}
private val DIModulesSlide3 by functionalComponent<SlideContentProps> { props ->
    kotlinSourceCode("""
    val thermosiphonModule = DI.Module("Thermosiphon") { ... }
    val electricHeaterModule = DI.Module("Electric Heater") { ... }«di«
    
    val modularizedContainer = DI {«bind-out«
        bind() from singleton { Thermosiphon(instance(), instance()) }
        bind() from singleton { ElectricHeater(instance()) }
        »bind() from provider { Coffee(instance()) }
        bind() from singleton { 
          CoffeeMaker(instance(), instance(), instance(), instance()) 
        }
        bind() from singleton { ConsoleLogger(instance()) }
    }
    »""".trimIndent()) {
        val currentState = props.state
        + "c-di" { blockEffectFrom(currentState, 1)}
        + "c-bind-out" { blockEffectOn(currentState, 1)}
    }
}
private val DIModulesSlide4 by functionalComponent<SlideContentProps> { props ->
    kotlinSourceCode("""
    val thermosiphonModule = DI.Module("Thermosiphon") { ... }
    val electricHeaterModule = DI.Module("Electric Heater") { ... }
    
    val modularizedContainer = DI {«import«
        import(thermosiphonModule)
        import(electricHeaterModule)»«importAll«
        importAll(thermosiphonModule, electricHeaterModule)
        »
        bind() from provider { Coffee(instance()) }
        bind() from singleton { 
          CoffeeMaker(instance(), instance(), instance(), instance()) 
        }
        bind() from singleton { ConsoleLogger(instance()) }
    }
    »""".trimIndent()) {
        val currentState = props.state
        + "c-import" {
            color = Palette.orange.color
            blockEffectTo(currentState, 1)
        }
        + "c-importAll" {
            color = Palette.orange.color
            lineEffectFrom(currentState, 1)
        }
    }
}

fun PresentationBuilder.commonMain() {
    slide { slideTitle("Solving the Coffee Maker problem") }
    slide { slideTitle("The simplest way") }
    slide(SlideInfos(7, outTransitions = Fade)) {
        child(SimpleDISlide1, it)
    }
    slide(SlideInfos(2, inTransitions = Fade, inTransitionDuration = 0)) {
        child(SimpleDISlide2, it)
    }
    slide { slideTitle("Modularize your app") }
    slide(SlideInfos(3, outTransitions = Fade)) {
        child(DIModulesSlide1, it)
    }
    slide(SlideInfos(3, inTransitions = Fade, inTransitionDuration = 0)) {
        child(DIModulesSlide2, it)
    }
    slide(SlideInfos(2, inTransitions = Fade, inTransitionDuration = 0)) {
        child(DIModulesSlide3, it)
    }
    slide(SlideInfos(2, inTransitions = Fade, inTransitionDuration = 0)) {
        child(DIModulesSlide4, it)
    }
}

