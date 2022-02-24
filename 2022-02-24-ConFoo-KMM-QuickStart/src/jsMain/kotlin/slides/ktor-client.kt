package slides

import net.kodein.pres.Slide
import net.kodein.pres.Transitions.fade
import net.kodein.pres.Transitions.fontGrow
import net.kodein.pres.Transitions.grow
import net.kodein.pres.shownIf
import net.kodein.pres.sourcecode.SourceCode
import net.kodein.pres.sourcecode.fontGrow
import net.kodein.pres.sourcecode.lineHeight
import net.kodein.pres.sourcecode.zoomed
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.H4
import org.jetbrains.compose.web.dom.Text


val ktorClient = Slide(
    name = "ktor-client",
    stateCount = 6
) { state ->
    H2 {
        Text("Ktor-Client")
    }
    H4({
        shownIf(state >= 2, fontGrow)
    }) { Text("& KotlinX Serialization") }

    SourceCode(
        lang = "kotlin",
        """
            «ser:«z:@Serializable»
            »«cus:data class User(
                val id: Int,
                val firstName: String,
                val lastName: String
            )
            »
            val client = HttpClient«p1:()» «p2:{»
            «json:    «z:install(JsonFeature)»
            }
            »
            «z:val user: «t1:Response»«t2:User»» = client.get("${'$'}API_URL/user/42")
        """.trimIndent()
    ) {
        "cus" { lineHeight(state >= 1) }
        "ser" { lineHeight(state >= 2) }
        "p1" { fontGrow(state < 3) }
        "p2" { fontGrow(state >= 3) }
        "json" { lineHeight(state >= 3) }
        "t1" { fontGrow(state < 3) }
        "t2" { fontGrow(state >= 3) }
        "z" { zoomed(state == 4) }
    }
}