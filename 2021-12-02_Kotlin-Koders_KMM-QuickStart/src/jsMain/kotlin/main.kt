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
import slides.*


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

        +kmm
        +gradle
        +arch
        +ktorClient
        +sqlDelight
        +kodeinDB
        +mvi
        +kodeinDI
        +gradleFinal
        +conclusion

        +thankYou
    }
}
