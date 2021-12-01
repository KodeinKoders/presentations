package slides

import net.kodein.pres.Slide
import net.kodein.pres.sourcecode.SourceCode


val gradleFinal = Slide(
    name = "gradle-final"
) {
    SourceCode(
        lang = "kotlin",
        """
            implementation(
                "org.jetbrains.kotlinx:kotlinx-coroutines-core:${'$'}kxcVer"
            )

            implementation("io.ktor:ktor-client-core:${'$'}ktorVer")
            implementation("org.kodein.db:kodein-db:${'$'}kdbVer")
            implementation("org.kodein.di:kodein-di:${'$'}kdiVer")
            
            //implementation("com.squareup.sqldelight:sqlite-driver:${'$'}sqldVer")
            //implementation("com.arkivanov.mvikotlin:mvikotlin:${'$'}mvikVer")
        """.trimIndent()
    ) {
    }

}
