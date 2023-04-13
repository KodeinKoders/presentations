package slides

import net.kodein.pres.*
import net.kodein.pres.Transitions
import net.kodein.pres.Transitions.fade
import net.kodein.pres.Transitions.fontGrow
import net.kodein.pres.Transitions.stamp
import net.kodein.pres.sourcecode.SourceCode
import net.kodein.pres.sourcecode.fontGrow
import net.kodein.pres.sourcecode.lineHeight
import net.kodein.pres.sourcecode.zoomed
import net.kodein.pres.widget.SubSlide
import net.kodein.theme.KodeinColor
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.kodein.cic.css
import utils.JetBrains
import utils.LangMarker

val d6_swiftApiInSwift = listOf(
    Slide(
        name = "swift-api-in-swift-title",
        stateCount = 2
    ) { state ->
        P({ css { fontSize(2.em) } }) {
            Text("What can we, as KMM developers,")
            Br()
            Text("do to improve")
            Br()
            Span({ css { color(KodeinColor.orange.css) } }) {
                Text("the ")
                Span({ shownIf(state >= 1, fontGrow) }) { Text("Swift ") }
                Text("API exposed")
            }
            Br()
            Text("to ")
            Span({
                css { color(KodeinColor.orange.css) }
            }) {
                Text("Swift ")
            }
            Text("iOS developers?")
        }
    },

    Slide(
        name = "mp-flow-swift-class"
    ) {
        SourceCode("swift", """
            public class MPFlow<T> : KotlinBase where T : AnyObject {
                open func subscribe(
                    next: @escaping (T) -> Void,
                    completion: @escaping (KotlinThrowable?) -> Void
                ) -> () -> Void
            }
        """.trimIndent())
    },
    Slide(
        name = "mp-flow-swift-util",
        stateCount = 2
    ) { state ->
        SubSlide(state == 0, fade) {
            SourceCode("swift", """
                func asPublisher<T : AnyObject>(
                    _ flow: MPFlow<T>
                ) -> some Publisher<T, Error> {
                    return MPFlowPublisher(flow: flow)
                }
            """.trimIndent())
        }
        SubSlide(state == 1, fade) {
            Div({ css { fontSize(0.68.em) } }) {
                SourceCode("swift", """
                    internal struct MPFlowPublisher<T : AnyObject> : Publisher {
                        typealias Output = T ; typealias Failure = Error
                        let flow: MPFlow<T>
                        func receive<S: Subscriber>(subscriber: S) where S.Failure == Failure, S.Input == Output {
                            let job = flow.subscribe(
                                each: { _ = subscriber.receive(${'$'}0) },
                                completion: {
                                    if let t = ${'$'}0 {
                                        subscriber.receive(completion: .failure(t.asError()))
                                    } else {
                                        subscriber.receive(completion: .finished)
                                    }
                                }
                            )
                            subscriber.receive(subscription: MPFlowSubscription(job))
                        }
                        class MPFlowSubscription: Subscription {
                            let cancelSub: () -> Void
                            init(_ cancelSub: () -> Void) { self.cancelSub = cancelSub }
                            func request(_ demand: Subscribers.Demand) {}
                            func cancel() { self.cancelSub() }
                        }
                    }
                """.trimIndent())
            }
        }
    },

    Slide(
        name = "swift-dependency",
        stateCount = 3
    ) { state ->
        SourceCode("swift", """
            let package = Package(
            «z:    name: "MyAmazingCompanionLib"»,
                platforms: [ .iOS(.v12), .watchOS(.v9) ],
                products: [ .library(
                    name: "MyAmazingCompanionLib",
                    targets: ["MyAmazingCompanionLib"]
                ), ],
                targets: [ .target(
                    name: "MyAmazingCompanionLib",
            «z:        dependencies: [.package(
                        url: "https://git.kompa.ny/shared.git"
                    ) ]»
                ) ]
            )
        """.trimIndent()) {
            "z" { zoomed(state == 1) }
        }
        JetBrains({ shownIf(state == 2, stamp) }) {
            Text("Open-Source Swift companions will soon be possible, thanks to")
            Br()
            Span({
                css {
                    fontSize(1.2.em)
                    fontWeight(700)
                }
            }) {
                Text("Multiple Namespace Generation")
            }
            Br()
            Br()
            Span({
                css { fontSize(0.75.em) }
            }) {
                Text("(Follow KT-42248 to know more).")
            }
        }
    },
).animatedWith(Animations.Move(towards = Animations.Move.Towards.Bottom))
