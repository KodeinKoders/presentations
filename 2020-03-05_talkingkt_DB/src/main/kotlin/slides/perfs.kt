package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.TextDecoration
import kotlinx.css.properties.border
import kotlinx.css.properties.s
import kotlinx.css.properties.transition
import react.dom.br
import react.dom.h1
import react.dom.h2
import styled.*
import ws.kpres.PresentationBuilder
import ws.kpres.SlideInfos
import ws.kpres.notes


private val infos = SlideInfos(
        stateCount = 5,
        notes = notes {
            0 ("Avec le cache **désactivé** et en utilisant **KotinX Serialization**")
            1 ("L'ajout est extremmement rapide")
            2 ("Le get est symptomatique d'une base NoSQL : pas besoin de relation")
            3 ("L'itération ne dépend pas du nombre de documents dans la collection mais du nombre de **documents lus**, donc pas de soucis sur une application.")
            4 ("L'itération sur un grand nombre d'élément est moins optimisé :\n\n - Pensez aux index !\n - Pas fait pour les analyses.")
        }
)

fun PresentationBuilder.perfs() = slide(infos) { props ->

    styledImg(src = "images/perfs.png") {
        css {
            maxHeight = 100.pct
            maxWidth = 100.pct
        }
    }

    styledDiv {
        css {
            position = Position.absolute
            border(0.2.em, BorderStyle.solid, Color.green, 0.5.em)

            transition(duration = 0.5.s)

            opacity = if (props.state == 0) 0.0 else 1.0

            bottom = 1.6.em;
            width = 4.1.em

            when (props.state) {
                0 -> {
                    left = 4.5.em
                    height = 1.em
                    bottom = 8.em
                }
                1 -> {
                    left = 4.5.em
                    height = 4.8.em
                }
                2 -> {
                    left = 9.4.em
                    height = 14.6.em
                }
                3 -> {
                    left = 14.2.em
                    height = 5.3.em
                }
                4 -> {
                    left = 19.em
                    width = 9.em
                    height = 4.em
                    borderColor = Color.orange
                }
            }
        }
    }

}
