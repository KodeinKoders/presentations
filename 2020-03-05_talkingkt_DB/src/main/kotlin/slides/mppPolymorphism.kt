package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.LineHeight
import kotlinx.css.properties.ms
import kotlinx.css.properties.transition
import ws.kpres.PresentationBuilder
import ws.kpres.SlideInfos
import ws.kpres.sourceCode


private val infos = SlideInfos(
        stateCount = 4
)

fun PresentationBuilder.mppPolymorphism() = slide(infos) { props ->

    sourceCode(
            "kotlin",
            """
                val db = DB.default.open(
                        "path/to/database"«1«,
            
                        DBTypeTable(TypeTable {«2«
                            root<«w«Person»>()«3«
                                .sub<«w«Child»>()»
                        »})»
                )
            """.trimIndent()
    ) {
        "code" {
            overflow = Overflow.hidden
        }

        "span" {
            +"c-marker" {
                opacity = 1.0
                verticalAlign = VerticalAlign.middle
                transition(::opacity, 300.ms)
                transition(::lineHeight, 300.ms)
                transition(::fontSize, 300.ms)
            }

            +"c-1" {
                lineHeight = LineHeight(if (props.state < 1) "0" else "1.2")
                opacity = if (props.state < 1) 0.0 else 1.0
            }
            +"c-2" {
                lineHeight = LineHeight(if (props.state < 2) "0" else "1.2")
                opacity = if (props.state < 2) 0.0 else 1.0
            }
            +"c-3" {
                lineHeight = LineHeight(if (props.state < 3) "0" else "1.2")
                opacity = if (props.state < 3) 0.0 else 1.0
            }
            +"c-w" {
                color = Color.white
            }
        }
    }

}
