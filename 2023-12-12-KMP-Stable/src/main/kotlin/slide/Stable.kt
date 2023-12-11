package slide

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import org.kodein.pres.Slide
import org.kodein.pres.ui.BulletPoints
import org.kodein.pres.ui.SourceCode
import org.kodein.pres.ui.bold
import theme.*


private val stableKmp by Slide(
    stepCount = 4
) { step ->
    Title(buildAnnotatedString {
        append("Kotlin/Multiplatform is ")
        bold { append("Stable") }
        append("!")
    })

    @Composable
    fun FeatureBox(text: String) {
        Box(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .width(128.dp)
                .border(2.dp, Color.Kodein.Orange, RoundedCornerShape(4.dp))
                .background(Color.Kodein.Rust, RoundedCornerShape(4.dp))
                .padding(8.dp)
        ) {
            Text(
                text = text,
                textAlign = TextAlign.Center,
                fontSize = 1.2.em,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }

    AnimatedVisibility(step >= 1) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth().padding(top = 24.dp)
        ) {
            FeatureBox("Compiler\nSupport")
            FeatureBox("Language\nFeature")
            FeatureBox("Library\nAPIs")
        }
    }

    AnimatedVisibility(step >= 2) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth().padding(top = 24.dp)
        ) {
            FeatureBox("IDE\nSupport")
            FeatureBox("Build\nTooling")
        }
    }

    AnimatedVisibility(step >= 3) {
        URLText(
            url = "https://kotl.in/kmp-stability",
            style = TextStyle(fontSize = 1.2.em),
            modifier = Modifier.padding(top = 24.dp)
        )
    }
}

private val stableExpectActual by Slide(
    stepCount = 2
) { step ->
    Text(text = "AndroidMain", fontFamily = FontFamily.Monospace, color = Color.Kodein.Salmon, modifier = Modifier.align(Alignment.Start).padding(start = 24.dp))
    SourceCode(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .background(Color.Kodein.Byzantium, RoundedCornerShape(8.dp))
            .padding(8.dp)
    ) {
        val Cls = marker(step == 0)
        val Itf = marker(step >= 1)
        """
            ${Cls}expect class${X}${Itf}interface${X} Platform() { val name: String }
            ${Itf}expect fun Platform(): Platform${X}
            val platform = Platform()
        """.trimIndent()
    }

    Text(text = "AndroidMain", fontFamily = FontFamily.Monospace, color = Color.Kodein.Salmon, modifier = Modifier.align(Alignment.Start).padding(top = 16.dp, start = 24.dp))
    SourceCode(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .background(Color.Kodein.Byzantium, RoundedCornerShape(8.dp))
            .padding(8.dp)
    ) {
        val Cls = marker(step == 0)
        val Itf = marker(step >= 1)
        """
            ${Cls}actual ${X}class ${Itf}Android${X}Platform ${Cls}actual constructor()${X}${Itf}: Platform${X} {
                ${Cls}actual${X}${Itf}override${X} val name: String get() = "Android"
            }
            ${Itf}actual fun Platform(): Platform = AndroidPlatform()${X}
        """.trimIndent()
    }

    Text(text = "iosMain", fontFamily = FontFamily.Monospace, color = Color.Kodein.Salmon, modifier = Modifier.align(Alignment.Start).padding(top = 16.dp, start = 24.dp))
    SourceCode(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .background(Color.Kodein.Byzantium, RoundedCornerShape(8.dp))
            .padding(8.dp)
    ) {
        val Cls = marker(step == 0)
        val Itf = marker(step >= 1)
        """
            ${Cls}actual ${X}class ${Itf}iOS${X}Platform ${Cls}actual constructor()${X}${Itf}: Platform${X} {
                ${Cls}actual${X}${Itf}override${X} val name: String get() = "iOS"
            }
            ${Itf}actual fun Platform(): Platform = iOSPlatform()${X}
        """.trimIndent()
    }
}

val stableHierarchy by Slide(
    stepCount = 8
) { step ->
    Row(Modifier.fillMaxWidth()) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxHeight().weight(1f)
        ) {
            Text(text = "build.gradle.kts", fontFamily = FontFamily.Monospace, color = Color.Kodein.Salmon, modifier = Modifier.align(Alignment.Start).padding(start = 8.dp))
            SourceCode(
                Modifier
                    .fillMaxWidth()
                    .background(Color.Kodein.Byzantium, RoundedCornerShape(8.dp))
                    .padding(8.dp)
            ) {
                val WOs = marker(step >= 3)
                """
                    kotlin {
                        androidTarget()

                        iosArm64()
                        iosX64()
                        iosSimulatorArm64()
                    ${WOs}
                        watchosArm64()
                        watchosX64()
                        watchosSimulatorArm64()${X}
                    }
                """.trimIndent()
            }

            AnimatedVisibility(step >= 7) {
                Text(
                    text = "↑ Gradle Kotlin ↑\nAutocompletion\n& Discovery!",
                    fontWeight = FontWeight.Medium,
                    fontFamily = FontFamily.Picon.Extended,
                    fontSize = 1.1.em,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
                )
            }
        }
        Spacer(Modifier.width(16.dp))
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxHeight().weight(1f)
        ) {
            @Composable
            fun SourceSetBox(
                text: String,
                modifier: Modifier = Modifier
            ) {
                Box(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 2.dp)
                        .border(2.dp, Color.Kodein.Orange, RoundedCornerShape(4.dp))
                        .background(Color.Kodein.Rust, RoundedCornerShape(4.dp))
                        .padding(4.dp)
                ) {
                    Text(
                        text = text,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
            AnimatedVisibility(step >= 1) {
                Column {
                    AnimatedVisibility(step >= 6) {
                        Text(
                            text = "Default SourceSet Hierarchy:",
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
                        )
                    }

                    SourceSetBox("commonMain/\ncommonTest/")

                    SourceSetBox("androidMain/\nandroidTest/", Modifier.padding(top = 8.dp))

                    AnimatedVisibility(step < 2) {
                        Column(Modifier.padding(top = 8.dp)) {
                            SourceSetBox("iosX64Main/\niosX64Test/")
                            SourceSetBox("iosArm64Main/\niosArm64Test/")
                            SourceSetBox("iosSimulatorArm64Main/\niosSimulatorArm64Test/")
                        }
                    }
                    AnimatedVisibility(step >= 5) {
                        SourceSetBox("appleMain/\nappleTest/", Modifier.padding(top = 8.dp))
                    }
                    AnimatedVisibility(step >= 2) {
                        SourceSetBox("iosMain/\niosTest/", Modifier.padding(top = 8.dp))
                    }
                    AnimatedVisibility(step >= 4) {
                        SourceSetBox("watchosMain/\nwatchosTest/", Modifier.padding(top = 8.dp))
                    }
                }
            }
        }
    }
}

val stablePerformance by Slide {
    Title {
        Text("Build & Runtime performance\nis getting better\nat each new release ;)")
    }
    Spacer(Modifier.height(16.dp))
    Text("Checkout Kotlin 1.9.20 & Kotlin 2.0.0-Beta1!")
}

val Stable = VerticalSlides(
    stableKmp,
    stableExpectActual,
    stableHierarchy,
    stablePerformance
)
