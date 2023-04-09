
import net.kodein.theme.compose.pres.kodeinPres
import net.kodein.theme.compose.pres.kodeinSlides
import slides.*

fun main() {
    kodeinPres(nextOnClick = true) {
        +title
        +problem
        +multiplatform
        +expect_actual
        +ecosystem
        +kotlin_native
        +DX
        +redux
        +ios
        +android
        +tada
//        +faq
        +kodeinSlides()
        +thankYou
    }
}
