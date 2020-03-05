package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.ms
import kotlinx.css.properties.transition
import react.child
import react.functionalComponent
import styled.*
import ws.kpres.PresentationBuilder
import ws.kpres.SlideContentProps
import ws.kpres.SlideInfos
import ws.kpres.sourceCode
import ws.utils.*

private val WhatsMVP by functionalComponent<SlideContentProps> { props ->
    slideTitle("What's MVP, anyway?")
    styledDiv {
        css {
            display = Display.flex
            alignItems = Align.center
            height = 100.pct
        }

        styledImg(src = "images/mvp.png") {
            css {
                height = 12.em
                transition(::opacity, 300.ms)
                opacity = if (props.state == 1) 1 else 0
            }
        }
    }
}
private val SimpleMVP by functionalComponent<SlideContentProps> { props ->
    kotlinSourceCode("""
    class CMContract«dot« { ... }»«contract-out« {
        interface Presenter
        interface View {
            fun displayInstruction(msg: String)
        }
    }

    »«presenter«class CoffeeMakerPresenterImpl(override val di: DI) 
        : CMContract.Presenter, DIAware {«view«
        private var view: CMContract.View? = null; private set«inject«
        
        private val coffeeMaker: CoffeeMaker by instance()»
        
        »«attach«fun attachView(view: CMContract.View) {
            this.view = view«inject«
            coffeeMaker.brew()    
        »}
        
        fun detachView() {
            view = null
        }
    »}»""".trimIndent()) {
        val currentState = props.state
        +"c-presenter" { blockEffectFrom(currentState, 1) }
        +"c-view" { blockEffectFrom(currentState, 2) }
        +"c-attach" { blockEffectFrom(currentState, 3) }
        +"c-contract-out" { blockEffectTo(currentState, 3) }
        +"c-dot" { lineEffectFrom(currentState, 3) }
        +"c-inject" { blockEffectFrom(currentState, 4) }
    }
}
private val ScreenLogger by functionalComponent<SlideContentProps> { props ->
    kotlinSourceCode("""
    «logger«class ScreenLogger: CommonLogger {«callback«
        var text: String = ""
            private set

        var callback: (() -> Unit)? = null

        »«override«override fun log(msg: String) {«callback«
            text += "/$/msg\n"
            callback?.invoke()
        »}
    »}»«usage«
    // usage
    private val logger: ScreenLogger by instance()
    logger.callback = {
        // Do something each time we log
    }»
    """.trimIndent()) {
        val currentState = props.state
        +"c-logger" { blockEffectFrom(currentState, 1) }
        +"c-override" { blockEffectFrom(currentState, 2) }
        +"c-callback" { blockEffectFrom(currentState, 3) }
        +"c-usage" { blockEffectFrom(currentState, 4) }
    }
}
private val SuperMVP by functionalComponent<SlideContentProps> { props ->
    kotlinSourceCode("""
    class CoffeeMakerPresenterImpl(override val di: DI) 
        : CMContract.Presenter, DIAware {
        private var view: CMContract.View? = null; private set
        
        private val coffeeMaker: CoffeeMaker by instance()«inject«
        private val «log-color«logger: ScreenLogger» by instance()»
        
        fun attachView(view: Brew.View) {
            this.view = view«callback«
            
            «callback-color«logger.callback = {
                view.displayInstruction(logger.text)
            }»
            
            »coffeeMaker.brew()    
        }
        
        fun detachView() { ... }
    }
    """.trimIndent()) {
        val currentState = props.state
        +"c-inject" {
            blockEffectFrom(currentState, 1)
            highlightOn(currentState, 1, Palette.orange)
        }
        +"c-callback" {
            blockEffectFrom(currentState, 2)
            highlightOn(currentState, 2, Palette.orange)
        }
    }
}
private val NewBindings by functionalComponent<SlideContentProps> { props ->
    kotlinSourceCode("""
    val diContainer = DI {
        importAll(thermosiphonModule, electricHeaterModule)
        bind() from provider { Coffee(instance()) }
        bind() from singleton { 
          CoffeeMaker(instance(), instance(), instance(), provider()) 
        }«log-out«
        
        bind() from singleton { ConsoleLogger(instance()) }»«log-in«
        bind() from singleton { ScreenLogger(instance()) }»«presenter«
        
        bind() from singleton { CoffeeMakerPresenterImpl(di) }
    »}
    """.trimIndent()) {
        val currentState = props.state
        +"c-log-out" {
            blockEffectTo(currentState, 2)
            highlightOn(currentState, 1, Color.darkRed)
        }
        +"c-log-in" {
            blockEffectFrom(currentState, 2)
            highlightOn(currentState, 2, Palette.orange)
        }
        +"c-presenter" {
            blockEffectFrom(currentState, 3)
            highlightOn(currentState, 3, Palette.orange)
        }

    }
}
private val iOSBridge by functionalComponent<SlideContentProps> { props ->
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
            +"iOS tricks: a bridge injector"
        }
        styledDiv {
            css {
                height = 80.pct
                width = 100.pct
            }
            kotlinSourceCode("""«in«@ThreadLocal
object CommonInjector {«di«
    val diContainer = DI {
        importAll(thermosiphonModule, electricHeaterModule)
        bind() from provider { Coffee(instance()) }
        bind() from singleton {
            CoffeeMaker(instance(), instance(), 
                        instance(), provider())
        }

        bind() from singleton { ScreenLogger(instance()) }

        bind() from singleton { CoffeeMakerPresenterImpl(di) }
    }»«presenter«
    
    fun coffeeMakerPresenter(): CoffeeMakerPresenterImpl
                = diContainer.direct.instance()
»}»
    """.trimIndent()) {
                val currentState = props.state
                +"c-in" {
                    blockEffectFrom(currentState, 1)
                }
                +"c-di" {
                    blockEffectFrom(currentState, 2)
                }
                +"c-presenter" {
                    blockEffectFrom(currentState, 3)
                }
            }
        }
    }
}
private val iOSUsage1 by functionalComponent<SlideContentProps> { props ->
    slideTitle("iOS")

    sourceCode("swift", """
        class ViewController: UIViewController«view-line«, CMContractView» {
            @IBOutlet weak var msgLabel: UILabel!«inject-1«
            private var presenter: CMContractPresenter!»
            
            override func viewDidLoad() {
                super.viewDidLoad()«inject-2«
                presenter = CommonInjector.init().coffeeMakerPresenter()»
            }«view-override«
        
            func displayInstruction(msg: String) {
                msgLabel.text = msg
            }
        »}
        """.trimIndent()) {
        "code" {
            overflow = Overflow.hidden
        }
        "span.c-marker" {
            opacity = 1.0
            transition(::opacity, 300.ms)
            transition(::fontSize, 300.ms)
            transition(::lineHeight, 300.ms)
            val currentState = props.state
            +"c-view-override" { blockEffectFrom(currentState, 1) }
            +"c-view-line" { lineEffectFrom(currentState, 1) }
            +"c-inject-1" { blockEffectFrom(currentState, 2) }
            +"c-inject-2" { blockEffectFrom(currentState, 3) }
        }
    }
}
private val iOSUsage2 by functionalComponent<SlideContentProps> { props ->
    slideTitle("iOS")

    sourceCode("swift", """
        class ViewController: UIViewController, CMContractView {
            «dot-out«@IBOutlet weak var msgLabel: UILabel!
            private var presenter: CMContractPresenter!
            »«dot-in« //...»
            override func viewDidLoad()«dot-in« { ... }»«dot-out« {
                super.viewDidLoad()«inject-2«
                presenter = CommonInjector.init().coffeeMakerPresenter()»
            }»«inject«
            override func viewWillAppear(_ animated: Bool) {
                super.viewWillAppear(animated)
                presenter.attachView(view: self)
            }
            
            override func viewWillDisappear(_ animated: Bool) {
                super.viewWillDisappear(animated)
                presenter.detachView()
            }» 
        
            func displayInstruction(msg: String)«dot-in« { ... }»«dot-out« {
                msgLabel.text = msg
            }»
        }
        """.trimIndent()) {
        "code" {
            overflow = Overflow.hidden
        }
        "span.c-marker" {
            opacity = 1.0
            transition(::opacity, 300.ms)
            transition(::fontSize, 300.ms)
            transition(::lineHeight, 300.ms)
            val currentState = props.state
            +"c-dot-in" { lineEffectFrom(currentState, 1) }
            +"c-dot-out" { blockEffectTo(currentState, 1) }
            +"c-inject" { blockEffectFrom(currentState, 1) }
        }
    }
}
private val iOSUsage3 by functionalComponent<SlideContentProps> { props ->
    styledDiv {
        css {
            display = Display.flex
            alignItems = Align.center
            height = 100.pct
        }

        styledImg(src = "images/ios.png") {
            css {
                height = 16.em
                transition(::opacity, 300.ms)
            }
        }
    }
}
private val AndroidUsage1 by functionalComponent<SlideContentProps> { props ->
    slideTitle("Android")

    kotlinSourceCode("""
        class App : Application()«aware-ext«, DIAware» {«aware-override«
            override val di»«injector« = CommonInjector.diContainer»«lazy« by DI.lazy {«extend«
                extend(CommonInjector.sdiContainer)»«import«
                import(androidXModule(this@DemoApplication))
            »}
        »}
        """.trimIndent()) {
        val currentState = props.state
        +"c-aware-override" { blockEffectFrom(currentState, 1) }
        +"c-aware-ext" { lineEffectFrom(currentState, 1) }
        +"c-injector" {
            lineEffect(currentState, 2..3)
            highlightOn(currentState, 2, Palette.orange)
        }
        +"c-lazy" { blockEffectFrom(currentState, 4) }
        +"c-extend" { blockEffectFrom(currentState, 5) }
        +"c-import" { blockEffectFrom(currentState, 6) }
    }
}
private val AndroidUsage2 by functionalComponent<SlideContentProps> { props ->
    slideTitle("Android")

    kotlinSourceCode("""
class MainFragment : Fragment()«view-line«, CMContract.View»«aware«, DIAware» {«inject-1«
    override val di by di()»«inject-2«
    private val presenter: CMContract.Presenter by instance()
    
    »«inject-3«override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }
    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
    
    »«view-override«override fun displayInstruction(msg: String) {
        msgLabel.text = msg
    }»
}
        """.trimIndent()) {
        val currentState = props.state
        +"c-view-override" { blockEffectFrom(currentState, 1) }
        +"c-view-line" { lineEffectFrom(currentState, 1) }
        +"c-aware" { lineEffectFrom(currentState, 2) }
        +"c-inject-1" { lineEffectFrom(currentState, 2) }
        +"c-inject-2" { lineEffectFrom(currentState, 3) }
        +"c-inject-3" { blockEffectFrom(currentState, 4) }
    }
}
private val AndroidUsage3 by functionalComponent<SlideContentProps> { props ->
    styledDiv {
        css {
            display = Display.flex
            alignItems = Align.center
            height = 100.pct
        }

        styledImg(src = "images/android.png") {
            css {
                height = 16.em
                transition(::opacity, 300.ms)
            }
        }
    }
}
private val iOSVsAndroid by functionalComponent<SlideContentProps> { props ->
    styledH2 {
        css {
            fontWeight = FontWeight.normal
            margin(0.3.em, 0.em, 0.3.em, 0.em)
        }
        +" Seems stupid simple, right?"
    }

    styledDiv {
        css {
            margin(0.em)
            display = Display.flex
            flexDirection = FlexDirection.row
            alignSelf = Align.stretch
            height = 18.em
        }
        styledDiv {
            css {
                margin(0.5.em)
                width = 50.pct
                alignSelf = Align.stretch
                borderRadius = 0.3.em
            }
            styledImg(src = "images/ios.png") {
                css {
                    height = 16.em
                    transition(::opacity, 300.ms)
                }
            }
        }

        styledDiv {
            css {
                margin(0.5.em)
                width = 50.pct
                alignSelf = Align.stretch
                borderRadius = 0.3.em
            }
            styledImg(src = "images/android.png") {
                css {
                    height = 16.em
                    transition(::opacity, 300.ms)
                }
            }
        }
    }

}

fun PresentationBuilder.mvp() {
    slide { slideTitle("MVP pattern for Kotlin/Multiplatform") }
    slide(SlideInfos(2)) { child(WhatsMVP, it) }
    slide(SlideInfos(5)) { child(SimpleMVP, it) }
    slide(SlideInfos(5)) { child(ScreenLogger, it) }
    slide(SlideInfos(3)) { child(SuperMVP, it) }
    slide { slideTitle("Let's bind our new friends!") }
    slide(SlideInfos(4)) { child(NewBindings, it) }
    slide {
        slideTitle("Let's use our MVP on")
        slideTitle("Android & iOS!")
    }
    slide(SlideInfos(4)) { child(iOSBridge, it) }
    slide(SlideInfos(4)) { child(iOSUsage1, it) }
    slide(SlideInfos(2)) { child(iOSUsage2, it) }
    slide { child(iOSUsage3, it) }
    slide(SlideInfos(7)) { child(AndroidUsage1, it) }
    slide(SlideInfos(5)) { child(AndroidUsage2, it) }
    slide { child(AndroidUsage3, it) }
    slide { child(iOSVsAndroid, it) }
}
