package utils

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Img
import org.kodein.cic.css

@Composable
fun LangMarker(lang: String) {
    Img(src = "img/$lang.svg") {
        css {
            padding(2.em)
            width(3.em)
            height(3.em)
            property("object-fit", "contain")
            position(Position.Absolute)
            top(0.px)
            right(0.px)
        }
    }
}