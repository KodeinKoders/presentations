package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.LineHeight
import kotlinx.css.properties.ms
import kotlinx.css.properties.transition
import ws.kpres.PresentationBuilder
import ws.kpres.SlideInfos
import ws.kpres.notes
import ws.kpres.sourceCode


private val infos = SlideInfos(
        stateCount = 8,
        notes = notes {
            0 ("Un peu de recherche...")
            3 ("Configuration : objets")
        }
)

fun PresentationBuilder.query() = slide(infos) { props ->

    sourceCode(
            "kotlin",
            """
                val key = db.newKey<User>("...")
                val user = db[key]«c1«
                
                val users = db.find<User>(«o1«DataRead.VerifyChecksum(true)»)
                        .all()»«c2«
                
                val users = db.find<User>(«o2«ModelCache.«o2b«Refresh»«o2a«Skip»»)
                        .byIndex("lastName")»«c3«
                
                val users = db.find<User>(«o3«KXSerializer(mySerializer)»)
                        .byIndex("lastName", "Doe")»
                    """.trimIndent()
    ) {
        "code" {
            overflow = Overflow.hidden
        }

        "span.c-marker" {
            opacity = 1.0
            verticalAlign = VerticalAlign.middle
            transition(::opacity, 300.ms)
            transition(::lineHeight, 300.ms)
            fun CSSBuilder.minState(min: Int) {
                opacity = if (props.state < min) 0.0 else 1.0
                lineHeight = LineHeight(if (props.state < min) "0" else "1.2")
            }
            +"c-c1" { minState(1) }
            +"c-c2" { minState(2) }
            +"c-c3" { minState(3) }

            +"c-o1" {
                transition(::fontSize, 300.ms)
                fontSize = if (props.state < 4) 0.em else 1.em
                color = Color.white
            }
            +"c-o2" {
                transition(::fontSize, 300.ms)
                fontSize = if (props.state < 5) 0.em else 1.em
                color = Color.white
            }
            +"c-o2a" {
                if (props.state < 6) fontSize = 1.em
                else  {
                    transition(::fontSize, 300.ms)
                    fontSize = 0.em
                }
            }
            +"c-o2b" {
                if (props.state < 6) fontSize = 0.em
                else  {
                    transition(::fontSize, 300.ms)
                    fontSize = 1.em
                }
            }
            +"c-o3" {
                transition(::fontSize, 300.ms)
                fontSize = if (props.state < 7) 0.em else 1.em
                color = Color.white
            }
        }
    }
}
