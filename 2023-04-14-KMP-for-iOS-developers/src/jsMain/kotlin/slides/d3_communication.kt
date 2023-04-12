package slides

import net.kodein.pres.Animations
import net.kodein.pres.Slide
import net.kodein.pres.Transitions
import net.kodein.pres.Transitions.fade
import net.kodein.pres.Transitions.fontGrow
import net.kodein.pres.Transitions.stamp
import net.kodein.pres.animatedWith
import net.kodein.pres.shownIf
import net.kodein.pres.sourcecode.SourceCode
import net.kodein.pres.sourcecode.zoomed
import net.kodein.pres.widget.SubSlide
import net.kodein.theme.KodeinColor
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.kodein.cic.css
import utils.JetBrains
import utils.LangMarker
import utils.Stamp


val d3_communication = listOf(
    Slide(
        name = "com-title"
    ) {
        P({ css { fontSize(2.em) } }) {
            Text("What can we, as KMM developers,")
            Br()
            Text("do to improve")
            Br()
            Span({ css { color(KodeinColor.orange.css) } }) {
                Text("communication & engagement")
            }
            Br()
            Text("with iOS developers?")
        }
    },
    Slide(
        name = "interop",
        stateCount = 6
    ) { state ->
        H1 {
            Img(src = "img/kotlin.svg") {
                css {
                    width(1.em)
                    height(1.em)
                    property("object-fit", "contain")
                }
            }
            Span({ shownIf(state >= 3, fontGrow) }) { Text(" kinda") }
            Text(" likes ")
            Img(src = "img/ObjC.svg") {
                css {
                    width(1.em)
                    height(1.em)
                    property("object-fit", "contain")
                }
            }
            Text(",")
            Br()
            Span({ shownIf(state < 3, fontGrow) }) { Text("but") }
            Span({ shownIf(state >= 3, fontGrow) }) { Text("and") }
            Br()
            Span({ shownIf(state in 1..2, fontGrow) }) { Text("will ") }
            Img(src = "img/kotlin.svg") {
                css {
                    width(1.em)
                    height(1.em)
                    property("object-fit", "contain")
                }
            }
            Span({ shownIf(state >= 3, fontGrow) }) { Text(" will") }
            Text(" love")
            Span({ shownIf(state == 0, fontGrow) }) { Text("s") }
            Text(" ")
            Img(src = "img/Swift.svg") {
                css {
                    width(1.em)
                    height(1.em)
                    property("object-fit", "contain")
                }
            }
            Span({ shownIf(state == 0 || state >= 3, fontGrow) }) { Text("!") }
            Span({ shownIf(state in 1..2, fontGrow) }) { Text("?") }
            Br()
            Span({
                shownIf(state >= 5, fade)
                css {
                    fontSize(0.75.em)
                }
            }) { Text("(Eventually).") }
        }

        JetBrains({
            shownIf(state in 2..3, stamp)
        }) {
            Text("We've decided to stop improving interoperability with Objective-C,")
            Br()
            Span({
                css {
                    fontSize(1.2.em)
                    fontWeight(700)
                }
            }) {
                Text("in order to focus on Swift.")
            }
            Br()
            Br()
            Span({
                css { fontSize(0.75.em) }
                shownIf(state >= 3, fade)
            }) {
                Text("(So no default arguments for Objective-C.)")
            }
        }
    },
    Slide(
        name = "interop-meantime",
        stateCount = 2
    ) { state ->
        H2 {
            Text("In the meantime...")
            Br()
            Br()
            Span({ shownIf(state >= 1, fade) }) {
                Text("...")
                Span({ css { color(KodeinColor.korail.css) } }) { Text("Sourcekitten") }
                Text(" to the rescue!")
            }
        }
    },
    Slide(
        name =  "sourcekitten-request",
        stateCount = 3,
        stateInOverview = 0
    ) { state ->
        Div({
            css {
                fontSize(0.65.em)
            }
        }) {
            SourceCode("yaml", """
                «r:key.request: source.request.editor.open.interface
                key.name: "5F63C5B8-6D92-44FF-8012-DCA7D787D243"»
                key.compilerargs:
                  - "-target"
                  - "arm64-apple-ios12.0"
                  - "-sdk"
                  - "«v:${"$"}xcodePath»/Platforms/iPhoneOS.platform/Developer/SDKs/iPhoneOS.sdk"
                  - "-I"
                  - "«v:${"$"}xcodePath»/Platforms/iPhoneOS.platform/Developer/SDKs/iPhoneOS.sdk/usr/include"
                  - "-F"
                  - "«v:${"$"}xcodePath»/Platforms/iPhoneOS.platform/Developer/SDKs/iPhoneOS.sdk/System/Library/Frameworks"
                  - "-F"
                  - "«v:${"$"}frameworkDir»"
                  - ""
                key.modulename: "«v:${"$"}frameworkName»"
                key.toolchains:
                  - "com.apple.dt.toolchain.XcodeDefault"
                key.synthesizedextensions: 1
            """.trimIndent()) {
                "r" { zoomed(state == 1) }
                "v" { zoomed(state == 2) }
            }
        }
    },
    Slide(
        name = "sourcekitten-example",
        stateCount = 5,
        stateInOverview = 3
    ) { state ->
        SubSlide(state == 0, fade) {
            LangMarker("Kotlin")
            SourceCode("kotlin", """
                enum class PlaneEquipment {
                    AutoPilot,
                    GlassCockpit,
                    GPS
                }
            """.trimIndent())
        }
        SubSlide(state in 1..2, fade) {
            LangMarker("ObjC")
            SourceCode("objc", """
                __attribute__((swift_name("Plane")))
                @protocol Playground_NativePlane
                @required
                - (NSArray<Playground_NativePlaneEquipment *> *)equipments
                    __attribute__((swift_name("equipments()")));
                @property (readonly) NSString *make
                    __attribute__((swift_name("make")));
                @property (readonly) NSString *model
                    __attribute__((swift_name("model")));
                @end
            """.trimIndent())
            Stamp(state >= 2) {
                Span({
                    css {
                        fontSize(2.4.em)
                    }
                }) {
                    Text("Not cool!")
                }
            }
        }
        SubSlide(state == 3, fade) {
            Div({
                css { fontSize(1.6.em) }
            }) {
                SourceCode("bash", """
                sourcekitten request --yaml req.yaml
            """.trimIndent())
            }
        }
        SubSlide(state == 4, fade) {
            LangMarker("Swift")
            SourceCode("swift", """
                public protocol Plane {
                    func equipments() -> [PlaneEquipment]
                    var make: String { get }
                    var model: String { get }
                }
            """.trimIndent())
        }
    }
).animatedWith(Animations.Move(towards = Animations.Move.Towards.Bottom))
