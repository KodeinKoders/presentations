
import net.kodein.pres.Slide
import net.kodein.pres.emojis.Emoji
import net.kodein.theme.compose.pres.kodeinPres
import net.kodein.theme.compose.pres.kodeinSlides
import org.jetbrains.compose.web.css.em
import org.jetbrains.compose.web.css.fontSize
import org.jetbrains.compose.web.css.margin
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.Text
import slides.android_head
import slides.android_view
import slides.conclusion
import slides.devExperience
import slides.downsides
import slides.ecosystem
import slides.ios_head
import slides.ios_view
import slides.ios_vm
import slides.kodein
import slides.kotlin
import slides.maturity
import slides.mindset
import slides.mocKMP
import slides.redux
import slides.repository
import slides.service
import slides.sharableCode
import slides.sqlDelight
import slides.tada
import slides.thankYou
import slides.title
import slides.useCase


fun Todo(name: String): Slide = Slide(
    name = name.replace(" ", "-").lowercase()
) {
    H1({
        style {
            fontSize(5.em)
            margin(0.1.em)
        }
    }) {
        Text(Emoji.safety_vest)
    }
    H3 {
        Text(name)
    }
}

fun main() {
    kodeinPres {
        +title
        +kodeinSlides()

        +kotlin
        +sharableCode
        +maturity
        +ecosystem
        +devExperience
        +mindset
        +useCase
        +service
        +sqlDelight
        +repository
        +redux
        +kodein
        +mocKMP
        +ios_head
        +ios_vm
        +ios_view
        +android_head
        +android_view
        +tada
        +downsides
        +conclusion
        +thankYou
    }
}
