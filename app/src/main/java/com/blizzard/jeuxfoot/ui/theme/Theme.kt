package com.blizzard.jeuxfoot.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.blizzard.jeuxfoot.R

val Chalkboard = Color(0xFF16382B)
val ChalkboardDeep = Color(0xFF10281F)
val ChalkboardCard = Color(0xFF1E4A36)
val Chalk = Color(0xFFF4F1E6)
val ChalkMuted = Color(0xFFC9D4C5)

val Handwriting = FontFamily(Font(R.font.patrick_hand))

private val colorScheme = darkColorScheme(
    primary = Chalk,
    onPrimary = ChalkboardDeep,
    background = Chalkboard,
    onBackground = Chalk,
    surface = ChalkboardCard,
    onSurface = Chalk,
)

private val typography = Typography(
    headlineLarge = TextStyle(
        fontFamily = Handwriting,
        fontSize = 40.sp,
        letterSpacing = 2.sp,
        color = Chalk,
    ),
    titleLarge = TextStyle(
        fontFamily = Handwriting,
        fontSize = 22.sp,
        color = Chalk,
    ),
    bodyLarge = TextStyle(
        fontFamily = Handwriting,
        fontSize = 18.sp,
        color = Chalk,
    ),
    bodyMedium = TextStyle(
        fontFamily = Handwriting,
        fontSize = 16.sp,
        color = ChalkMuted,
    ),
)

@Composable
fun JeuxFootTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = colorScheme,
        typography = typography,
        content = content,
    )
}
