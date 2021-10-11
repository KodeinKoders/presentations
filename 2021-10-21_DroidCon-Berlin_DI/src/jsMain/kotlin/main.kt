import net.kodein.pres.Slide
import net.kodein.theme.compose.pres.kodeinPres
import slides.*


fun main() {
    kodeinPres {
        +title
        +kodeinKoders
        +kodeinFramework
        +dependencyInjection
        +multiplatformDI
        +bindingTypes
        +taggedBindings
    }
}
