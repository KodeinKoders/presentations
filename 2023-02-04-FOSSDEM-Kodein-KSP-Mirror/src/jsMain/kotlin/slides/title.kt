package slides

import net.kodein.pres.Slide
import net.kodein.theme.KodeinColor
import net.kodein.theme.compose.web.Logo
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.attributes.ATarget
import org.jetbrains.compose.web.attributes.target
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.kodein.cic.css


val title = Slide(
    name = "title"
) {
    Img("img/gde.png") {
        css {
            height(1.4.em)
            margin(1.em)
        }
    }

    H1({
        style {
            fontSize(2.1.em)
        }
    }) {
        Text("A Mirror without reflection")
        Br()
        Text("with Kotlin/Multiplatform")
    }

    P({
        css {
            margin(0.1.em, 0.em, 1.em, 0.em)
            fontWeight(200)
        }
    }) {
        Text("Fosdem - 04/02/2023")
    }

    H3({
        css {
            color(KodeinColor.kaumon.css)
        }
    }) {
        Text("Salomon BRYS")
    }

    A(href = "https://kodein.net", {
        target(ATarget.Blank)
        css {
            margin(0.25.em)
            fontSize(2.em)
        }
    }) {
        Logo { Text("Koders") }
    }
}
