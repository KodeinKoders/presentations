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
        +example
        +stage0
        +stage1
        +stage2_0
        +illus("services")
        +stage2_1
        +stage2_2
        +illus("team")
        +stage3_0
        +stage3_1
        +illus("open-source")
        +stage4
        +kodeinSlides()
        +illus("training")
        +thankYou
    }
}
