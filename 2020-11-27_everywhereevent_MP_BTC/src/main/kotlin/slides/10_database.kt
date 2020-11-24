package ws.slides

import kotlinx.css.*
import org.kodein.kpres.PresentationBuilder
import org.kodein.kpres.Swipe
import org.kodein.kpres.sourceCode
import org.kodein.kpres.subSlide
import styled.css
import styled.styledDiv
import styled.styledH2
import styled.styledH3
import ws.charter.kodein
import ws.comp.logo
import ws.utils.light
import ws.utils.opacity
import ws.utils.slideCode
import ws.utils.titledSlide

fun PresentationBuilder.database() = slide(
    stateCount = 5
) { props ->
    titledSlide("Embedded NoSQL database") {
        subSlide(0..3, props.state, Swipe) {
            styledDiv {
                css {
                    width = 100.pct
                    "h3" {
                        color = Color.kodein.purple
                        fontWeight = FontWeight.light
                        fontSize = 1.em
                        margin(.75.em, 0.em, 0.em, 1.em)
                        textAlign = TextAlign.left
                    }
                }
                styledH3 {
                    opacity(props.state >= 1)
                    +"No Schema"
                }

                styledDiv {
                    css {
                        width = 100.pct
                        opacity(props.state >= 1)
                    }
                    sourceCode("kotlin", """
                    val db = DB.inDir(getFilesDir()).open("channels")
                """.trimIndent()) {
                        width = 100.pct - 2.em
                        specific { margin(0.5.em) }
                    }
                }

                styledH3 {
                    opacity(props.state >= 2)
                    +"Works with KotlinX.Serialization"
                }

                styledDiv {
                    css {
                        width = 100.pct
                        opacity(props.state >= 2)
                    }
                    sourceCode("kotlin", """
                        db.put(channel)
                    """.trimIndent()) {
                        width = 100.pct - 2.em
                        specific { margin(0.5.em) }
                    }
                }

                styledH3 {
                    opacity(props.state >= 3)
                    +"Simple query DSL"
                }

                styledDiv {
                    css {
                        width = 100.pct
                        opacity(props.state >= 3)
                    }
                    sourceCode("kotlin", """
                        val channels = db.find<Channel>().all().use { it.toList() }
                    """.trimIndent()) {
                        width = 100.pct - 2.em
                        specific { margin(0.5.em) }
                    }
                }

            }
        }

        subSlide(4..4, props.state, Swipe) {
            logo(
                "DB",
                logoColor = "orange",
                fontColor = Color.kodein.orange
            ) {
                +"Painless NoSQL database"
            }
        }
    }
}
