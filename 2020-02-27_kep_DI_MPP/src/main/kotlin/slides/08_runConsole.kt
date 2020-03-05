package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.ms
import kotlinx.css.properties.transition
import react.child
import react.functionalComponent
import styled.css
import styled.styledDiv
import styled.styledImg
import styled.styledSpan
import ws.kpres.PresentationBuilder
import ws.kpres.SlideContentProps
import ws.kpres.SlideInfos
import ws.utils.*

private val ConsoleRunSlide by functionalComponent<SlideContentProps> { props ->
    kotlinSourceCode("""
    «main«fun main() {
        MppApplication(modularizedContainer)
    }
    »«app«class MppApplication(«aware«override »val «override«di: DI»)«aware« : DIAware» {
        «init«init {
            val coffeeMaker: CoffeeMaker by «delegate«di.»instance()
            coffeeMaker.brew()
        }»
    }»""".trimIndent()) {
        val currentState = props.state
        +"c-app" { blockEffectFrom(currentState, 1) }
        +"c-init" { blockEffectFrom(currentState, 2) }
        +"c-aware" {
            lineEffectFrom(currentState, 3)
            color = if (currentState == 3) Palette.orange.color else Color.black
        }
        +"c-override" {
            color = if (currentState == 3) Palette.orange.color else Color.black
        }
        +"c-delegate" {
            lineEffect(currentState, 2..4)
            color = if (currentState == 4) Palette.orange.color else Color.black
        }
        +"c-main" { blockEffectFrom(currentState, 6) }
    }
}
private val ConsoleSlide by functionalComponent<SlideContentProps> { props ->
    styledDiv {
        css {
            display = Display.flex
            flexDirection = FlexDirection.column
            alignItems = Align.center
            height = 100.pct
            width = 100.pct
        }

        styledSpan {
            css {
                height = 10.pct
                width = 100.pct
            }
            +"native console"
        }
        styledDiv {
            css {
                height = 80.pct
            }
            styledDiv {
                css {
                    display = Display.flex
                    alignItems = Align.center
                    height = 100.pct
                }

                styledImg(src = "images/console_run.svg") {
                    css {
                        height = 16.em
                        transition(::opacity, 300.ms)
                    }
                }
            }
        }
    }
}
private val JavaSlide by functionalComponent<SlideContentProps> { props ->
    styledDiv {
        css {
            display = Display.flex
            flexDirection = FlexDirection.column
            alignItems = Align.center
            height = 100.pct
            width = 100.pct
        }

        styledSpan {
            css {
                height = 10.pct
                width = 100.pct
            }
            +"Java console"
        }
        styledDiv {
            css {
                display = Display.flex
                alignItems = Align.center
            }

            styledImg(src = "images/java_run.svg") {
                css {
                    height = 16.em
                    transition(::opacity, 300.ms)
                }
            }
        }
    }
}

fun PresentationBuilder.consoleRun() {
    slide { slideTitle("Simple runner: on the console") }
    slide(SlideInfos(7)) {
        child(ConsoleRunSlide, it)
    }
    slide { child(ConsoleSlide, it) }
    slide { child(JavaSlide, it) }
}