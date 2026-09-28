package com.blizzard.jeuxfoot.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.blizzard.jeuxfoot.Play
import com.blizzard.jeuxfoot.Plays
import com.blizzard.jeuxfoot.R
import com.blizzard.jeuxfoot.ui.theme.Chalk
import com.blizzard.jeuxfoot.ui.theme.ChalkMuted
import com.blizzard.jeuxfoot.ui.theme.ChalkboardCard

private const val COLUMNS = 4

@Composable
fun PlayPickerScreen(onPlaySelected: (Play) -> Unit) {
    ChalkboardBackground {
        Column(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 22.dp, vertical = 18.dp),
        ) {
            PickerHeader()
            Spacer(Modifier.height(12.dp))
            LazyVerticalGrid(
                columns = GridCells.Fixed(COLUMNS),
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                items(Plays.all, key = { it.id }) { play ->
                    PlayCard(
                        play = play,
                        onClick = { onPlaySelected(play) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(108.dp),
                    )
                }
            }
        }
    }
}

@Composable
private fun PickerHeader() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(Modifier.weight(1f)) {
            Text(
                text = stringResource(R.string.picker_title),
                style = MaterialTheme.typography.headlineLarge,
            )
            Text(
                text = stringResource(R.string.picker_hint),
                style = MaterialTheme.typography.bodyMedium,
            )
        }
        Text(
            text = stringResource(R.string.picker_badge),
            style = MaterialTheme.typography.titleLarge,
            color = Chalk.copy(alpha = 0.85f),
        )
    }
}

@Composable
private fun PlayCard(
    play: Play,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val hasVideo = play.assetFileName != null
    val showDiagram = play.id == "minnesota"
    val description = stringResource(R.string.play_content_description, play.title)
    Box(
        modifier
            .clip(RoundedCornerShape(16.dp))
            .semantics(mergeDescendants = true) {
                role = Role.Button
                contentDescription = description
            }
            .clickable(onClick = onClick)
            .chalkBorder(),
        contentAlignment = Alignment.Center,
    ) {
        if (showDiagram) {
            Image(
                painter = painterResource(R.drawable.minnesota_chalkboard),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                alpha = 0.35f,
            )
        }
        Box(
            Modifier
                .matchParentSize()
                .drawBehind {
                    drawRect(
                        if (showDiagram) {
                            ChalkboardCard.copy(alpha = 0.62f)
                        } else {
                            ChalkboardCard.copy(alpha = 0.94f)
                        },
                    )
                },
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp),
        ) {
            Text(
                text = play.title,
                style = MaterialTheme.typography.titleLarge.copy(
                    shadow = Shadow(color = Color.Black.copy(alpha = 0.7f), blurRadius = 6f),
                    lineHeight = 24.sp,
                ),
                textAlign = TextAlign.Center,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = Chalk,
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = if (hasVideo) {
                    "▶  ${stringResource(R.string.has_video)}"
                } else {
                    stringResource(R.string.coming_soon)
                },
                style = MaterialTheme.typography.bodyMedium,
                color = if (hasVideo) Chalk else ChalkMuted.copy(alpha = 0.85f),
                textAlign = TextAlign.Center,
            )
        }
    }
}
