package theme

import androidx.compose.ui.graphics.Color


object KodeinColors {
    val Light: Color = Color(0xFF_F7_E1_DE)
    val Dark: Color = Color(0xFF_24_08_21)
    val Darker: Color = Color(0xFF_12_04_11)

    val Orange: Color = Color(0xFF_E8_44_1F)
    val Purple: Color = Color(0xFF_92_1F_81)

    val Salmon: Color = Color(0xFF_F0_A6_98)
    val Byzantium: Color = Color(0xFF_48_0F_40)
    val Zinzolin: Color = Color(0xFF_6D_17_61)
    val Amethyst: Color = Color(0xFF_B3_5C_9D)
    val Glycine: Color = Color(0xFF_D3_9A_B8)
    val Coral: Color = Color(0xFF_EC_75_5B)
    val Copper: Color = Color(0xFF_A6_30_1F)
    val Rust: Color = Color(0xFF_65_1B_20)

    val Dark_Orange get() = Rust
    val Orange_Dark get() = Copper
    val Orange_Light get() = Coral
    val Light_Orange get() = Salmon

    val Dark_Purple get() = Byzantium
    val Purple_Dark get() = Zinzolin
    val Purple_Light get() = Amethyst
    val Light_Purple get() = Glycine
}

val Color.Companion.Kodein get() = KodeinColors