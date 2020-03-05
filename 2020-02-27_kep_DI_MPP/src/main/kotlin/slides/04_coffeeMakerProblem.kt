package ws.slides

import react.child
import react.functionalComponent
import ws.kpres.PresentationBuilder
import ws.kpres.SlideContentProps
import ws.kpres.SlideInfos
import ws.utils.bulletCode
import ws.utils.bulletList
import ws.utils.getValue
import ws.utils.slideTitle


private val infos = SlideInfos(
        stateCount = 5
)

private val CoffeeMakerProblemSlide by functionalComponent<SlideContentProps> { props ->
    slideTitle("The Coffee Maker problem")

    bulletList(props) {
        bulletCode(props.state, 1, "CoffeeMaker", "kotlin",
                """
                    class CoffeeMaker(
                            private val logger: CommonLogger,
                            private val heater: Heater,
                            private val pump: Pump,
                            private var ration: () -> Ration
                    )
                """.trimIndent()
        )
        bulletCode(props.state, 2, "ConsoleLogger", "kotlin",
                """
                    class ConsoleLogger: CommonLogger {
                        override fun log(msg: String) = println(msg)
                    } 
                """.trimIndent()
        )
        bulletCode(props.state, 3, "Heater", "kotlin",
                """
                    class ElectricHeater(private val log: CommonLogger) : Heater {
                        override fun on() { ... }
                        override fun off() { ... }
                        override val isHot: Boolean get() = ...
                    } 
                """.trimIndent()
        )
        bulletCode(props.state, 4, "Pump", "kotlin",
                """
                    class Thermosiphon(
                            private val log: CommonLogger,
                            private val heater: Heater
                    ) : Pump {
                        override fun pumpWater() { ... }
                    }
                """.trimIndent()
        )
    }
}

fun PresentationBuilder.coffeeMakerProblem() = slide(infos) { child(CoffeeMakerProblemSlide, it) }


