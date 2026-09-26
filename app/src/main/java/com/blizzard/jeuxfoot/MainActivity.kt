package com.blizzard.jeuxfoot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.blizzard.jeuxfoot.ui.PlayPickerScreen
import com.blizzard.jeuxfoot.ui.PlayPlayerScreen
import com.blizzard.jeuxfoot.ui.theme.JeuxFootTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        hideSystemBars()
        setContent {
            JeuxFootTheme {
                var playId by rememberSaveable { mutableStateOf<String?>(null) }
                val play = playId?.let(Plays::byId)
                if (play == null) {
                    PlayPickerScreen(onPlaySelected = { playId = it.id })
                } else {
                    PlayPlayerScreen(play = play, onBack = { playId = null })
                }
            }
        }
    }

    private fun hideSystemBars() {
        val controller = WindowCompat.getInsetsController(window, window.decorView)
        controller.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        controller.hide(WindowInsetsCompat.Type.systemBars())
    }
}
