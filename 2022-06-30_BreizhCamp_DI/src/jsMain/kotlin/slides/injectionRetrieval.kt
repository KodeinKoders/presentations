package slides

import net.kodein.pres.Slide
import net.kodein.pres.Transitions
import net.kodein.pres.shownIf
import net.kodein.pres.sourcecode.SourceCode
import net.kodein.pres.sourcecode.fontGrow
import net.kodein.pres.sourcecode.lineHeight
import net.kodein.pres.sourcecode.zoomed
import org.jetbrains.compose.web.dom.B
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.H4
import org.jetbrains.compose.web.dom.Text
import kotlin.time.Duration.Companion.seconds

val injectionRetrieval = listOf(
    Slide(
        name = "injection-retrieval",
        stateCount = 3,
    ) { state ->
        H2 { Text("Dependency access:") }
        H3({ shownIf(state >= 1, Transitions.fade) }) {
            B { Text("Injected") }
            Text(" as constructor's parameter(s)")
        }
        H4({ shownIf(state >= 2, Transitions.fade) }) {
            Text("or")
        }
        H3({ shownIf(state >= 2, Transitions.Fade(1.seconds)) }) {
            Text("Lazily ")
            B { Text("retrieved") }
            Text(" at call site")
        }
    },

    Slide( name = "injection", stateCount = 7) { state ->
        SourceCode(
            lang = "kotlin",
            code = """
            class UserRepository(«empty:) { /* ... */ }»
            «db:    private val db: DB,
            »«service:    private val httpClient: HttpClient,
            »«db:) { /* ... */ }»«container:
            
            DI {
                bindSingleton<DB> { DB.open("userDB") }
                bindSingleton<HttpClient { AppHttpClient() }
                «repo:bindSingleton { «new:new(::»UserRepository«new:)»«what:(?)»«inject:(instance(), instance())» }»
            }»
        """.trimIndent(),
        ) {
            "empty" { fontGrow(state < 1) }
            "db" { lineHeight(state >= 1) }
            "service" { lineHeight(state >= 2) }
            "container" { lineHeight(state >= 3) }
            "repo" { zoomed(state == 4) }
            "what" { fontGrow(state <= 4) }
            "inject" { fontGrow(state == 5) }
            "new" { fontGrow(state >= 6) }
        }
    },

    Slide(name = "retrieval", stateCount = 9) { state ->
        SourceCode(
            lang = "kotlin",
            code = """
            class UserRepository(
            «inject:    private val db: DB, 
                private val httpClient: HttpClient
            »«override:     override val di: DI
            »)«diaware:: DIAware» {
            «comment:    /* ... */
            }»«retrieve:    private val db: DB «lazy:by» instance()
                private val httpClient: HttpClient «lazy:by» instance()
            »«eoc:}»«fun:«lazy:    fun saveUser(user: User) = db.put(user)»
            }»«container:
        
            DI {
                bindSingleton<DB> { DB.open("userDB") }
                bindSingleton<HttpClient> { AppHttpClient() }
                «repo:bindSingleton { UserRepository(«what:?»«passdi:di») }»
            }»
    """.trimIndent(),
        ) {
            "diaware" { fontGrow(state >= 1) }
            "inject" { lineHeight(state < 2) }
            "override" { lineHeight(state >= 2) }
            "comment" { lineHeight(state < 3) }
            "retrieve" { lineHeight(state >= 3) }
            "eoc" { fontGrow(state == 3) }
            "fun" { lineHeight(state >= 4) }
            "lazy" { zoomed(state == 5) }
            "container" { lineHeight(state >= 6) }
            "what" { fontGrow(state <= 6) }
            "passdi" { fontGrow(state >= 7) }
            "repo" { zoomed(state == 7) }
        }
    }
)