package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.*
import react.dom.span
import styled.StyledComponents.css
import styled.css
import styled.styledDiv
import ws.kpres.PresentationBuilder
import ws.kpres.SlideInfos
import ws.kpres.notes
import ws.kpres.sourceCode


private val infos = SlideInfos(
        stateCount = 9,
        notes = notes {
            0 ("Kotlin/Native limite Kotlin/Multiplatform")
            3 ("Pas de reflexion, donc pas d'annotation")
        }
)

fun PresentationBuilder.mppModel() = slide(infos) { props ->

    sourceCode(
            "kotlin",
            """
                «ano1«@Serializable»
                data class User(
                    «ano2«@Id »val uid: String,
                    val firstName: String,
                    «ano2«@Indexed("lastName") »val lastName: String,
                    val address: Key<Address>?
                )«met1« : «w«Metadata» {«met2«
                    override val id get() = «w«Value.ofAscii(uid)»»«met3«
                    override val indexes get() =
                        indexSet("lastName" to «w«Value.ofAscii(lastName)»)
                »}»
                                
                val db = DB.open(
                    "path/to/database"«ser1«,
                
                    DBSerializer(KotlinxSerializer {«ser2«
                        «wb«+»User.serializer()
                        «wb«+»Address.serializer()
                    »})»
                )

            """.trimIndent()
    ) {
        "span" {
            +"c-marker" {
                transition(::opacity, 300.ms)
                transition(::fontSize, 300.ms)
                transition(::lineHeight, 300.ms)
                verticalAlign = VerticalAlign.middle
            }
            +"c-ano1" {
//                fontSize = if (props.state < 1) 0.em else 1.em
                opacity = if (props.state < 1) 0.0 else 1.0
                lineHeight = LineHeight(if (props.state < 1) "0" else "1.2")
                universal {
                    color = Color.white
                }
            }
            +"c-ser1" {
                opacity = if (props.state < 2) 0.0 else 1.0
                lineHeight = LineHeight(if (props.state < 2) "0" else "1.2")
            }
            +"c-ser2" {
                opacity = if (props.state < 3) 0.0 else 1.0
                lineHeight = LineHeight(if (props.state < 3) "0" else "1.2")
            }
            +"c-ano2" {
                fontSize = if (props.state < 4) 1.em else 0.em
            }
            +"c-met1" {
                opacity = if (props.state < 5) 0.0 else 1.0
                lineHeight = LineHeight(if (props.state < 5) "0" else "1.2")
            }
            +"c-met2" {
                opacity = if (props.state < 6) 0.0 else 1.0
                lineHeight = LineHeight(if (props.state < 6) "0" else "1.2")
            }
            +"c-met3" {
                opacity = if (props.state < 7) 0.0 else 1.0
                lineHeight = LineHeight(if (props.state < 7) "0" else "1.2")
            }
            +"c-w" {
                color = Color.white
                universal { color = Color.white }
            }
            +"c-wb" {
                color = Color.white
                fontWeight = FontWeight.w600
            }
        }
    }

    styledDiv {
        css {
            position = Position.absolute
            width = 20.em
            height = 6.em
            left = 50.pct - 10.em
            top = 50.pct - 3.em
            color = Color.red
            fontSize = 1.4.em
            put("text-shadow", "black 0.1em 0 0.1em, black -0.1em 0 0.1em, black 0 0.1em 0.1em, black 0 -0.1em 0.1em")

            transition(::opacity, 500.ms, Timing.easeIn)
            transition("transform", 500.ms, Timing.easeIn)
            if (props.state < 8) {
                opacity = 0.0
                transform {
                    rotate((-25).deg)
                    scale(1.5)
                }
            } else {
                opacity = 1.0
                transform { rotate((-25).deg) }
            }

            "span" {
                display = Display.block
                fontWeight = FontWeight.w600
                fontSize = 1.8.em
            }
        }
        +"Obsolete when"
        span {
            +"compiler plugins"
        }
        +"are a real thing!"
    }

}
