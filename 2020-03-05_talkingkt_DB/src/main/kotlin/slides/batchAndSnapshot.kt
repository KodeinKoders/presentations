package ws.slides

import kotlinx.css.em
import kotlinx.css.opacity
import kotlinx.css.properties.ms
import kotlinx.css.properties.transition
import kotlinx.css.width
import org.kodein.kpres.PresentationBuilder
import org.kodein.kpres.notes
import react.dom.b
import react.dom.h1
import react.dom.i
import react.dom.p
import styled.css
import styled.styledDiv
import styled.styledP


fun PresentationBuilder.batchAndSnapshot() = slide(
        stateCount = 3,
        notes = notes {
            0 ("""
                Au fait, la Kodein-DB supporte aussi les batchs et snapshots (grâce à LevelDB).
                
                Utiliser un snapshot vous assure que l'ensemble de données requêtée reflète l'état de la base lorsque le snapshot a été créé.
                
                Un batch modifie la base de données de manière atomique, c'est à dire que toutes les modifications sont écrites en une seule fois.
            """)
            1 ("""
                Par conséquent, un snapshot ne peut jamais refléter une part seulement des modifications d'un batch.
                
                Soit c'est toutes les modifications qui seront reflétées, soit c'est aucune.
                
                Utiliser les snapshots sans les batchs n'a pas vraiment d'intérret.
            """)

            2 ("""
                Notez que lorsque vous créez un curseur pour itérer sur une collection, ce curseur contiens un snapshot créé lorsque le curseur à été crée,
                ce qui vous assure de ne jamais recevoir de nouvelles données incohérentes avec les précédentes.
            """)
        }
) { props ->
    h1 {
        +"Batch & Snapshot:"
    }

    styledDiv {
        css {
            width = 20.em
        }
        p {
            +"A snapshot ensures that the queried data set reflects the state it was when created."
        }

        p {
            +"A batch modifies the database \"atomically\"."
        }

        styledP {
            css {
                transition(::opacity, 300.ms)
                opacity = if (props.state < 1) 0.0 else 1.0
            }
            +"Therefore, a "
            b { +"snapshot" }
            +" can never reflect "
            i { +"part" }
            +" of a "
            b { +"batch" }
            +" modification."
        }

        styledP {
            css {
                transition(::opacity, 300.ms)
                opacity = if (props.state < 2) 0.0 else 1.0
            }
            +"By the way, "
            b { +"cursors" }
            +" use snapshots."
        }
    }
}
