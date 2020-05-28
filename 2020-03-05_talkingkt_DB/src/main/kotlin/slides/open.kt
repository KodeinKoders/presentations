package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.LineHeight
import kotlinx.css.properties.ms
import kotlinx.css.properties.transition
import org.kodein.kpres.PresentationBuilder
import org.kodein.kpres.notes
import org.kodein.kpres.sourceCode


fun PresentationBuilder.open() = slide(
        stateCount = 6,
        notes = notes {
            0 ("Comment **configurer** la BDD ?")
            1 ("Passer un nombre illimité d'objets de configuration")
            3 ("Défaut: OPEN_OR_CREATE")
        }
) { props ->

    sourceCode(
            "kotlin",
            """
                val db = DB.open(
                    "path/to/database"«1«,»«B1«

                    »«1«LevelDBOptions.«a«SnappyCompression(false)»«b«ParanoidChecks(true)»«2«,»»«B2«
                    »«2«LevelDBOptions.OpenPolicy(LevelDB.OpenPolicy.OPEN)«3«,»»«B3«
                    
                    »«3«ModelCache.«a«Disable»«b«MaxSize(4 * 1024 * 1024)»»«B4«
                »)
            """.trimIndent()
    ) {
        "span" {
            +"c-marker" {
                transition(::opacity, 300.ms)
                transition(::fontSize, 300.ms)
                transition(::lineHeight, 300.ms)
                verticalAlign = VerticalAlign.middle
            }
            +"c-1" {
                opacity = if (props.state < 1) 0.0 else 1.0
                lineHeight = LineHeight("0")

                ".c-a" {
                    fontSize = if (props.state < 2) 1.em else 0.em
                }
                ".c-b" {
                    fontSize = if (props.state < 2) 0.em else 1.em
                }
            }
            +"c-2" {
                opacity = if (props.state < 3) 0.0 else 1.0
                lineHeight = LineHeight("0")
            }
            +"c-3" {
                opacity = if (props.state < 4) 0.0 else 1.0
                lineHeight = LineHeight("0")

                ".c-a" {
                    fontSize = if (props.state < 5) 1.em else 0.em
                }
                ".c-b" {
                    fontSize = if (props.state < 5) 0.em else 1.em
                }
            }

            +"c-B1" { lineHeight = LineHeight(if (props.state < 1) "0" else "1.2") }
            +"c-B2" { lineHeight = LineHeight(if (props.state < 3) "0" else "1.2") }
            +"c-B3" { lineHeight = LineHeight(if (props.state < 4) "0" else "1.2") }
            +"c-B4" { lineHeight = LineHeight(if (props.state < 5) "0" else "1.2") }
        }
    }

}
