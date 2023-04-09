package slides

import net.kodein.pres.*
import net.kodein.pres.Transitions
import net.kodein.pres.Transitions.fade
import net.kodein.pres.emojis.Emoji
import net.kodein.pres.sourcecode.SourceCode
import net.kodein.pres.sourcecode.lineHeight
import net.kodein.pres.sourcecode.zoomed
import net.kodein.pres.widget.SubSlide
import org.jetbrains.compose.web.dom.*

val expect_actual = listOf(
    Slide(
        name = "expect_actual",
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
    Slide(name = "expect_class", stateCount = 2) {state ->
        H1({shownIf(state >= 1, fade)}) { Text(Emoji.x) }
        SourceCode(
            "kotlin",
            """
            expect class NetworkMonitor() {
                val isNetworkAvailable: Boolean
            }               
            """.trimIndent()
        )
    },
    Slide(name = "expect_factory", stateCount = 2) {state ->
        H1({shownIf(state >= 1, fade)}) { Text(Emoji.white_check_mark) }
        SourceCode(
            "kotlin",
            """
            expect fun NetworkMonitorFactory(): NetworkMonitor
            
            interface NetworkMonitor {
                val isNetworkAvailable: Boolean
            }
            """.trimIndent()
        )
    },
).animatedWith(Animations.Move(towards = Animations.Move.Towards.Bottom))
