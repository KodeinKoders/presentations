package slides

import androidx.compose.runtime.Composable
import net.kodein.pres.Slide
import net.kodein.pres.Transitions.fade
import net.kodein.pres.Transitions.fontGrow
import net.kodein.pres.emojis.Emoji
import net.kodein.pres.shownIf
import net.kodein.pres.widget.SubSlide
import net.kodein.theme.KodeinColor
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.css.FlexDirection
import org.jetbrains.compose.web.css.JustifyContent
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.backgroundColor
import org.jetbrains.compose.web.css.border
import org.jetbrains.compose.web.css.borderRadius
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.css.em
import org.jetbrains.compose.web.css.fontSize
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.lineHeight
import org.jetbrains.compose.web.css.margin
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.position
import org.jetbrains.compose.web.css.textAlign
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text
import org.kodein.cic.css
import utils.Flex

val sharableCode = Slide(
    name = "sharableCode",
    stateCount = 5
) { state ->

    SubSlide(state == 0, fontGrow) {
        H2 { Text("So, what can be shared?") }
    }

    SubSlide(state > 0, fontGrow) {
        Flex(FlexDirection.Row, attrs = {
            css { height(100.percent); width(100.percent) }
        }) {
            Flex(
                direction = FlexDirection.Column,
                justifyContent = JustifyContent.Center,
                alignItems = AlignItems.Center,
                css = {
                    height(100.percent)
                    width(50.percent)
                    textAlign("center")

                    "p" {
                        width(70.percent)
                        lineHeight("1.5")
                        fontSize(1.25.em)
                        margin(0.25.em)
                        padding(0.25.em)
                        color(KodeinColor.dark.css)
                        backgroundColor(KodeinColor.korail.css)
                        border(0.1.em, LineStyle.Solid, KodeinColor.orange.css)
                        borderRadius(0.25.em)
                    }
                }
            ) {
                P({ shownIf(state > 0, fontGrow) }) { Text("API consumption / DTOs") }
                P({ shownIf(state > 1, fontGrow) }) { Text("Business logic") }
                P({ shownIf(state > 2, fontGrow) }) { Text("UI Behavior") }
                P({ shownIf(state > 3, fontGrow) }) { Text("Tests!!!") }
            }
            Div({
                css {
                    height(25.percent); width(25.percent)
                    property("margin", "auto")
                    fontSize(4.em)
                }
            }) {
                Emotion(state == 1, Emoji.shrug)
                Emotion(state == 2, Emoji.astonished)
                Emotion(state == 3, Emoji.smiling_face_with_three_hearts)
                Emotion(state == 4, Emoji.heart_eyes)
            }
        }
    }
}

@Composable
private fun Emotion(shownIf: Boolean, emoji: String) {
    Div({
        css {
            position(Position.Absolute)
        }
        shownIf(shownIf, fade)
    }) { Text(emoji) }
}