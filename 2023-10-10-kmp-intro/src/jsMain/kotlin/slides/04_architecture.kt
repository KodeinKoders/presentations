package slides

import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.Text
import org.kodein.compose.html.pres.Animations
import org.kodein.compose.html.pres.Slide
import org.kodein.compose.html.pres.Slides
import org.kodein.compose.html.pres.Transitions
import org.kodein.compose.html.pres.emojis.Emoji
import org.kodein.compose.html.pres.shownIf
import org.kodein.compose.html.pres.sourcecode.SourceCode
import org.kodein.compose.html.pres.sourcecode.fontGrow
import org.kodein.compose.html.pres.sourcecode.lineHeight
import org.kodein.compose.html.pres.sourcecode.zoomed
import org.kodein.compose.html.pres.widget.SubSlide

val expect_actual = Slides(Animations.Move(towards = Animations.Move.Towards.Bottom)) {
    +Slide(
        name = "expect_actual",
        stateCount = 5
    ) { state ->
        SubSlide(state == 0, Transitions.Grow()) {
            H2 {
                Text("How to handle platform specific behavior?")
            }
        }
        SubSlide(state in 1..4, Transitions.Fade()) {
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
    }

    +Slide(name = "expect_class", stateCount = 6) {state ->
        H1({shownIf(state == 2, Transitions.Fade())}) { Text(Emoji.x) }
        SourceCode(
            "kotlin",
            """
            «ctx-class:
            // Kotlin common code (src/commonMain/kotlin)
            expect class Context
            »expect class NetworkMonitor(«ctx-line:context: Context») {
                val isNetworkAvailable: Boolean
            }               
            «android:
            // Kotlin for Android (src/androidMain/kotlin)
            typealias Context = android.content.Context
            actual class NetworkMonitor(context: Context) {
                val isNetworkAvailable: Boolean
                    get() = …
            }
            »«ios:
            // Kotlin for iOS (src/iosMain/kotlin)
            class Context { /* EMPTY CLASS */ }
            actual class NetworkMonitor(context: Context) {
                val isNetworkAvailable: Boolean
                    get() = …
            }
            »""".trimIndent()
        )  {
            "ctx-line" { fontGrow(state >= 2) }
            "ctx-class" { lineHeight(state >= 3) }
            "android" { lineHeight(state == 4) }
            "ios" { lineHeight(state >= 5) }
        }
    }

    +Slide(name = "expect_factory", stateCount = 5) {state ->
        H1({shownIf(state >= 4, Transitions.Fade())}) { Text(Emoji.white_check_mark) }
        SourceCode(
            "kotlin",
            """
            // Kotlin common code (src/commonMain/kotlin)
            expect fun NetworkMonitorFactory(): NetworkMonitor«common:
            interface NetworkMonitor {
                val isNetworkAvailable: Boolean
            }»
            «android:// Kotlin for Android (src/androidMain/kotlin)
            actual fun NetworkMonitorFactory(): NetworkMonitor 
                    = AndroidNetworkMonitor()
            class AndroidNetworkMonitor : NetworkMonitor { ... }
            
            »«ios:// Kotlin for iOS (src/iosMain/kotlin)
            actual fun NetworkMonitorFactory(): NetworkMonitor
                    = IosNetworkMonitor()
            class IosNetworkMonitor : NetworkMonitor { ... }
            »""".trimIndent()
        ) {
            "common" { lineHeight(state <= 2) }
            "android" { lineHeight(state >= 3) }
            "ios" { lineHeight(state >= 4) }
        }
    }

}
