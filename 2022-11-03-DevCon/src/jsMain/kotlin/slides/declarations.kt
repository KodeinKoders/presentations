package slides

import androidx.compose.runtime.Composable
import net.kodein.pres.Slide
import net.kodein.pres.Transition
import net.kodein.pres.Transitions
import net.kodein.pres.shownIf
import net.kodein.theme.KodeinColor
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.kodein.cic.css
import utils.Stamp
import kotlin.time.Duration


@Composable
private fun DeclSlide(state: Int) {
    val fade = object : Transition {
        override val cssTransitions: org.jetbrains.compose.web.css.Transitions.() -> Unit = {
            "opacity" { duration = 1500.ms }
        }

        override val hiddenStyle: StyleScope.() -> Unit = {
            opacity(0.1)
        }
    }


    H4({
        css {
            color(KodeinColor.korail.css)
        }
    }) {
        Text("Les influenceurs Kotlin vous mentent !")
    }
    P({
        shownIf(state < 1, fade)
    }) {
        Text("Quelle affirmation est vraie ?")
    }
    Ul({
        shownIf(state < 1, fade)
        css {
            "li" {
                padding(0.25.em, 0.em)
            }
        }
    }) {
        Li { Text("Le langage Kotlin a été créé pour moderniser le développement Android.") }
        Li { Text("Les coroutines permettent de faire du multi-threading de manière plus légère.") }
        Li { Text("Kotlin/Multiplateforme permet de transposer du code Kotlin JVM pour iOS.") }
        Li { Text("Kotlin/Multiplateforme est interopérable avec Swift.") }
        Li { Text("Les lambdas permettent de créer facilement des objets fonctions.") }
        Li { Text("Le contenu d'une value class est inliné et n'a donc pas de référence.") }
        Li { Text("Les collections sont optimisées pour la programmation fonctionnelle.") }
    }
    Stamp(state == 2) {
        Img(src = "img/troll.png")
    }
}

val declarations = Slide(
    name = "declarations"
) {
    DeclSlide(0)
}

val declarationsReveal = Slide(
    name = "declarations:reveal",
    stateCount = 3
) { state ->
    DeclSlide(state)
}
