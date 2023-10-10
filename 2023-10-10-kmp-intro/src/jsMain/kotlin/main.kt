
import net.kodein.theme.compose.pres.kodeinPres
import net.kodein.theme.compose.pres.kodeinSlides
import slides.DX
import slides.ecosystem
import slides.expect_actual
import slides.caveats
import slides.multiplatform
import slides.problem
import slides.thankYou
import slides.title

fun main() {
    kodeinPres {
        +title
        +problem
        +multiplatform
        +expect_actual
        +DX
        +ecosystem
        +caveats
        kodeinSlides()
        +thankYou
    }
}
