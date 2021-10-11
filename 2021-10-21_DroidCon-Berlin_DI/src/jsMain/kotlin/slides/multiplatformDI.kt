package slides

import net.kodein.pres.Slide
import org.jetbrains.compose.web.css.em
import org.jetbrains.compose.web.css.marginBottom
import org.jetbrains.compose.web.css.marginTop
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.Text

val multiplatformDI = Slide(
    name = "multiplatform-di"
) {
    H1({
        style { marginBottom(0.em) }
    }) {
        Text("Multiplatform")
    }
    H3({
        style { marginTop(0.em) }
    }) {
        Text(" Dependency Injection")
    }

    // List the goals of a multiplatform DI container
}
