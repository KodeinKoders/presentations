package theme

import org.kodein.pres.SlideGroup
import org.kodein.pres.Slides
import org.kodein.pres.TransitionSet
import org.kodein.pres.copyWithInsideTransitions


@Suppress("FunctionName")
fun VerticalSlides(vararg slides: SlideGroup) = Slides(*slides) {
    copyWithInsideTransitions(it, TransitionSet.moveVertical, TransitionSet.moveVertical)
}
