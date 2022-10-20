package slides

import net.kodein.pres.Animations
import net.kodein.pres.Slide
import net.kodein.pres.sourcecode.SourceCode
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.Text
import kotlin.time.Duration.Companion.milliseconds

val ios_head = Slide(name = "ios-head") {
    H2 { Text("Use it on iOS!") }
}

val ios_vm = Slide(
    name = "ios-vm",
    stateCount = 4
) { state ->
    SourceCode(
        "swift",
        """
        open class AbstractViewBridgeModel<State: PosSDK.State, Action: PosSDK.Action>: ObservableObject {
        
            @Published
            public var state: State
        
            public let store: PosSDK.Store<State, Action>
            public var bag: ClosableBag = ClosableBag()
        
            public init(store: PosSDK.Store<State, Action>) {
                self.store = store
                self.state = store.initialState
                FlowWrapper<State>(flow: store.stateFlow)
                    .collect(
                        coroutineScope: store,
                        coroutineContext: DispatchersKt.dispatcherMain,
                        onEach: { [weak self] state in
                            guard let state = state else { return }
                            self?.state = state
                        },
                        onThrow: {
                            Logger.error("Error: \(${'$'}0)")
                        }
                    )
                    .addTo(closableBag: bag)
            }
        
            deinit {
                store.stop()
            }
        
            open func emit(_ action: Action) { store.sendAction(action: action) }
        }

    """.trimIndent()
    ) {
    }
}

val ios_view = Slide(
    name = "ios-view",
    stateCount = 4,
    outAnimation = Animations.Fade(300.milliseconds)
) { state ->
    SourceCode(
        "swift",
        """
                @available(iOS 14.0, *)
                public struct CashbookSettingsView: View {
                    @StateObject
                    var model: CashbookSettingsViewBridgeModel
                
                    public init(model: CashbookSettingsViewBridgeModel) {
                        _model = .init(wrappedValue: model)
                    }
                
                    public var body: some View {
                        VStack {
                            if let state = model.enabledState {
                                enableView(state)
                            } else {
                                disableView()
                            }
                        }
                        .frame(maxWidth: .infinity)
                        .popup(isPresented: \$\model.isTransactionFlowPresented) { transactionPopupView() }
                    }
                }
            """.trimIndent()
    ) {
    }
}