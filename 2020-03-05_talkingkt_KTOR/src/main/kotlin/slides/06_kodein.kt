package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.ms
import kotlinx.css.properties.transition
import react.child
import react.functionalComponent
import styled.*
import ws.comp.logo
import ws.kpres.Flip
import ws.kpres.PresentationBuilder
import ws.kpres.SlideContentProps
import ws.kpres.SlideInfos
import ws.utils.*

private val infos = SlideInfos(
        containerStyle = {
            ".inner-container" {
                backgroundColor = Color("#EB5A44")
                transition(::background, 1000.ms)
            }
        },
        outTransitions = Flip
)

private fun PresentationBuilder.whatIsKodeinDI() = slide(infos) {
    logo(division = {
        styledSpan {
            css {
                verticalAlign = VerticalAlign.middle
            }
            +"DI"
        }
    }, zoom = 0.8) {
        styledP {
            css {
                padding(0.em)
                margin(0.em)
            }
            +"painless "
            styledImg(src = "images/kotlin-white.svg") {
                css { height = 0.65.em }
            }
            +" "
            styledSpan {
                css {
                    transition(::fontSize, 500.ms)
                    verticalAlign = VerticalAlign.middle
                }
                +"Dependency Injection"
            }
        }
    }
}

private val CustomFeature by functionalComponent<SlideContentProps> { props ->
    titledContent("Custom feature") {
        kotlinSourceCode("""
        fun Application.diModule() {«feature«
            «feature-in«di»«feature-out«install(DIFeature)» {«bind«
                bind() from singleton { UserService() }    
                bind() from singleton { ItemService() }    
            »}
        »}«usage«
        
        // Usage
        val itemService: ItemService by instance()»
        """.trimIndent()) {
            +"c-feature" { blockEffectFrom(props.state, 1) }
            +"c-bind" { blockEffectFrom(props.state, 2) }
            +"c-feature-in" {
                lineEffectFrom(props.state, 3)
                highlightOn(props.state, 3, Palette.orange)
            }
            +"c-feature-out" { lineEffectTo(props.state, 3) }
            +"c-usage" { blockEffectFrom(props.state, 4) }
        }
    }
}
private val ClosestPattern by functionalComponent<SlideContentProps> { props ->
    titledContent("Closest DI pattern") {
        kotlinSourceCode("""
        fun «cp-5«Application».diModule() {    
            «cp-5«di» {        
                bind<ItemService>() with singleton { BasicItemService() }    
            }
        }«route«
        fun Application.routeModule() {    
            «cp-4«routing» {
                «cp-3«route(...)» {
                    «cp-2«route(...)» {
                        val itemService: ItemService by «cp-1«di()».instance()
                    }
                }
            }
        }» 
        """.trimIndent()) {
            +"c-route" { blockEffectFrom(props.state, 1) }
            +"c-cp-1" { highlightOnRange(props.state, 2..6, Palette.orange) }
            +"c-cp-2" {
                highlightOnRange(props.state, 3..6, Palette.orange)
            }
            +"c-cp-3" { highlightOnRange(props.state, 4..6, Palette.orange) }
            +"c-cp-4" { highlightOnRange(props.state, 5..6, Palette.orange) }
            +"c-cp-5" { highlightOn(props.state, 6, Palette.orange) }
        }
    }
}
private val KtorScopes by functionalComponent<SlideContentProps> { props ->
    titledContent("Ktor Scopes") {
        bulletList(props) {
            bulletCode(props.state, 1, "Session Scope", "kotlin",
            """
            // Bindings
            bind() from scoped(SessionScope).singleton { 
                SessionItemService() 
            }     
            // Retrieval
            route(...) {
                val session = call.sessions.get<UserSession>()
                val itemService: SessionItemService 
                        by di().on(session).instance()
            }
            """.trimIndent())
            bulletCode(props.state, 2, "Request Scope", "kotlin",
            """
            // Bindings
            bind() from scoped(CallScope).singleton { 
                RequestItemService() 
            }
            // Retrieval
            route(...) {
                val itemService: RequestItemService 
                    by di().on(applicationCall).instance()
            }
            """.trimIndent())
        }
    }
}

private val DIController by functionalComponent<SlideContentProps> { props ->
    titledContent("DI Aware Controller") {
        kotlinSourceCode("""
        class MyController(«app-line-in«application: Application») «aware-line«: DIController »{«aware-block«
            override val di «app-line-out«= ?»«app-line-in«by di { application }»
            »«repo«private val repository: DataRepository by instance()»«aware-block«    
            
            override fun Routing.installRoutes() {        
                get("/version") {            
                    val version: String by constant()            
                    call.respondText(version)
                }    
            }
        »}«usage«
        // Usage
        fun Application.routeModule() {
            routing {«controller«
                controller { MyController(instance()) }»
                «protected«route("/protected") {
                    controller { MyController(instance()) }
                }
            »}
        }»
        """.trimIndent()) {
            val currentSate = props.state
            +"c-aware-line" {
                lineEffectFrom(currentSate, 1)
                highlightOn(currentSate, 1)
            }
            +"c-aware-block" { blockEffectFrom(currentSate, 2) }
            +"c-app-line-in" {
                lineEffectFrom(currentSate, 3)
                highlightOn(currentSate, 3)
            }
            +"c-app-line-out" { lineEffect(currentSate, 1..2) }
            +"c-repo" { blockEffectFrom(currentSate, 4) }
            +"c-usage" { blockEffectFrom(currentSate, 5) }
            +"c-controller" { blockEffect(currentSate, 6..6) }
            +"c-protected" { blockEffectFrom(currentSate, 7) }
        }
    }
}

fun PresentationBuilder.di() {
    whatIsKodeinDI()
    slide { slideTitle("Dependency Injection for Ktor") }
    slide(SlideInfos(5)) { child(CustomFeature, it) }
    slide(SlideInfos(7)) { child(ClosestPattern, it) }
    slide(SlideInfos(3)) { child(KtorScopes, it) }
    slide { slideTitle("Isolating your endpoints logic") }
    slide(SlideInfos(8)) { child(DIController, it) }
    slide { showCode() }
}
