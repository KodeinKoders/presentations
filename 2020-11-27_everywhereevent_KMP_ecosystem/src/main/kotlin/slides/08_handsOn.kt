package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.*
import org.kodein.kpres.*
import react.dom.span
import styled.*
import ws.charter.kodein
import ws.utils.*


fun PresentationBuilder.handsOnSection() = slide(
    stateCount = 3,
    inTransitions = Flip,
    inTransitionDuration = 1000
) { (state, _) ->
    styledH1 {
        css {
            +kodein.display2
            fontWeight = FontWeight.lighter
            color = Color.kodein.orange

            "span" {
                "&.ecosystem" {
                    when {
                        state == 1 -> textDecoration = TextDecoration(setOf(TextDecorationLine.lineThrough))
                        state > 1 -> {
                            opacity = 0
                            width = 0.em
                            position = Position.absolute
                        }
                    }
                }
                "&.handson" {
                    transition(duration = 0.5.s)
                    if (state < 2) {
                        opacity = 0
                        width = 0.em
                        position = Position.absolute
                    }
                }
            }

        }
        span("ecosystem") { +"The ecosystem" }
        span("handson") { +"Hands on!" }
    }
}

fun PresentationBuilder.problem() = slide { props ->
    titledSlide("KMM developer common tasks") {
        bulletList(props) {
            bulletPoint("Getting data from a REST API.")
            bulletPoint("Serializing into a model.")
            bulletPoint("Saving the data for offline use.")
            bulletPoint("Test our common code once!")
            bulletPoint("Use it on Android AND iOS.")
        }
    }
}

fun PresentationBuilder.initialUseCase() = slide(stateCount = 3) { (state, _) ->
    titledSlide("The use-case") {
        styledImg(src = "images/randomapi.png") {
            css {
                height = 100.pct
                transition(duration = 0.5.s)
                when {
                    state < 1 -> {
                        transform { scale(2) }
                        opacity = 0.0
                    }
                    state > 1 -> {
                        height = 0.pct
                        transform { scale(0) }
                        opacity = 0.0
                    }
                }
            }
        }
        sourceCode("json", """
            {
              "name": {
                "first": "Romain",
                "last": "Boisselle"
              },
              "email": "romain@kodein.net",
              "login": {
                "uuid": "19331328-9998-4dca-a4b3-0ab2f40c9e0f",
                "username": "bluebear681",
                "password": "redfox",
              },
              "dob": {
                "date": "1987-09-12T16:41:34.905Z",
                "age": 33
              },
              ...
            }
        """.trimIndent()) {
            transition(duration = 0.5.s)
            when {
                state < 2 -> {
                    transform { scale(2) }
                    opacity = 0.0
                }
            }
        }
    }
}

fun PresentationBuilder.showMeSomeCode() = slide(
    stateCount = 3,
    outTransitions = Flip
) { (state, _) ->
    styledH1 {
        css {
            +kodein.display2
            fontWeight = FontWeight.lighter
            color = Color.kodein.orange
        }
        +"Demo time!"
    }
    styledImg(src = "images/bang.png") {
        css {
            height = 6.rem
            transition(duration = 0.5.s)
            if (state < 1) {
                opacity = 0
                transform { scale(0) }
            }
        }
    }
    flexRow(JustifyContent.center) {
        opacity(state >= 2)
        css {
            +kodein.body
            marginTop = 3.em
        }
        styledA(href = "https://cutt.ly/ee-kmp-demo") {
            css {
                color = Color.kodein.orange
                textDecoration = TextDecoration.none
                alignSelf = Align.flexEnd
            }
            +"cutt.ly/ee-kmp-demo"
        }
    }
}
