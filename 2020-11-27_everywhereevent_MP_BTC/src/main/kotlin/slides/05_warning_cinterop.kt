package ws.slides

import kotlinx.css.Color
import kotlinx.css.FontWeight
import kotlinx.css.color
import kotlinx.css.fontWeight
import org.kodein.kpres.PresentationBuilder
import styled.css
import styled.styledH1
import ws.charter.kodein
import ws.utils.light

fun PresentationBuilder.warning_cInterop() = slide(
) { props ->
    styledH1 {
        css {
            +kodein.display2
            color = Color.kodein.orange
            fontWeight = FontWeight.lighter
        }

        +"Don't underestimate C-Interop!"
    }
}
