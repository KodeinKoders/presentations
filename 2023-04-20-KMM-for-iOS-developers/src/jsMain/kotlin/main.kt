
import net.kodein.theme.compose.pres.kodeinPres
import net.kodein.theme.compose.pres.kodeinSlides
import slides.*

fun main() {
    kodeinPres(nextOnClick = true) {
        +title
        +problem
        +multiplatform
        +expect_actual
        +DX
        +redux
        +ios
        +android
        +tada
        +ecosystem
        +kotlin_native
        +kodeinSlides()
        +thankYou
    }
}
