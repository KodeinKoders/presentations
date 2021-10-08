package slides

import net.kodein.pres.Animations
import net.kodein.pres.Slide
import net.kodein.pres.Transitions.stamp
import net.kodein.pres.shownIf
import net.kodein.theme.compose.pres.PStyle
import net.kodein.theme.compose.web.Logo
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.Text
import kotlin.time.Duration


val kodeinKoders = Slide(
    name = "kodein-koders",
    states = 2,
    outAnimation = Animations.Flip(Duration.seconds(2))
) { state ->
    Div({
        classes(PStyle.css {
            fontSize(8.em)
        })
    }) {
        Logo("Koders") {
            Text("painless technology")
        }
    }

    Img(
        src = "img/kotlin-certified-trainer.svg",
        alt = "Kotlin Trainer, certified by Jetbrains"
    ) {
        shownIf(state >= 1, stamp)
        classes(PStyle.css {
            height(5.em)
            padding(2.em)
            marginBottom((-5).em)
            alignSelf(AlignSelf.End)
        })
    }
}
