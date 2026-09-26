package com.blizzard.jeuxfoot.ui

import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.annotation.OptIn
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.media3.common.AudioAttributes
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import com.blizzard.jeuxfoot.Play
import com.blizzard.jeuxfoot.Plays
import com.blizzard.jeuxfoot.R
import com.blizzard.jeuxfoot.ui.theme.Chalk
import com.blizzard.jeuxfoot.ui.theme.Chalkboard
import com.blizzard.jeuxfoot.ui.theme.ChalkboardDeep

@Composable
fun PlayPlayerScreen(play: Play, onBack: () -> Unit) {
    BackHandler(onBack = onBack)
    val fileName = play.assetFileName
    if (fileName == null) {
        ComingSoon(play = play, onBack = onBack)
    } else {
        LoopingVideo(play = play, fileName = fileName, onBack = onBack)
    }
}

@Composable
private fun ComingSoon(play: Play, onBack: () -> Unit) {
    ChalkboardBackground {
        Box(Modifier.fillMaxSize()) {
            BackChip(onBack = onBack, modifier = Modifier.align(Alignment.TopStart))
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text(text = play.title, style = MaterialTheme.typography.headlineLarge)
                Text(
                    text = stringResource(R.string.coming_soon),
                    style = MaterialTheme.typography.titleLarge,
                    color = Chalk.copy(alpha = 0.8f),
                )
            }
        }
    }
}

@OptIn(UnstableApi::class)
@Composable
private fun LoopingVideo(play: Play, fileName: String, onBack: () -> Unit) {
    val context = LocalContext.current
    var failed by remember(fileName) { mutableStateOf(false) }
    val player = remember(fileName) {
        ExoPlayer.Builder(context).build().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setUsage(C.USAGE_MEDIA)
                    .setContentType(C.AUDIO_CONTENT_TYPE_MOVIE)
                    .build(),
                /* handleAudioFocus = */ true,
            )
            setMediaItem(MediaItem.fromUri(Uri.parse(Plays.assetUri(fileName))))
            repeatMode = Player.REPEAT_MODE_ONE
            volume = 1f
            playWhenReady = true
            addListener(object : Player.Listener {
                override fun onPlayerError(error: PlaybackException) {
                    failed = true
                }
            })
            prepare()
        }
    }

    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(player, lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_START -> player.play()
                Lifecycle.Event.ON_STOP -> player.pause()
                else -> Unit
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
            player.release()
        }
    }

    val boardColor = Chalkboard.toArgb()
    Box(
        Modifier
            .fillMaxSize()
            .background(Chalkboard),
    ) {
        if (failed) {
            ComingSoon(play = play, onBack = onBack)
        } else {
            AndroidView(
                modifier = Modifier.fillMaxSize(),
                factory = { viewContext ->
                    PlayerView(viewContext).apply {
                        this.player = player
                        useController = false
                        resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT
                        setShutterBackgroundColor(boardColor)
                        setBackgroundColor(boardColor)
                        keepScreenOn = true
                    }
                },
                update = { view -> view.player = player },
            )
            BackChip(onBack = onBack, modifier = Modifier.align(Alignment.TopStart))
        }
    }
}

@Composable
private fun BackChip(onBack: () -> Unit, modifier: Modifier = Modifier) {
    Text(
        text = "←  ${stringResource(R.string.back)}",
        style = MaterialTheme.typography.titleLarge,
        color = Chalk,
        modifier = modifier
            .padding(16.dp)
            .clip(RoundedCornerShape(50))
            .background(ChalkboardDeep.copy(alpha = 0.82f))
            .clickable(onClick = onBack)
            .padding(horizontal = 16.dp, vertical = 10.dp),
    )
}
