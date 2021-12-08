package slides

import net.kodein.pres.Slide
import net.kodein.pres.sourcecode.SourceCode


val gradleFinal1 = Slide(
    name = "gradle-final"
) {
    SourceCode(
        lang = "kotlin",
        """
            implementation(
                "org.jetbrains.kotlinx:kotlinx-coroutines-core:${'$'}kxCVer"
            )
            implementation(
                "org.jetbrains.kotlinx:kotlinx-serialization-json:${'$'}kxSVer"
            )

            implementation("io.ktor:ktor-client-core:${'$'}ktorVer")
            implementation("org.kodein.db:kodein-db:${'$'}kdbVer")
            implementation("org.kodein.di:kodein-di:${'$'}kdiVer")
        """.trimIndent()
    ) {}
}

val gradleFinal2 = Slide(
    name = "gradle-final-2"
) {
    SourceCode(
        lang = "kotlin",
        """
            implementation(
                "org.jetbrains.kotlinx:kotlinx-coroutines-core:${'$'}kxCVer"
            )
            implementation(
                "org.jetbrains.kotlinx:kotlinx-serialization-json:${'$'}kxSVer"
            )

            implementation("io.ktor:ktor-client-core:${'$'}ktorVer")
            implementation("org.kodein.db:kodein-db:${'$'}kDbVer")
            implementation("org.kodein.di:kodein-di:${'$'}kDiVer")

            testImplementation("org.kodein.micromock:micro-mock:${'$'}kMmVer")
        """.trimIndent()
    ) {}
}
