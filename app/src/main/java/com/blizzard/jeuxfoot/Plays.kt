package com.blizzard.jeuxfoot

/**
 * Un jeu de l'équipe. [assetFileName] est le MP4 dans `assets/raw/`,
 * ou null tant que l'animation n'est pas prête.
 */
data class Play(
    val id: String,
    val title: String,
    val assetFileName: String?,
)

/**
 * Catalogue des jeux, et table playId → fichier vidéo.
 *
 * Pour ajouter une animation :
 * 1. déposer le MP4 dans `app/src/main/assets/raw/`
 * 2. ajouter une ligne dans [videoByPlayId]
 */
object Plays {
    const val ASSET_DIR = "raw"

    private val videoByPlayId: Map<String, String> = mapOf(
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
    )

    val all: List<Play> = listOf(
        play("40_hawaii", "40 Hawaii"),
        play("40_florida", "40 Florida"),
        play("40_hawaii_university", "40 Hawaii University"),
        play("massachusetts", "Massachusetts"),
        play("massachusetts_university", "Massachusetts University"),
        play("minnesota", "Minnesota"),
        play("timberwolves", "Timberwolves"),
        play("protection_40_41", "Protection 40/41"),
        play("protection_42_43", "Protection 42/43"),
        play("denver", "Denver"),
        play("sacramento", "Sacramento"),
        play("jet_denver", "Jet Denver"),
        play("denver_hollywood", "Denver Hollywood"),
        play("denver_hollywood_jet", "Denver Hollywood Jet"),
    )

    fun byId(id: String): Play? = all.find { it.id == id }

    fun assetUri(fileName: String): String = "asset:///$ASSET_DIR/$fileName"

    private fun play(id: String, title: String) = Play(
        id = id,
        title = title,
        assetFileName = videoByPlayId[id],
    )
}
