package slides

import net.kodein.pres.Slide
import net.kodein.pres.sourcecode.SourceCode
import net.kodein.pres.sourcecode.lineHeight
import net.kodein.pres.sourcecode.zoomed
import org.jetbrains.compose.web.css.em
import org.jetbrains.compose.web.css.marginBottom
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.H4
import org.jetbrains.compose.web.dom.Text

val repository = Slide(
    name = "persisting-data",
    stateCount = 5
) { state ->
    H3({
        style { marginBottom(0.em) }
    }) { Text("Persisting data") }
    H4 { Text("(an idiomatic approach)") }

    SourceCode(
        lang = "kotlin",
        """
                «z:@Serializable»
                data class Brewery(«z:override val id»: Int,
                    val name: String, val type: BreweryType
                ) : «z:Metadata» {
                    override fun indexes() = mapOf("type" to type)
                }
                
                «open:val db = DB.open("path/to/db")
                »
                «put:db.put(Brewery("Saint-Feuillien", PROPRIETOR))
                db.put(Brewery("Acchouffe", REGIONAL))
                »
                «q:val regionals: Sequence<Brewery> = 
                    db.find<Brewery>().byIndex("type", REGIONAL).asModelSequence()
                »
            """.trimIndent()
    ) {
        "z" { zoomed(state == 1) }
        "open" { lineHeight(state >= 2) }
        "put" { lineHeight(state >= 3) }
        "q" { lineHeight(state >= 4) }
    }
}

