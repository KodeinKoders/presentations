package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.LineHeight
import kotlinx.css.properties.ms
import kotlinx.css.properties.transition
import org.kodein.kpres.PresentationBuilder
import org.kodein.kpres.notes
import org.kodein.kpres.sourceCode


fun PresentationBuilder.reactiveContext() = slide(
        stateCount = 5,
        notes = notes {
            0 ("Comment passer un contexte aux listeners ?")
        }
) { props ->

//    styledH2 {
//        css {
//            margin(0.em, 0.em, 0.5.em, 0.em)
//            transition(::opacity, 500.ms)
//            transition("transform", 500.ms)
//            if (props.state < 3) {
//                opacity = 0.0
//                transform { translateY(1.5.em) }
//            }
//        }
//        +"Reaction context"
//    }

    sourceCode(
            "kotlin",
            """
                «class«class MessageContext(val fromNetwork: Boolean) : «w«Options.Write»
                
                »db.on<Message>().register {
                    didPut {«get«
                        val context = «w«options»
                            .filterIsInstance<MessageContext>()
                            .firstOrNull()

                        »if («check«context?.fromNetwork == true»«place«FROM_NETWORK») showNotification(it)
                    }
                }«put«
                

                db.put(message, «w«MessageContext»(true))»
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

            +"c-class" {
                lineHeight = LineHeight(if (props.state < 1) "0" else "1.2")
                opacity = if (props.state < 1) 0.0 else 1.0
            }
            +"c-get" {
                lineHeight = LineHeight(if (props.state < 2) "0" else "1.2")
                opacity = if (props.state < 2) 0.0 else 1.0
            }
            +"c-check" {
                fontSize = if (props.state < 3) 0.em else 1.em
            }
            +"c-place" {
                fontSize = if (props.state < 3) 1.em else 0.em
            }
            +"c-put" {
                lineHeight = LineHeight(if (props.state < 4) "0" else "1.2")
                opacity = if (props.state < 4) 0.0 else 1.0
            }
            +"c-w" {
                color = Color.white
            }
        }

    }

}
