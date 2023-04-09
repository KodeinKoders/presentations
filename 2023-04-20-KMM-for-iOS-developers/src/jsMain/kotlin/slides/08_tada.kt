package slides

import net.kodein.pres.Slide
import org.jetbrains.compose.web.css.FlexDirection
import org.jetbrains.compose.web.css.em
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.margin
import org.jetbrains.compose.web.dom.Img
import org.kodein.cic.css
import utils.Flex

val tada = Slide(name = "tada") {
    Flex(FlexDirection.Row) {

        Img("img/ios.png") {
            css {
                height(25.em)
                margin(2.em)
            }
        }

        Img("img/android.png") {
            css {
                height(25.em)
                margin(2.em)
            }
        }

    }
}