package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.*
import org.kodein.kpres.Flip
import org.kodein.kpres.PresentationBuilder
import styled.css
import styled.styledA
import styled.styledDiv
import styled.styledImg
import ws.charter.kodein
import ws.comp.logo
import ws.utils.flexColumn
import ws.utils.flexRow


fun PresentationBuilder.phoenix() = slide(
    style = {
        backgroundColor = Color.kodein.dark
    }
) {
    flexColumn {

        flexRow(JustifyContent.center, Align.center) {
            styledImg(src = "images/acinq-logo.svg") {
                css {
                    height = 3.3.em
                    marginLeft = 1.5.em
                }
            }
            styledImg(src = "images/acinq-text.svg") {
                css {
                    height = 1.5.em
                    marginLeft = 0.25.em
                }
            }
        }


        styledImg(src = "images/phoenix.png") {
            css {
                height = 10.em
                marginTop = 1.5.em
                objectFit = ObjectFit.contain
            }
        }
    }
}

