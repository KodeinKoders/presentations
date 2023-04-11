import net.kodein.theme.compose.pres.kodeinPres
import net.kodein.theme.compose.pres.kodeinSlides
import slides.*


fun main() {
    kodeinPres {
        +title
        +d1_multiplatform
        +illus("team")
        +d2_api
        +d3_communication
        +d4_swiftApi
        +d5_deployment
        +d6_swiftApiInSwift
        +d7_engagement
        +d8_conclusion
//        +illus("open-source")
        +kodeinSlides()
        +thankYou
    }
}
