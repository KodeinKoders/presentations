package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.*
import org.kodein.kpres.Flip
import org.kodein.kpres.Move
import org.kodein.kpres.PresentationBuilder
import org.kodein.kpres.SlideContentProps
import react.dom.b
import react.dom.br
import react.dom.li
import react.dom.p
import styled.*
import ws.charter.kodein
import ws.comp.logo
import ws.utils.*


fun PresentationBuilder.sharableCode() = slide(stateCount = 5) { props ->
    titledSlide("What can be shared ?") {
        val state = props.state
        flexColumn(justifyContent = JustifyContent.center, alignItems = Align.center) {
            css {
                height = 100.pct
                width = 50.pct
                textAlign = TextAlign.center

                "p" {
                    width = 70.pct
                    lineHeight = LineHeight("1.5")
                    margin(0.25.em)
                    padding(0.25.em)
                    color = Color.kodein.kyzantium
                    backgroundColor = Color.kodein.korail
                    border(.1.em, BorderStyle.solid, Color.kodein.orange, 0.25.em)
                }
            }
            styledP {
                opacity(state >= 1)
                +"Data structure"
            }
            styledP {
                opacity(state >= 2)
                +"Business logic"
            }
            styledP {
                opacity(state >= 3)
                +"UI behavior"
            }
            styledP {
                opacity(state >= 4)
                +"Tests !!!"
            }
        }
    }

    fun CSSBuilder.emojiStyle() {
        height = 8.rem
        position = Position.absolute
        right = 5.em
        bottom = 7.em
        transition(duration = 0.5.s)
    }

    styledImg(src = "images/shrugging.png") {
        css {
            emojiStyle()
            when {
                props.state > 1 -> {
                    opacity = 0
                    right = 0.em
                }
                props.state == 1 -> {
                    opacity = 1
                    right = 5.em
                }
                else -> {
                    opacity = 0
                    right = 10.em
                }
            }
        }
    }
    styledImg(src = "images/astonished-face.png") {
        css {
            emojiStyle()
            when {
                props.state > 2 -> {
                    opacity = 0
                    right = 0.em
                }
                props.state == 2 -> {
                    opacity = 1
                    right = 5.em
                }
                else -> {
                    opacity = 0
                    right = 10.em
                }
            }
        }
    }
    styledImg(src = "images/smiling-face-with-hearts.png") {
        css {
            emojiStyle()
            when {
                props.state > 3 -> {
                    opacity = 0
                    right = 0.em
                }
                props.state == 3 -> {
                    opacity = 1
                    right = 5.em
                }
                else -> {
                    opacity = 0
                    right = 10.em
                }
            }
        }
    }
    styledImg(src = "images/smiling-face-with-heart-eyes.png") {
        css {
            emojiStyle()
            when {
                props.state > 4 -> {
                    opacity = 0
                    right = 0.em
                }
                props.state == 4 -> {
                    opacity = 1
                    right = 5.em
                }
                else -> {
                    opacity = 0
                    right = 10.em
                }
            }
        }
    }
}
