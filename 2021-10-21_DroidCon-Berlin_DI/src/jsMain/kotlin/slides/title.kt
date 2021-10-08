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
    H1 {
        Text("Advanced Multiplatform Dependency Injection")
    }

    H3({
        classes(PStyle.css {
            color(KodeinColor.kaumon.css)
        })
    }) { Text("Salomon BRYS") }

    A(href = "https://kodein.net", {
        target(ATarget.Blank)
    }) {
        Logo(PStyle, 4.em)
    }
}
