package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.ms
import kotlinx.css.properties.transition
import react.RBuilder
import react.child
import react.dom.li
import react.functionalComponent
import styled.StyledComponents.css
import styled.css
import styled.styledLi
import styled.styledSpan
import ws.kpres.PresentationBuilder
import ws.kpres.SlideContentProps
import ws.kpres.SlideInfos
import ws.utils.*

private val WhatNext by functionalComponent<SlideContentProps> { props ->
    slideTitle("What next?")

    bulletList(props) {
        bulletCode(props.state, 1, "Simplified API", "kotlin", """
            // Binding (Ration to Coffee)
            bind<Ration>() with singleton { Coffee(instance()) }
            // Direct Binding (Coffee)        
            bind() from singleton { Coffee(instance()) }
            // Next ?
            bindSingleton<Ration> { Coffee(instance()) }
            bindSingleton { Coffee(instance()) }
        """.trimIndent())
        bulletCode(props.state, 2, "Powered API", "kotlin", """
            bindSingleton<Ration, Coffee>() // With compiler plugins
        """.trimIndent())
        bulletPoint(props.state, 3, "Swift support")
        bulletPoint(props.state, 4, "Coroutine support")
        bulletPoint(props.state, 5, "Compiler plugin")
        bulletPoint(props.state, 6, "MVI pattern for MPP")
        bulletPoint(props.state, 7, "...")
    }

}
fun PresentationBuilder.whatNext() {
    slide(SlideInfos(8)) { child(WhatNext, it) }
}

private fun RBuilder.bulletPoint(currentState: Int, stateRef: Int, value: String, level: Int = 1, ruleSet: CSSBuilder.(currentState: Int, stateRef: Int) -> Unit = stepByStepBulletRule) {
    styledLi {
        css {
            "span" {
                opacity = 1.0
                transition(::opacity, 300.ms)
            }
        }

        styledSpan {
            css { ruleSet(currentState, stateRef) }
            +value
        }
    }
}