import net.kodein.theme.compose.pres.kodeinPres
import net.kodein.theme.compose.pres.kodeinSlides
import slides.thankYou
import slides.title

fun main() {
    kodeinPres {
        +title
        +kodeinSlides()
        +thankYou
    }
}
