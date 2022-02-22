import net.kodein.pres.Slide
import net.kodein.pres.emojis.Emoji
import net.kodein.theme.compose.pres.KodeinOpenSourceComponent
import net.kodein.theme.compose.pres.kodeinPres
import net.kodein.theme.compose.pres.kodeinSlides
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.em
import org.jetbrains.compose.web.css.fontSize
import org.jetbrains.compose.web.css.margin
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.Text
import slides.*


val dbBlue = Color("#1B93D2")

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
        +kodeinSlides(KodeinOpenSourceComponent(
            name = "Kodein-DB",
            usage = "NoSQL",
            color = dbBlue,
            targets = listOf("Android", "iOS", "Desktop")
        ))

        +wip
        +nosql
        +sqlite
        +example
        +layers
        +immutability
        +data
        +definition
        +ids
        +polymorphism
        +eventBus
        +reactions
        +other

        +thankYou
    }
}
