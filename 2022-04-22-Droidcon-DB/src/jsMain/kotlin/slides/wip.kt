package slides

import net.kodein.pres.Slide
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.css.em
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.dom.Img


val wip = Slide(
    name = "wip"
) {
    Img("img/wip.png") {
        css {
            height(15.em)
        }
    }
}
