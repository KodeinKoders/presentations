package slides

import net.kodein.theme.KodeinColor
import net.kodein.theme.compose.web.Logo
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.attributes.ATarget
import org.jetbrains.compose.web.attributes.target
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.css.em
import org.jetbrains.compose.web.css.fontSize
import org.jetbrains.compose.web.css.fontWeight
import org.jetbrains.compose.web.css.margin
import org.jetbrains.compose.web.css.paddingTop
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.Br
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text
import org.kodein.compose.html.css.css
import org.kodein.compose.html.pres.Slide


val title = Slide(
    name = "title"
) {

    A(href = "https://kodein.net", {
        target(ATarget.Blank)
        css {
            margin(1.em)
            fontSize(2.em)
        }
    }) {
        Logo { Text("Koders") }
    }

    H1({
        style {
            fontSize(2.1.em)
        }
    }) {
        Text("Introduction to")
        Br()
        Text("Kotlin Multiplatform")
    }

    P({
        css {
            margin(0.1.em, 0.em, 1.em, 0.em)
            fontWeight(200)
        }
    }) {
        Text("HumanTalks - Grenoble")
        Br {  }
        Text(" 10/10/2023")
    }

    H3({
        css {
            color(KodeinColor.kaumon.css)
        }
    }) {
        Text("Romain BOISSELLE")
    }

    A(href = "https://p.kodein.net/ht-kmp-intro", {
        css {
            paddingTop(1.em)
            fontWeight(200)
        }
    }) {
        Text("https://p.kodein.net/ht-kmp-intro")
    }
}
