package slides

import net.kodein.pres.Slide
import net.kodein.theme.KodeinColor
import net.kodein.theme.compose.web.Logo
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.attributes.ATarget
import org.jetbrains.compose.web.attributes.target
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*


val title = Slide(
    name = "title"
) {
    Img("img/gde.png") {
        css {
            height(1.4.em)
            margin(1.em)
        }
    }

    H1 {
        Text("Advanced Multiplatform Dependency Injection")
    }

    P({
        css {
            margin(0.1.em, 0.em, 1.em, 0.em)
            fontWeight(200)
        }
    }) {
        Text("DroidCon Berlin - 21/10/2021")
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
            fontSize(4.em)
        }
    }) {
        Logo { Text("Koders") }
    }
}
