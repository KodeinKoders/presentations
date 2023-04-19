package slides

import net.kodein.pres.Slide
import net.kodein.theme.KodeinColor
import net.kodein.theme.compose.web.Logo
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.attributes.ATarget
import org.jetbrains.compose.web.attributes.target
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.Br
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text
import org.kodein.cic.css


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
        Text("Kotlin Multiplatform")
        Br()
        Text("for iOS developers")
    }

    P({
        css {
            margin(0.1.em, 0.em, 1.em, 0.em)
            fontWeight(200)
        }
    }) {
        Text("CocoaHeads Lyon - GDG Alps")
        Br {  }
        Text(" 20/04/2023")
    }

    H3({
        css {
            color(KodeinColor.kaumon.css)
        }
    }) {
        Text("Romain BOISSELLE")
    }

    A(href = "https://p.kodein.net/ch-gdg-lyon", {
        css {
            paddingTop(1.em)
            fontWeight(200)
        }
    }) {
        Text("https://p.kodein.net/ch-gdg-lyon")
    }
}
