import net.kodein.theme.compose.pres.kodeinPres
import net.kodein.theme.compose.pres.kodeinSlides
import slides.*


fun main() {
    kodeinPres {
        +title
        +d1_whatIsReflection
        +d2_whatIsKMP
        +illus("team")
        +d3_solution
        +d4_kotlinPoet
        +d5_ksp
        +d6_puzzle
        +d7_example
        +d8_KSPProperties
        +illus("open-source")
        +kodeinSlides()
        +illus("training")
        +thankYou
    }
}
