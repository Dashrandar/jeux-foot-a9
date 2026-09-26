package com.blizzard.jeuxfoot

import org.junit.Assert.assertEquals
import org.junit.Test

class PlaysTest {
    @Test
    fun listsTheSevenPlaysInOrder() {
        assertEquals(
            listOf(
                "40 Hawaii",
                "40 Florida",
                "40 Hawaii University",
                "Massachusetts",
                "Massachusetts University",
                "Minnesota",
                "Timberwolves",
            ),
            Plays.all.map { it.title },
        )
    }

    @Test
    fun onlyMinnesotaMapsToAVideo() {
        val withVideo = Plays.all.filter { it.assetFileName != null }
        assertEquals(listOf("minnesota"), withVideo.map { it.id })
        assertEquals("minnesota.mp4", withVideo.single().assetFileName)
        assertEquals("asset:///raw/minnesota.mp4", Plays.assetUri("minnesota.mp4"))
    }
}
