package slides

import net.kodein.pres.Animations
import net.kodein.pres.OverlayAttrs
import net.kodein.pres.Slide
import net.kodein.theme.KodeinColor
import net.kodein.theme.compose.pres.PStyle
import net.kodein.theme.compose.web.Logo
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.Text
import kotlin.time.Duration


val kodeinFramework = Slide(
    "kodein-framework",
    inAnimation = Animations.Flip(Duration.seconds(2)),
    config = OverlayAttrs {
        classes(PStyle.css {
            backgroundColor(Color("#46AF6D"))
        })
    }
) {
    Div({
        classes(PStyle.css {
            fontSize(7.em)
        })
    }) {
        Logo("Framework", "white", Color.white) {
            Text("painless ")
            Img(
                src = "img/kotlin.svg",
                alt = "Kotlin"
            ) {
                classes(PStyle.css {
                    height(0.55.em)
                })
            }
            Text(" multiplatform")
        }
    }
}
