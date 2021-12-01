package slides

import net.kodein.pres.Slide
import net.kodein.pres.sourcecode.SourceCode
import net.kodein.pres.sourcecode.fontGrow
import net.kodein.pres.sourcecode.lineHeight
import net.kodein.pres.sourcecode.zoomed
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.Text


val ktorClient = Slide(
    name = "ktor-client",
    stateCount = 4
) { state ->
    H2 { Text("Ktor-Client") }

    SourceCode(
        lang = "kotlin",
        """
            «cus:@Serializable
            data class User(
                val id: Int,
                val firstName: String,
                val lastName: String
            )
            »
            val client = HttpClient«p1:()» «p2:{»
            «json:    «z:install(JsonFeature)»
            }
            »
            «z:val addr: «t1:Response»«t2:User»» = client.get("${'$'}API_URL/user/42")
        """.trimIndent()
    ) {
        "cus" { lineHeight(state >= 1) }
        "p1" { fontGrow(state < 2) }
        "p2" { fontGrow(state >= 2) }
        "json" { lineHeight(state >= 2) }
        "t1" { fontGrow(state < 2) }
        "t2" { fontGrow(state >= 2) }
        "z" { zoomed(state == 3) }
    }
}