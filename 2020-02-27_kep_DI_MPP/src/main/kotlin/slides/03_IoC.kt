package ws.slides

import kotlinx.css.*
import react.RBuilder
import react.child
import react.dom.p
import react.functionalComponent
import styled.css
import styled.styledDiv
import ws.kpres.PresentationBuilder
import ws.kpres.SlideContentProps
import ws.kpres.SlideInfos
import ws.kpres.notes
import ws.utils.*


private val infos = SlideInfos(
        stateCount = 9
)

private val IoCSlide by functionalComponent<SlideContentProps> { props ->
    slideTitle(" IoC Pattern")

    bulletList(props = props) {
        styledDiv {
                css {
                    display = Display.flex
                    flexDirection = FlexDirection.row
                    alignSelf = Align.center
                }

            fun RBuilder.versusBulletPoint(currentState: Int, stateRef: Int, value: String) =
                    bulletPoint(currentState, stateRef, value, ruleSet = versusBulletRule(4))

            styledDiv {
                css { margin(1.em) }
                versusBulletPoint(props.state, 1, "Strictly coupled")
                versusBulletPoint(props.state, 2, "Static declaration")
                versusBulletPoint(props.state, 3, "On site call...")
                versusBulletPoint(props.state, 4, "App responsibility")
            }

            styledDiv {
                css { margin(1.em) }
                versusBulletPoint(props.state, 5, "Decouple your code")
                versusBulletPoint(props.state, 6, "Dynamic execution")
                versusBulletPoint(props.state, 7, "Managed call!")
                versusBulletPoint(props.state, 8, "Framework responsibility")
            }
        }
    }
}

fun PresentationBuilder.inversionOfControl() = slide(infos) { child(IoCSlide, it) }


