package slides

import net.kodein.pres.Slide
import net.kodein.pres.Transitions
import net.kodein.pres.hiddenIf
import net.kodein.pres.shownIf
import net.kodein.pres.sourcecode.SourceCode
import net.kodein.pres.sourcecode.lineHeight
import net.kodein.pres.sourcecode.zoomed
import net.kodein.pres.widget.SubSlide
import org.jetbrains.compose.web.css.fontWeight
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text
import org.kodein.cic.css

val platformModules = listOf(
    Slide(
        name = "platform-modules"
    ) {
        H3 { Text("Platform specific modules") }
    },
    Slide(
        name = "expect-actual",
        stateCount = 5
    ) { state ->
        SubSlide(state == 0, Transitions.grow) {
            H2 {
                Text("How to handle platform specific behavior?")
            }
        }
        SubSlide(state in 1..4, Transitions.fade) {
            SourceCode(
                "kotlin",
                """
        «expect:// Kotlin common code (src/commonMain/kotlin)
        expect fun saveValueLocally(value: String)               
    
        »«android:// Kotlin for Android (src/androidMain/kotlin)
        «platform:import android.content.SharedPreferences»
        actual fun saveValueLocally(value: String) {
            val sharedPreferences = …
            sharedPreferences.edit { putString("MyString", value) }
        }
        
        »«ios:// Kotlin for iOS (src/iosMain/kotlin)
        «platform:import platform.Foundation.NSUserDefaults»
        actual fun saveValueLocally(value: String) {
            NSUserDefaults.standardUserDefaults
                    .setValue(value, forKey = "MyString")
        }»
        """.trimIndent()
            ) {
                "expect" { lineHeight(state >= 1) }
                "android" { lineHeight(state >= 2) }
                "ios" { lineHeight(state >= 3) }
                "platform" { zoomed(state == 4) }
            }

        }
    },
    Slide(
        name = "expect-di",
        stateCount = 4
    ) { state ->
        H2 {
            Text("Expect platform modules")
        }

        SourceCode(
            lang = "kotlin",
            code = """
                «common:// Common
                «common-hlgt:public expect val logModule: DI.Module»
    
                »«platform:// Android
                public actual val logModule by DI.Module {
                    bindSingleton<LoggerFactory> {
                        // SLF4J implementation
                    }
                }
    
                // iOS
                public actual val logModule by DI.Module {
                    bindSingleton<LoggerFactory> {
                        // OSLog implementation
                    }
                }
                »
            """.trimIndent()
        ) {
            "common" { lineHeight(state >= 1) }
            "platform" { lineHeight(state >= 2) }
            "common-hlgt" { zoomed(state >= 3) }
        }
    },
)