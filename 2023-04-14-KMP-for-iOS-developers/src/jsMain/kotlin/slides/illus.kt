package slides

import net.kodein.pres.Slide
import org.jetbrains.compose.web.css.maxHeight
import org.jetbrains.compose.web.css.maxWidth
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.transform
import org.jetbrains.compose.web.dom.Img
import org.kodein.cic.css


fun illus(illus: String) = Slide(
    name = "illus-$illus"
) {
    Img(
        src = "img/illus/${illus}_1920.webp"
    ) {
        css {
            maxWidth(125.percent)
            maxHeight(125.percent)
            transform { scale(1.2) }
        }
    }
}
