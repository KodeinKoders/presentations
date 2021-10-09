package slides

import net.kodein.pres.Animations
import net.kodein.pres.Slide
import net.kodein.pres.Transitions.stamp
import net.kodein.pres.shownIf
import net.kodein.pres.util.d
import net.kodein.theme.compose.web.Logo
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import kotlin.time.Duration


val kodeinKoders = Slide(
    name = "kodein-koders",
    stateCount = 2,
    outAnimation = Animations.Flip(Duration.seconds(2))
) { state ->
    Div({
        css {
            fontSize(8.em)
        }
    }) {
        Logo(
            catchPhrase = { Text("painless technology") },
            section = { Text("Koders") }
        )
    }

    Img(
        src = "img/kotlin-certified-trainer.svg",
        alt = "Kotlin Trainer, certified by Jetbrains"
    ) {
        shownIf(state >= 1, stamp)
        css {
            height(5.em)
            padding(2.em)
            marginBottom((-5).em)
            alignSelf(AlignSelf.End)
        }
    }
}
