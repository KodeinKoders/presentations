import net.kodein.pres.Slide
import net.kodein.theme.compose.pres.kodeinPres
import slides.kodeinFramework
import slides.kodeinKoders
import slides.title


fun main() {
    kodeinPres {
        +title
        +kodeinKoders
        +kodeinFramework
        +Slide(

        ) {

        }
    }
}
