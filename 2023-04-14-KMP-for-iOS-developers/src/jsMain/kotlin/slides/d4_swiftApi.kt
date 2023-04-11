package slides

import net.kodein.pres.*
import net.kodein.pres.Transitions
import net.kodein.pres.Transitions.fontGrow
import net.kodein.pres.sourcecode.SourceCode
import net.kodein.pres.sourcecode.fontGrow
import net.kodein.pres.sourcecode.lineHeight
import net.kodein.pres.widget.SubSlide
import net.kodein.theme.KodeinColor
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.kodein.cic.css
import utils.LangMarker

val d4_swiftApi = listOf(
    Slide(
        name = "swift-api-title",
        stateCount = 2
    ) { state ->
        P({ css { fontSize(2.em) } }) {
            Text("What can we, as KMM developers,")
            Br()
            Text("do to improve")
            Br()
            Span({ css { color(KodeinColor.orange.css) } }) {
                Text("the API exposed")
            }
            Br()
            Text("to ")
            Span({
                css { color(KodeinColor.orange.css) }
                shownIf(state >= 1, fontGrow)
            }) {
                Text("Swift ")
            }
            Text("iOS developers?")
        }
    },

    Slide(
        name = "swift-param-name",
        stateCount = 7
    ) { state ->
        SubSlide(state in listOf(0, 3, 4), Transitions.fade) {
            LangMarker("Kotlin")
            SourceCode("kotlin", """
                abstract class Plane {
                    //...
                    abstract fun listenToATIS(«n:@ObjcName("on") »freq: Freq)
                }
            """.trimIndent()) {
                "n" { fontGrow(state >= 4) }
            }
        }
        SubSlide(state in listOf(1, 2, 5, 6), Transitions.fade) {
            LangMarker("Swift")
            SourceCode("swift", """
                open class Plane : KotlinBase {
                    //...
                    open func listenToATIS(«f:freq»«o:on»: Freq)
                }
                «c:
                plane.listenToATIS(«f:freq»«o:on»: Freq(119, 205))»
            """.trimIndent())  {
                "c" { lineHeight(state >= 2) }
                "f" { fontGrow(state < 6) }
                "o" { fontGrow(state >= 6) }
            }
        }
    },

    Slide(
        name = "swift-param-appearance",
        stateCount = 8
    ) { state ->
        SubSlide(state in listOf(0, 3, 4), Transitions.fade) {
            LangMarker("Kotlin")
            SourceCode("kotlin", """
                sealed class Volume {
                    class Gallons(
                        «n:@ObjCName(swiftName = "_") »val gallons: Double
                    ): Volume()
                    class Liters(
                        «n:@ObjCName(swiftName = "_") »val liters: Double
                    ): Volume()
                }
                
                abstract class Plane {
                    abstract fun fuel(«n:@ObjCName(swiftName = "_") »volume: Volume)
                }
            """.trimIndent()) {
                "n" { fontGrow(state >= 4) }
            }
        }
        SubSlide(state in listOf(1, 2, 5, 6, 7), Transitions.fade) {
            LangMarker("Swift")
            Div({ css { width(26.em) } }) {
                SourceCode("swift", """
                    open class Plane : KotlinBase {
                        //...
                        open func fuel(«n:_ »volume: Volume)
                    }
                    open class Volume : KotlinBase {}
                    extension Volume {
                        public class Gallons : Volume {
                            public init(«n:_ »gallons: Double)
                        }
                        public class Liters : Volume {
                            public init(«n:_ »liters: Double)
                        }
                    }
                    «c:
                    plane.fuel(«r:volume: ».Gallons(«r:gallons: »12.5))»
                """.trimIndent())  {
                    "c" { lineHeight(state >= 2) }
                    "n" { fontGrow(state >= 6) }
                    "r" { fontGrow(state < 7) }
                }
            }
        }
    }

).animatedWith(Animations.Move(towards = Animations.Move.Towards.Bottom))
