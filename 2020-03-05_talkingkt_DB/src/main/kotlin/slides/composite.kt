package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.*
import react.dom.b
import styled.css
import styled.styledH2
import styled.styledImg
import ws.kpres.PresentationBuilder
import ws.kpres.SlideInfos
import ws.kpres.notes
import ws.kpres.sourceCode
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin


private val infos = SlideInfos(
        stateCount = 9,
        notes = notes {
            0 ("Ajoutons la date de naissance...")
            1 ("Que nous allons bien sûr indexer")
            6 ("Index **inutile** en étant **malin**")
            8 ("Que se passe-t-il si l'ID change ?")
        }
)

fun PresentationBuilder.composite() = slide(infos) { props ->

    sourceCode(
            "kotlin",
            """
                @Serializable
                data class User(
                    val uid: String,
                    val name: Name,
                    val address: Key<Address>?«birth1«,
                    val birth: Date»
                ) : Metadata {
                    override val id get() =
                        Value.ofAscii(«n-id«name.last, name.first, »uid)
                    override val indexes get() = indexSet(«n-idx«
                        "lastName" to Value.ofAscii(name.last)»«birth2««n-ix«,»
                        "birth" to Value.of(«w«birth.year, birth.month, birth.day»)»
                    )
                }«pals«
                
                val birthPals = db.find<User>().byIndex("birth", 1986«pals-m«, 12»«pals-d«, 15»)»«family«
                
                val family = db.find<User>().by«n-id«Id»«n-ix«Index»(«n-ix«"lastName", »"BRYS")»
            """.trimIndent()
    ) {
        "code" {
            overflow = Overflow.hidden
        }

        span {
            +"c-marker" {
                opacity = 1.0
                verticalAlign = VerticalAlign.middle
                transition(::opacity, 300.ms)
                transition(::lineHeight, 300.ms)
                transition(::fontSize, 300.ms)
            }

            +"c-w" {
                color = Color.white
            }

            +"c-birth1" {
                lineHeight = LineHeight(if (props.state < 1) "0" else "1.2")
                opacity = if (props.state < 1) 0.0 else 1.0
            }

            +"c-birth2" {
                lineHeight = LineHeight(if (props.state < 2) "0" else "1.2")
                opacity = if (props.state < 2) 0.0 else 1.0
            }

            +"c-pals" {
                lineHeight = LineHeight(if (props.state < 3) "0" else "1.2")
                opacity = if (props.state < 3) 0.0 else 1.0
            }
            +"c-pals-m" {
                fontSize = if (props.state < 4) 0.em else 1.em
            }
            +"c-pals-d" {
                fontSize = if (props.state < 5) 0.em else 1.em
            }

            +"c-family" {
                lineHeight = LineHeight(if (props.state < 6) "0" else "1.2")
                opacity = if (props.state < 6) 0.0 else 1.0
            }

            +"c-n-idx" {
                lineHeight = LineHeight(if (props.state < 7) "1.2" else "0")
                opacity = if (props.state < 7) 1.0 else 0.0
            }
            +"c-n-id" {
                color = Color.white
                fontSize = if (props.state < 7) 0.em else 1.em
            }
            +"c-n-ix" {
                fontSize = if (props.state < 7) 1.em else 0.em
            }
        }
    }

    fun CSSBuilder.absolute() {
        position = Position.absolute
        transition(::opacity, 500.ms, Timing.easeIn)
        transition("transform", 500.ms, Timing.easeIn)
        opacity = if (props.state < 8) 0.0 else 1.0
        if (props.state < 8) {
            val rad = 135.0 * PI / 180.0
            transform { translate(-cos(rad).em, -sin(rad).em) }
        }
    }

    styledImg {
        attrs.src = "images/arrow.webp"
        css {
            absolute()
            right = 11.8.em
            top = 6.em
            width = 6.em
            transform { rotate(135.deg) }
        }
    }

    styledImg {
        attrs.src = "images/arrow.webp"
        css {
            absolute()
            right = 7.4.em
            top = 6.em
            width = 6.em
            transform { rotate(135.deg) }
        }
    }

    styledH2 {
        css {
            absolute()
            color = Color("#007fff")
            right = 4.3.em
            top = 3.4.em
            fontSize = 1.2.em
            fontWeight = FontWeight.w400
            margin(0.em)
        }
        +"Beware of "
        b { +"weddings" }
        +"!"
    }
}
