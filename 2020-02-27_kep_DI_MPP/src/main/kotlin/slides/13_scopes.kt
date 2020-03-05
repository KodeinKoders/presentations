package ws.slides

import react.child
import react.functionalComponent
import ws.kpres.PresentationBuilder
import ws.kpres.SlideContentProps
import ws.kpres.SlideInfos
import ws.utils.*

private val ServiceScope by functionalComponent<SlideContentProps> { props ->
    kotlinSourceCode("""
    «enum«enum class Service { HR, IT }
    
    »«scope«object ServiceScope«obj-out« {»«obj-override« : «obj-impl«Scope<Service>» {»«impl«
        private val «map«mapRegistry» = HashMap<Service, ScopeRegistry>()

        override fun getRegistry(context: Service): ScopeRegistry {
            return «get«mapRegistry[context]» ?: run {
                val scopeRegistry = StandardScopeRegistry()
                «new«mapRegistry[context] = scopeRegistry»
                «return«scopeRegistry»
            }
        }
    »}»
    """.trimIndent()) {
        val currentState = props.state
        +"c-enum" { blockEffectFrom(currentState, 1) }
        +"c-scope" { blockEffectFrom(currentState, 2) }
        +"c-obj-out" { lineEffectTo(currentState, 3) }
        +"c-obj-override" { lineEffectFrom(currentState, 3) }
        +"c-obj-impl" { highlightOn(currentState, 4, Palette.orange) }
        +"c-impl" { blockEffectFrom(currentState, 4) }
        +"c-map" { highlightOnRange(currentState, 5..6, Palette.orange) }
        +"c-get" { highlightOn(currentState, 5, Palette.orange) }
        +"c-new" { highlightOn(currentState, 6, Palette.orange) }
        +"c-return" { highlightOn(currentState, 7, Palette.orange) }
    }
}
private val ScopeBinding by functionalComponent<SlideContentProps> { props ->
    kotlinSourceCode("""
    // Binding
    bind<CoffeeMaker>() with «scope«scoped(ServiceScope).»singleton {
        CoffeeMaker(instance(), instance(), instance(), provider())
    }«usage«
    
    // Usage
    val coffeeMaker1: CoffeeMaker by «on-scope«on(Service.HR).»instance()
    val coffeeMaker2: CoffeeMaker by «on-scope«on(Service.IT).»instance()
    val coffeeMaker3: CoffeeMaker by «on-scope«on(Service.HR).»instance()

    println("coffeeMaker1«on-scope« [HR]»: /$/coffeeMaker1")
    println("coffeeMaker2«on-scope« [IT]»: /$/coffeeMaker2")
    println("coffeeMaker3«on-scope« [HR]»: /$/coffeeMaker3")»«result«
    
    /*
    <Creating ElectricHeater>
    <Creating Thermosiphon>
    <Creating CoffeeMaker>
    coffeeMaker1 [HR]: o.k.d.s.c.CoffeeMaker@2d554825
    <Creating CoffeeMaker>
    coffeeMaker2 [IT]: o.k.d.s.c.CoffeeMaker@6be46e8f
    coffeeMaker3 [HR]: o.k.d.s.c.CoffeeMaker@2d554825
    */
    »
    """.trimIndent()) {
        val currentState = props.state
        +"c-scope" {
            lineEffectFrom(currentState, 1)
            highlightOn(currentState, 1, Palette.orange)
        }
        +"c-usage" { blockEffectFrom(currentState, 2) }
        +"c-on-scope" {
            lineEffectFrom(currentState, 3)
            highlightOn(currentState, 3, Palette.orange)
        }
        +"c-result" { blockEffectOn(currentState, 4) }
    }
}

fun PresentationBuilder.scopes() {
    slide {
        slideTitle("[Advanced Mode]")
        slideTitle("_ scopes _")
    }
    slide(SlideInfos(8)) { child(ServiceScope, it) }
    slide(SlideInfos(5)) { child(ScopeBinding, it) }
}