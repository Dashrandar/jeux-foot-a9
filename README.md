# Jeux Foot à 9

Application Android, en paysage, pour lancer les animations des jeux de l'équipe. Sept jeux sont listés. **Minnesota** lit sa vidéo en boucle, avec le son. Les autres affichent « Animation bientôt » tant qu'aucun MP4 n'est branché.

L'application fonctionne hors ligne. Elle ne demande pas Internet.

APK prêt à installer : [`artifacts/jeux-foot-debug.apk`](artifacts/jeux-foot-debug.apk)

## Installer l'APK

1. Copiez `artifacts/jeux-foot-debug.apk` sur le téléphone (câble, mail, Drive, clé USB…).
2. Ouvrez le fichier depuis l'application Fichiers.
3. Android bloque d'abord les applications qui ne viennent pas du Play Store. Autorisez la source qui ouvre l'APK :
   - **Android 8 et plus** : au moment de l'installation, touchez Réglages dans la fenêtre qui s'affiche, puis activez « Autoriser cette source » (ou « Installer des applications inconnues ») pour Fichiers, Chrome ou l'application utilisée.
   - On peut aussi passer par Réglages → Applications → Accès spécial → Installer des applications inconnues, puis choisir Fichiers.
4. Revenez en arrière et confirmez **Installer**.
5. Ouvrez **Jeux Foot**. Le téléphone se met en paysage. Touchez **Minnesota** : l'animation tourne en boucle, avec le son du hut, du snap et de la passe. **Retour** (ou le geste retour) ramène à la liste.

C'est un APK *debug*, signé avec la clé de développement. Il suffit pour l'installer directement sur les téléphones de l'équipe. Il ne passe pas par le Play Store.

## Ajouter la vidéo d'un jeu

Chaque animation est un fichier MP4 (image et son). Le lien entre un jeu et son fichier est une seule table, `videoByPlayId`, dans `app/src/main/java/com/blizzard/jeuxfoot/Plays.kt`.

1. Copiez le MP4 dans `app/src/main/assets/raw/`.
   Exemple : `app/src/main/assets/raw/40_hawaii.mp4`
2. Dans `Plays.kt`, ajoutez une ligne :

```kotlin
private val videoByPlayId: Map<String, String> = mapOf(
    "minnesota" to "minnesota.mp4",
    "40_hawaii" to "40_hawaii.mp4",
)
```

L'identifiant (`40_hawaii`, `massachusetts`, …) est déjà défini dans la liste `Plays.all`. Seul le nom de fichier est à renseigner. Sans ligne dans la table, l'écran du jeu affiche « Animation bientôt ».

3. Recompilez (JDK 17+, Android SDK, `ANDROID_HOME` ou `local.properties`) :

```bash
export ANDROID_HOME="$HOME/android-sdk"
./gradlew assembleDebug
```

L'APK debug est recopié dans `artifacts/jeux-foot-debug.apk`. Réinstallez-le sur le téléphone (Android propose de mettre à jour l'application).

Le lecteur utilise Media3 / ExoPlayer : lecture en boucle (`REPEAT_MODE_ONE`), son activé, plein écran en paysage.

Les titres utilisent la police [Patrick Hand](https://fonts.google.com/specimen/Patrick+Hand) (SIL Open Font License, voir `third_party/fonts/PatrickHand-OFL.txt`).

## Jeux

| Jeu | Identifiant | Vidéo |
| --- | --- | --- |
| 40 Hawaii | `40_hawaii` | à ajouter |
| 40 Florida | `40_florida` | à ajouter |
| 40 Hawaii University | `40_hawaii_university` | à ajouter |
| Massachusetts | `massachusetts` | à ajouter |
| Massachusetts University | `massachusetts_university` | à ajouter |
| Minnesota | `minnesota` | `assets/raw/minnesota.mp4` |
| Timberwolves | `timberwolves` | à ajouter |
