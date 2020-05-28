package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.*
import org.kodein.kpres.PresentationBuilder
import org.kodein.kpres.sourceCode
import styled.css
import styled.styledH2


fun PresentationBuilder.reactiveGlobal() = slide(stateCount = 4) { props ->

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
        +"Global reaction"
    }

    sourceCode(
            "kotlin",
            """
                db.on<User>().«w«register» {«wp«

                    «w«willPut» {
                        require(it.firstName.isNotEmpty())
                    }»«dd«

                    «w«didDeleteIt» { user ->
                        db.find<Picture>()
                            .byId(user.uid)
                            .keys()
                            .forEach { db.delete(it) }
                    }»

                }
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

            +"c-wp" {
                lineHeight = LineHeight(if (props.state < 1) "0" else "1.2")
                opacity = if (props.state < 1) 0.0 else 1.0
            }
            +"c-dd" {
                lineHeight = LineHeight(if (props.state < 2) "0" else "1.2")
                opacity = if (props.state < 2) 0.0 else 1.0
            }
            +"c-w" {
                color = Color.white
            }
        }

    }

}
