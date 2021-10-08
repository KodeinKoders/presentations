import net.kodein.pres.util.InHeadRulesHolder
import net.kodein.theme.compose.pres.kodeinPres
import org.jetbrains.compose.web.css.StyleSheet
import slides.title


fun main() {
    kodeinPres {
        +title
    }
}