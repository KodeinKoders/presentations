import net.kodein.theme.compose.pres.kodeinPres
import slides.*


fun main() {
    kodeinPres {
        +title
        +kodeinSlides
        +dependencyInjection
        +multiplatformDI
        +bindingTypes
        +taggedBindings
        +scopeSlides
    }
}
