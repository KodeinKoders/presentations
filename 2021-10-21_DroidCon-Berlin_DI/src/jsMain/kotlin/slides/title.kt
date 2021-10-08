package slides

import net.kodein.pres.Slide
import net.kodein.pres.sourcecode.SourceCode
import net.kodein.theme.KodeinColor
import net.kodein.theme.KodeinFont
import net.kodein.theme.compose.pres.PStyle
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
        classes(PStyle.css {
            height(1.4.em)
            margin(1.em)
        })
    }

    H1 {
        Text("Advanced Multiplatform Dependency Injection")
    }

    H3({
        classes(PStyle.css {
            color(KodeinColor.kaumon.css)
        })
    }) {
        Text("Salomon BRYS")
    }

    A(href = "https://kodein.net", {
        target(ATarget.Blank)
        classes(PStyle.css {
            margin(0.25.em)
            fontSize(4.em)
        })
    }) {
        Logo("Koders") {}
    }
}
