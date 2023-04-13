package slides

import net.kodein.pres.Animations
import net.kodein.pres.Slide
import net.kodein.pres.Transitions.fade
import net.kodein.pres.Transitions.fontGrow
import net.kodein.pres.animatedWith
import net.kodein.pres.emojis.Emoji
import net.kodein.pres.shownIf
import net.kodein.pres.sourcecode.*
import net.kodein.pres.widget.SubSlide
import net.kodein.theme.KodeinColor
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.kodein.cic.css
import utils.LangMarker

val d2_api = listOf(
    Slide(
        name = "api-title",
    ) {
        P({ css { fontSize(2.em) } }) {
            Text("What can we, as KMM developers,")
            Br()
            Text("do to improve")
            Br()
            Span({ css { color(KodeinColor.orange.css) } }) {
                Text("the API exposed")
            }
            Br()
            Text("to iOS developers?")
        }
    },

    Slide(
        name = "simple-api",
        stateCount = 2
    ) { state ->
        SubSlide(state == 0, fade) {
            LangMarker("Kotlin")
            SourceCode("kotlin", """
                enum class PlaneEquipment {
                    AutoPilot,
                    GlassCockpit,
                    GPS
                }
    
                interface Plane {
                    val make: String
                    val model: String
    
                    fun equipments(): List<PlaneEquipment>
                }
            """.trimIndent())
        }
        SubSlide(state == 1, fade) {
            LangMarker("Swift")
            SourceCode("swift", """
                public class PlaneEquipment : KotlinEnum<PlaneEquipment> {
                    open class var autopilot: PlaneEquipment { get }
                    open class var glasscockpit: PlaneEquipment { get }
                    open class var gps: PlaneEquipment { get }
                }
    
                public protocol Plane {
                    func equipments() -> [PlaneEquipment]
                    var make: String { get }
                    var model: String { get }
                }
            """.trimIndent())
        }
    },
    Slide(
        name = "generic-api",
        stateCount = 5,
    ) { state ->
        SubSlide(state in 0..2, fade) {
            LangMarker("Kotlin")
            SourceCode("kotlin", """
                interface Plane«e:<E : Engine>» {
                    val make: String
                    val model: String
                «l:
                    val engine: E
    
                »    fun equipments(): List<PlaneEquipment>
                }
            """.trimIndent()) {
                "e" { fontGrow(state >= 1) }
                "l" { lineHeight(state >= 2) }
            }
        }
        SubSlide(state in 3..4, fade) {
            LangMarker("Swift")
            SourceCode("swift", """
                public «z:protocol Plane» {
                    func equipments() -> [PlaneEquipment]
                    «z:var engine: Engine» { get }
                    var make: String { get }
                    var model: String { get }
                }
            """.trimIndent()) {
                "z" { zoomed(state == 4) }
            }
        }
    },
    Slide(
        name = "generic-api-problem",
    ) {
        H1 { Text(Emoji.warning) }
        H4 { Text("Kotlin only interops with Obj-C, and") }
        H3 { Text("Obj-C only supports Generic classes") }
    },
    Slide(
        name = "generic-api-solution",
        stateCount = 4
    ) { state ->
        SubSlide(state in 0..1, fade) {
            LangMarker("Kotlin")
            SourceCode("kotlin", """
                «c:abstract class »«i:interface »Plane<E : Engine> {
                    «c:abstract »val make: String
                    «c:abstract »val model: String
    
                    «c:abstract »val engine: E
    
                    «c:abstract »fun equipments(): List<PlaneEquipment>
                }
            """.trimIndent()) {
                "c" { fontGrow(state >= 1) }
                "i" { fontGrow(state < 1) }
            }
        }
        SubSlide(state in 2..3, fade) {
            LangMarker("Swift")
            SourceCode("swift", """
                open class Plane<E> : KotlinBase where «e:E : AnyObject» {
                    public init()
                    open func equipments() -> [PlaneEquipment]
                    open var engine: E { get }
                    open var make: String { get }
                    open var model: String { get }
                }
            """.trimIndent()) {
                "e" { zoomed(state == 3) }
            }
        }
    },
    Slide(
        name = "suspend-api",
        stateCount = 5
    ) { state ->
        SubSlide(state in 0..2, fade) {
            LangMarker("Kotlin")
            SourceCode("kotlin", """
                abstract class Plane<E : Engine> {
                    abstract val make: String
                    abstract val model: String
                    abstract val engine: E
    
                    abstract fun equipments(): List<PlaneEquipment>
                «l:
                    abstract «s:suspend »fun startup()
                »}
            """.trimIndent()) {
                "l" { lineHeight(state >= 1) }
                "s" { fontGrow(state >= 2) }
            }
        }
        SubSlide(state in 3..4, fade) {
            LangMarker("Swift")
            SourceCode("swift", """
        open class Plane<E> : KotlinBase where E : AnyObject {
            // ...

            // This method converts cancellation exceptions to errors.
            // Other uncaught Kotlin exceptions are fatal.
            open func startup(completion: @escaping (Error?) -> Void)

            // This method converts cancellation exceptions to errors.
            // Other uncaught Kotlin exceptions are fatal.
            open func startup() «a:async» throws
        }
        """.trimIndent()) {
                "a" { zoomed(state == 4) }
            }
        }
    },
    Slide(
        name = "flow-api",
        stateCount = 4
    ) { state ->
        SubSlide(state in 0..2, fade) {
            LangMarker("Kotlin")
            SourceCode("kotlin", """
                abstract class Plane<E : Engine> {
                    abstract val make: String
                    abstract val model: String
                    abstract val engine: E
    
                    abstract fun equipments(): List<PlaneEquipment>
    
                    abstract suspend fun startup()
                «e:
                »«l1:    abstract fun onAlarm(cb: (Alarm) -> Unit): () -> Unit
                »«l2:    abstract val alarms: Flow<Alarm>
                »}
            """.trimIndent()) {
                "e" { lineHeight(state >= 1) }
                "l1" { lineHeight(state == 1) }
                "l2" { lineHeight(state >= 2) }
            }
        }
        SubSlide(state == 3, fade) {
            LangMarker("Swift")
            SourceCode("swift", """
            open class Plane<E> : KotlinBase where E : AnyObject {
               // ...
                open var alarms: Kotlinx_coroutines_coreFlow { get }
            }
        """.trimIndent())
        }
    },
    Slide(
        name = "flow-api-problem",
        stateCount = 2
    ) { state ->
        H1 { Text(Emoji.warning) }
        H3 {
            Text("KotlinX Flows are")
            Br()
            Text("not")
            Span({ shownIf(state >= 1, fontGrow) }) {
                Text(" properly")
            }
            Text(" supported!")
        }
    },
    Slide(
        name = "flow-api-solution",
        stateCount = 2
    ) { state ->
        H1 { Text(Emoji.bulb) }
        H3 { Text("Use KMP-NativeCoroutines") }
        H3({ shownIf(state >= 1 , fade) }) { Text("or...") }
    },
    Slide(
        name = "mp-flow-api",
        stateCount = 5,
        stateInOverview = 0
    ) { state ->
        SubSlide(state in 0..3, fade) {
            LangMarker("Kotlin")
            Div({
                css { fontSize(0.85.em) }
            }) {
                SourceCode("kotlin", """
                    «c:class MPFlow<T : Any> internal constructor(
                        internal val flow: Flow<T>
                    ) {»
                    «s:    fun subscribe(
                            next: (T) -> Unit,
                            completion: (Throwable?) -> Unit
                        ): () -> Unit {
                            val job = MainScope().launch {
                                flow.onCompletion { completion(it) }
                                    .collect { next(it) }
                            }
                            return ({ job.cancel() })
                        }»
                    }
    
                    «k:@HiddenFromObjC
                    val <T : Any> MPFlow<T>.kt: Flow<T> get() = flow
                    @HiddenFromObjC
                    fun <T : Any> Flow<T>.mp(): MPFlow<T> = MPFlow(this)
                    »
                """.trimIndent()) {
                    "c" { zoomed(state == 1) }
                    "s" { zoomed(state == 2) }
                    "k" { zoomed(state == 3) }
                }
            }
        }
        SubSlide(state == 4, fade) {
            LangMarker("Swift")
            Div({
                css { fontSize(0.85.em) }
            }) {
                SourceCode("swift", """
                public class MPFlow<T> : KotlinBase where T : AnyObject {
                    open func subscribe(
                        next: @escaping (T) -> Void,
                        completion: @escaping (KotlinThrowable?) -> Void
                    ) -> () -> Void
                }
            """.trimIndent())
            }
        }
    },
).animatedWith(Animations.Move(towards = Animations.Move.Towards.Bottom))
