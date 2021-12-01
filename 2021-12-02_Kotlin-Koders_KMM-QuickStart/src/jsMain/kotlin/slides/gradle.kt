package slides

import net.kodein.pres.Slide
import net.kodein.pres.sourcecode.SourceCode
import net.kodein.pres.sourcecode.lineHeight
import net.kodein.pres.sourcecode.shownIf


val gradle = Slide(
    name = "gradle",
    stateCount = 4
) { state ->
    SourceCode(
        lang = "kotlin",
        """
            plugins {
                kotlin("multiplatform") version "1.6.0"
                id("com.android.library")
            }

            kotlin {
            «t-a:    android()  // Jar
            »«t-i:
                ios {      // Framework
                    binaries { framework() }
                }
            »}
            «and:
            android {
                /* ... */
            }
            »
        """.trimIndent()
    ) {
        "t-a" { lineHeight(state >= 1) }
        "t-i" { lineHeight(state >= 2) }
        "and" { lineHeight(state >= 3) }
    }
}