package com.blizzard.jeuxfoot

import org.junit.Assert.assertEquals
import org.junit.Test

class PlaysTest {
    @Test
    fun listsPlaysInOrder() {
        assertEquals(
            listOf(
                "40 Hawaii",
                "40 Florida",
                "40 Hawaii University",
                "Massachusetts",
                "Massachusetts University",
                "Minnesota",
                "Timberwolves",
                "Protection 40/41",
                "Protection 42/43",
                "Denver",
                "Sacramento",
                "Jet Denver",
                "Denver Hollywood",
                "Denver Hollywood Jet",
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
                "protection_40_41" to "protection-40-41-anim.mp4",
                "protection_42_43" to "protection-42-43-anim.mp4",
                "denver" to "denver-anim.mp4",
                "sacramento" to "sacramento-anim.mp4",
                "jet_denver" to "jet-denver-anim.mp4",
                "denver_hollywood" to "denver-hollywood-anim.mp4",
                "denver_hollywood_jet" to "denver-hollywood-jet-anim.mp4",
            ),
            Plays.all.map { it.id to it.assetFileName },
        )
        assertEquals("asset:///raw/minnesota-anim.mp4", Plays.assetUri("minnesota-anim.mp4"))
    }
}
