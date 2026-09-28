package com.blizzard.jeuxfoot.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.blizzard.jeuxfoot.ui.theme.Chalk
import com.blizzard.jeuxfoot.ui.theme.Chalkboard

@Composable
fun ChalkboardBackground(content: @Composable () -> Unit) {
    Box(Modifier.fillMaxSize()) {
        Canvas(Modifier.fillMaxSize()) {
            drawRect(Chalkboard)
            val line = Color.White.copy(alpha = 0.045f)
            val gap = 28.dp.toPx()
            var y = gap
            while (y < size.height) {
                drawLine(line, Offset(0f, y), Offset(size.width, y), strokeWidth = 1.5f)
                y += gap
            }
            val inset = 10.dp.toPx()
            drawRoundRect(
                color = Chalk.copy(alpha = 0.18f),
                topLeft = Offset(inset, inset),
                size = Size(size.width - inset * 2, size.height - inset * 2),
                cornerRadius = CornerRadius(6.dp.toPx()),
                style = Stroke(width = 2.dp.toPx()),
            )
        }
        content()
    }
}

fun Modifier.chalkBorder(): Modifier = drawBehind {
    val inset = 1.dp.toPx()
    drawRoundRect(
        color = Chalk.copy(alpha = 0.7f),
        topLeft = Offset(inset, inset),
        size = Size(size.width - inset * 2, size.height - inset * 2),
        cornerRadius = CornerRadius(16.dp.toPx()),
        style = Stroke(
            width = 1.6.dp.toPx(),
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(12f, 9f)),
        ),
    )
}
