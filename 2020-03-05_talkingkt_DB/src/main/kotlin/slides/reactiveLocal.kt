package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.*
import kotlinx.css.properties.transform
import styled.css
import styled.styledH2
import ws.kpres.PresentationBuilder
import ws.kpres.SlideInfos
import ws.kpres.notes
import ws.kpres.sourceCode


private val infos = SlideInfos(
        stateCount = 4,
        notes = notes {
            1 ("DSL, possible avec objets")
        }
)

fun PresentationBuilder.reactiveLocal() = slide(infos) { props ->

    styledH2 {
        css {
            margin(0.em, 0.em, 0.5.em, 0.em)
            transition(::opacity, 500.ms)
            transition("transform", 500.ms)
            if (props.state < 3) {
                opacity = 0.0
                transform { translateY(1.5.em) }
            }
        }
        +"Local reaction"
    }

    sourceCode(
            "kotlin",
            """
                «s-i«val sub = »db.on<User>().«w«register» {«d«

                    «w«didPut» {
                        view.updateUsersList(db.find<User>().all())
                    }

                    «w«didDelete» {
                        view.updateUsersList(db.find<User>().all())
                    }»

                }«s-l«
                
                /*...*/
                
                sub.«w«close»()»
            """.trimIndent()
    ) {
        zIndex = 1
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

            +"c-d" {
                lineHeight = LineHeight(if (props.state < 1) "0" else "1.2")
                opacity = if (props.state < 1) 0.0 else 1.0
            }
            +"c-s-l" {
                lineHeight = LineHeight(if (props.state < 2) "0" else "1.2")
                opacity = if (props.state < 2) 0.0 else 1.0
            }
            +"c-s-i" {
                fontSize = if (props.state < 2) 0.em else 1.em
            }
            +"c-w" {
                color = Color.white
            }
        }

    }

}
