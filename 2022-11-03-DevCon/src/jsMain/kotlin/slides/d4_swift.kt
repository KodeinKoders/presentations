package slides

import net.kodein.pres.*
import net.kodein.pres.Transitions
import net.kodein.pres.emojis.Emoji
import net.kodein.pres.sourcecode.SourceCode
import net.kodein.pres.sourcecode.lineHeight
import net.kodein.pres.sourcecode.struck
import net.kodein.pres.sourcecode.zoomed
import net.kodein.theme.KodeinColor
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.kodein.cic.css
import utils.Stamp
import kotlin.time.Duration.Companion.seconds


val d4_Swift = listOf(
    Slide(
        name = "Swift:Declaration",
        stateCount = 4
    ) { state ->
        H2({
            css {
                color(KodeinColor.kamethiste.css)
            }
        }) {
            Text("Kotlin/Multiplateforme")
            Br()
            Text("est interopérable")
            Br()
            Text("avec ")
            Span({ shownIf(state < 2, Transitions.fontGrow) }) { Text("Swift") }
            Span({
                shownIf(state >= 2, Transitions.fontGrow)
                css { color(KodeinColor.korail.css) }
            }) { Text("Objective-C") }
            Span({
                shownIf(state >= 3, Transitions.fontGrow)
                css { color(KodeinColor.korail.css) }
            }) { Text(",") }
            Span({
                shownIf(state >= 3, Transitions.fontGrow)
                css { color(KodeinColor.korail.css) }
            }) {
                Br()
                Text("lui même interopérable")
                Br()
                Text("avec Swift")
            }
        }
        Stamp(state == 1) { Text("Trompeur !") }
    },
    Slide(
        name = "Swift:Class",
        stateCount = 5
    ) { state ->
        SourceCode(
            lang  = "kotlin",
            //language=kotlin
            code = """
                class Entry<V>(
                    val id: String,
                    val value: V
                )
            """.trimIndent()
        )
        Div({
            shownIf(state >= 1, Transitions.fade)
        }) {
            SourceCode(
                lang  = "ObjC",
                code = """
                    __attribute__((«s:swift_name("Entry")»))
                    «g:@interface SharedEntry<V>» : SharedBase
                    - (instancetype)initWithId:(NSString *)id
                        value:(V _Nullable)value
                        __attribute__((«s:swift_name("init(id:value:)")»))
                    @property (readonly) NSString *id
                        __attribute__((«s:swift_name("id")»));
                    @property (readonly) V _Nullable value
                        __attribute__((«s:swift_name("value")»));
                    @end;
                """.trimIndent()
            ) {
                "s" { zoomed(state == 2) }
                "g" { zoomed(state == 4) }
            }
        }
    },    Slide(
        name = "Swift:Interface",
        stateCount = 3
    ) { state ->
        SourceCode(
            lang  = "kotlin",
            //language=kotlin
            code = """
                interface Entry<V> {
                    val id: String
                    val value: V
                }
            """.trimIndent()
        )
        Div({
            shownIf(state >= 1, Transitions.fade)
        }) {
            SourceCode(
                lang  = "ObjC",
                code = """
                    __attribute__((swift_name("Entry")))
                    «g:@protocol SharedEntry»
                    @required
                    @property (readonly) NSString *id
                        __attribute__((swift_name("id")));
                    @property (readonly) id _Nullable value
                        __attribute__((swift_name("value")));
                    @end;
                """.trimIndent()
            ) {
                "g" { zoomed(state == 2) }
            }
        }
    },
).animatedWith(Animations.Move(towards = Animations.Move.Towards.Bottom))
