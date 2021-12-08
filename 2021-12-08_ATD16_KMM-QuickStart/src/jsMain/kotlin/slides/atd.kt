package slides

import net.kodein.pres.Slide
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.Text


val atd = Slide(
    name = "atd-16"
) {
    Img(src = "img/atd/intro.jpeg") {
        css {
            maxWidth(100.percent)
            maxHeight(100.percent)
        }
    }

    Img(src = "img/avatar.jpg") {
        css {
            width(7.em)
            height(7.em)
            position(Position.Absolute)
            top(4.em)
            right(4.em)
        }
    }

    H3({
        css {
            color(Color.white)
            position(Position.Absolute)
            top(6.em)
            right(1.8.em)
        }
    }) { Text("Salomon BRYS") }
}
