import net.kodein.pres.Slide
import net.kodein.pres.emojis.Emoji
import net.kodein.theme.compose.pres.kodeinKodersSlide
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
        +example
        +stage0
        +stage1
        +Todo("Stage 2.0: Compile a C library")
        +Todo("Stage 2.1: Android with JNI")
        +Todo("Stage 2.2: iOS with C/Interop")
        +Todo("Stage 3.0: Android advanced API")
        +Todo("Stage 3.1: iOS Swift-only API")
        +Todo("Stage 4: Publish!")
        +thankYou
    }
}