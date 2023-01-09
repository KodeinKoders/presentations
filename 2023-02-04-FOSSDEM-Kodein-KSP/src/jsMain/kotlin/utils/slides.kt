package utils

import net.kodein.pres.Slide

operator fun Slide.plus(slides: List<Slide>): List<Slide> = buildList {
    add(this@plus)
    addAll(slides)
}
