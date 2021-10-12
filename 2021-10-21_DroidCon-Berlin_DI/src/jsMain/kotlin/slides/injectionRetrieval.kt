package slides

import net.kodein.pres.Slide
import net.kodein.pres.Transitions
import net.kodein.pres.shownIf
import net.kodein.pres.sourcecode.SourceCode
import net.kodein.pres.sourcecode.fontGrow
import net.kodein.pres.sourcecode.lineHeight
import net.kodein.pres.sourcecode.zoomed
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import kotlin.time.Duration

val injectionRetrievalSlides = listOf(
    Slide(
        name = "injection-retrieval",
        stateCount = 3,
    ) { state ->
        H3({ shownIf(state >= 1, Transitions.fade) }) {
            B { Text("Injected") }
            Text(" as constructor's parameter(s)")
        }
        H4({ shownIf(state >= 2, Transitions.fade) }) {
            Text("or")
        }
        H3({ shownIf(state >= 2, Transitions.Fade(Duration.Companion.seconds(1))) }) {
            Text("Lazily ")
            B { Text("retrieved") }
            Text(" at call site")
        }
    },

    Slide( name = "injection", stateCount = 6) { state ->
        SourceCode(
            lang = "kotlin",
            code = """
            class PersonRepository(«empty:) { /* ... */ }»
            «db:    private val db: DB,
            »«service:    private val service: PersonService,
            »«db:) { /* ... */ }»«container:
            
            DI {
                bindSingleton { DB.open("personDB") }
                bindSingleton { PersonService() }
                «repo:bindSingleton { PersonRepository(«what:?»«inject:instance(), instance()») }»
            }»
        """.trimIndent(),
        ) {
            "empty" { fontGrow(state < 1) }
            "db" { lineHeight(state >= 1) }
            "service" { lineHeight(state >= 2) }
            "container" { lineHeight(state >= 3) }
            "repo" { zoomed(state == 4) }
            "what" { fontGrow(state <= 4) }
            "inject" { fontGrow(state >= 5) }
        }
    },

    Slide(name = "retrieval", stateCount = 10) { state ->
        Div({
            shownIf(state > 0, Transitions.fade)
        }) {
            SourceCode(
                lang = "kotlin",
                code = """
                class PersonRepository(
                «inject:    private val db: DB, 
                    private val service: PersonService
                »«override:     override val di: DI
                »)«diaware:: DIAware» {
                «comment:    /* ... */
                }»«retrieve:    private val db: DB by instance()
                    private val service: PersonService by instance()
                »«eoc:}»«fun:«lazy:    fun saveUser(person: Person) = db.put(person)»
                }»«container:
            
                DI {
                    bindSingleton { DB.open("personDB") }
                    bindSingleton { PersonService() }
                    «repo:bindSingleton { PersonRepository(«what:?»«passdi:di») }»
                }»
        """.trimIndent(),
            ) {
                "diaware" { fontGrow(state >= 2) }
                "inject" { lineHeight(state < 3) }
                "override" { lineHeight(state >= 3) }
                "comment" { lineHeight(state < 4) }
                "retrieve" { lineHeight(state >= 4) }
                "eoc" { fontGrow(state == 4) }
                "fun" { lineHeight(state >= 5) }
                "lazy" { zoomed(state == 6) }
                "container" { lineHeight(state >= 7) }
                "what" { fontGrow(state <= 7) }
                "passdi" { fontGrow(state >= 8) }
                "repo" { zoomed(state == 8) }
            }
        }
    }
)