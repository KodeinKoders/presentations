package slides

import net.kodein.pres.*
import net.kodein.pres.Transitions
import net.kodein.theme.KodeinColor
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.kodein.cic.css
import utils.Stamp


val d1_Android = listOf(
    Slide(
        name = "Android:Declaration",
        stateCount = 2
    ) { state ->
        H2({
            css {
                color(KodeinColor.kamethiste.css)
            }
        }) {
            Text("Le langage Kotlin a été créé pour moderniser le développement Android.")
        }
        Stamp(state >= 1) { Text("Faux !") }
    },
    Slide(
        name = "Android:Truth",
        stateCount = 2
    ) { state ->
        H3({
            css { margin(0.px) }
        }) {
            Text("Mai 2017:")
        }
        Img(src = "img/io2017.jpeg", "Google I/O 2017") {
            css {
                margin(0.px)
                width(70.percent)
                height(35.percent)
                property("object-fit", "cover")
            }
        }
        Br()
        H3({
            shownIf(state >= 1, Transitions.fade)
            css { margin(0.px) }
        }) {
            Text("Février 2016:")
        }
        Img(src = "img/kotlin11.jpeg", "Kotlin 1.1 release") {
            shownIf(state >= 1, Transitions.fade)
            css {
                margin(0.px)
                width(70.percent)
                height(35.percent)
                property("object-fit", "cover")
            }
        }
    }

).animatedWith(Animations.Move(towards = Animations.Move.Towards.Bottom))
