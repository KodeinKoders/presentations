package utils

import org.kodein.compose.html.pres.Slide

operator fun Slide.plus(slides: List<Slide>): List<Slide> = buildList {
    add(this@plus)
    addAll(slides)
}
