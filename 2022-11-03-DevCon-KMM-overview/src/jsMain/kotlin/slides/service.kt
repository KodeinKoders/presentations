package slides

import net.kodein.pres.Slide
import net.kodein.pres.Transitions.fontGrow
import net.kodein.pres.shownIf
import net.kodein.pres.sourcecode.SourceCode
import net.kodein.pres.sourcecode.fontGrow
import net.kodein.pres.sourcecode.lineHeight
import net.kodein.pres.sourcecode.zoomed
import org.jetbrains.compose.web.css.em
import org.jetbrains.compose.web.css.marginBottom
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.H4
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text


val service = Slide(
    name = "communicating-and-deserializing",
    stateCount = 4
) { state ->
    H3({
        style { marginBottom(0.em) }
    }) { Text("API consumption") }
    H4 {
        Text("with Ktor-Client")
        Span({
            shownIf(state >= 2, fontGrow)
        }) { Text(" & KotlinX Serialization")}
    }

    SourceCode(
        lang = "kotlin",
        """
            «ser:@Serializable
            »«data:data class Brewery(
                val id: String,
                val name: String,
                val type: BreweryType,
            }

            »val client = HttpClient«p1:()»«p2: {»
            «json:    install(ContentNegotiation) { json(Json.Default) }
            }
            »
            «z:val breweries: «t1:Response»«t2:List<Brewery>>» =
                client.get("${'$'}API_URL/breweries")«t2:.body()»»
        """.trimIndent()
    ) {
        "data" { lineHeight(state >= 1) }
        "ser" { lineHeight(state >= 2) }
        "p1" { fontGrow(state < 2) }
        "p2" { fontGrow(state >= 2) }
        "json" { lineHeight(state >= 2) }
        "t1" { fontGrow(state < 3) }
        "t2" { fontGrow(state >= 3) }
        "z" { zoomed(state == 3) }
    }
}

val serviceBis = Slide(
    name = "service",
) {
    SourceCode(
        lang = "kotlin",
        """
            class Service {
                private val client = HttpClient {
                    install(ContentNegotiation) { json(Json.Default) }
                }
            
                suspend fun getBreweries(): List<Brewery> =
                    client.get("${'$'}API_URL/breweries").body()
            }
        """.trimIndent()
    )
}