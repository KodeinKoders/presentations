package slide

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import org.kodein.pres.Slide
import org.kodein.pres.Slides
import theme.Kodein
import theme.Title


private val Fleet by Slide {
    Title("Jetbrains Fleet")
    Image(
        painter = painterResource("fleet.png"),
        contentDescription = "Fleet",
        modifier = Modifier.height(128.dp)
    )

    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .weight(1f)
                .border(2.dp, Color.Kodein.Orange, RoundedCornerShape(4.dp))
                .background(Color.Kodein.Rust, RoundedCornerShape(4.dp))
                .padding(8.dp)
        ) {
            Text("Polyglot")
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .weight(1f)
                .border(2.dp, Color.Kodein.Orange, RoundedCornerShape(4.dp))
                .background(Color.Kodein.Rust, RoundedCornerShape(4.dp))
                .padding(8.dp)
        ) {
            Text("Distributed")
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .weight(1f)
                .border(2.dp, Color.Kodein.Orange, RoundedCornerShape(4.dp))
                .background(Color.Kodein.Rust, RoundedCornerShape(4.dp))
                .padding(8.dp)
        ) {
            Text("Collaborative")
        }
    }

    Text(
        text = "Supports Kotlin/Multiplatform AND Swift!",
        fontSize = 1.2.em,
        modifier = Modifier.padding(top = 16.dp)
    )
}

private val FleetDemo by Slide {
    Title("Fleet & Compose Multiplatform")
    Spacer(Modifier.height(24.dp))
    Title {
        Text(
            text = "Demo!",
            color = Color.Kodein.Orange
        )
    }
}

val FleetAndCompose = Slides(
    Fleet,
    FleetDemo
)
