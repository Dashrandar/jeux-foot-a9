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
    fun everyPlayMapsToItsOwnVideo() {
        assertEquals(
            listOf(
                "40_hawaii" to "hawaii-anim.mp4",
                "40_florida" to "florida-anim.mp4",
                "40_hawaii_university" to "hawaii-university-anim.mp4",
                "massachusetts" to "massachusetts-anim.mp4",
                "massachusetts_university" to "massachusetts-university-anim.mp4",
                "minnesota" to "minnesota-anim.mp4",
                "timberwolves" to "timberwolves-anim.mp4",
            ),
            Plays.all.map { it.id to it.assetFileName },
        )
        assertEquals("asset:///raw/minnesota-anim.mp4", Plays.assetUri("minnesota-anim.mp4"))
    }
}
