package slides

import net.kodein.pres.Slide
import net.kodein.pres.sourcecode.SourceCode
import net.kodein.pres.sourcecode.lineHeight
import net.kodein.pres.sourcecode.zoomed
import org.jetbrains.compose.web.css.em
import org.jetbrains.compose.web.css.marginBottom
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.H4
import org.jetbrains.compose.web.dom.H5
import org.jetbrains.compose.web.dom.Text

val repository = Slide(
    name = "idiomatic-data-storage",
    stateCount = 6
) { state ->
    H3({
        style { marginBottom(0.em) }
    }) { Text("A better way to store data?") }

    SourceCode(
        lang = "kotlin",
        """     
            «show:
            «z:@StoredModel»
            data class Brewery(
                «z:@Id» val id: Int,
                val name: String, 
                «z:@Index» val type: BreweryType
            )
            
            «open:val db = DB.open("path/to/db")
            »
            «put:db.put(Brewery(1, "Saint-Feuillien", PROPRIETOR))
            db.put(Brewery(2, "Acchouffe", REGIONAL))
            »
            «q:val regionals: list<Brewery> = db.find<Brewery>().byType(REGIONAL)
            »»
            """.trimIndent()
    ) {
        "show" { lineHeight(state >= 1) }
        "z" { zoomed(state == 2) }
        "open" { lineHeight(state >= 3) }
        "put" { lineHeight(state >= 4) }
        "q" { lineHeight(state >= 5) }
    }
}

