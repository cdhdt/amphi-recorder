# amphi

**amphi** est un enregistreur audio pour capter des cours en amphithéâtre : un téléphone posé sur une table, à une vingtaine de mètres de l'enseignant, doit produire un enregistrement exploitable malgré la distance (« champ lointain »). C'est le premier jalon (« J1 — capture ») d'un projet plus large visant une transcription automatique auto-hébergée de ces cours.

Ce dépôt ne couvre pour l'instant que la capture : sélection automatique d'une source micro adaptée au champ lointain, profil d'encodage (Opus 16 kHz mono, avec repli M4A sur les appareils trop anciens), et le renommage de l'application. La transcription elle-même est un jalon ultérieur, hors périmètre.

## Origine et licence

**amphi** est un fork de [Fossify Voice Recorder](https://github.com/FossifyOrg/Voice-Recorder), publié sous licence **GPL-3.0**. Un grand merci et tout le crédit à l'équipe Fossify et à ses contributeurs pour le travail original : ce fork ne fait que réutiliser et adapter une base solide déjà existante à un usage précis (la capture en amphithéâtre). Ce crédit n'est pas une obligation de la GPL, c'est simplement la moindre des choses envers un projet dont on reprend le travail.

Ce fork est distribué, comme le projet amont, sous **GPL-3.0**. Tout binaire distribué (APK) est accompagné du code source complet correspondant — celui de ce dépôt — conformément aux termes de la licence. Le texte complet de la licence se trouve dans [`LICENSE`](LICENSE), conservé à l'identique. Aucune notice de licence ou de copyright du projet amont n'a été retirée ni altérée.

## Fichiers modifiés et créés par amphi

Conformément à la GPL-3.0 (obligation d'indiquer clairement les fichiers modifiés et la date), voici la liste des fichiers touchés par ce fork depuis la base upstream (commit `9536823`), établie à partir de `git diff --name-status 9536823..HEAD`. Toutes ces modifications datent de **2026**.

### Fichiers créés

| Fichier | Objet |
|---|---|
| `app/src/main/kotlin/org/fossify/voicerecorder/amphi/AudioSourceResolver.kt` | Résolution de la source micro pour le champ lointain (`UNPROCESSED` si l'appareil le permet, sinon repli sur `VOICE_RECOGNITION`), appliquée une seule fois au premier lancement. |
| `app/src/main/kotlin/org/fossify/voicerecorder/amphi/CaptureProfile.kt` | Profil d'encodage champ lointain : Opus 16 kHz mono sur Android 10+, repli M4A 16 kHz sur les versions plus anciennes. |
| `app/src/main/kotlin/org/fossify/voicerecorder/amphi/CaptureMetadata.kt` | Sidecar JSON de métadonnées de capture (source effective, encodage, appareil) pour le banc de mesure WER, écrit dans le stockage externe privé de l'app. |
| `app/src/main/kotlin/org/fossify/voicerecorder/extensions/AmphiExtensions.kt` | Détection de la capacité `PROPERTY_SUPPORT_AUDIO_SOURCE_UNPROCESSED` de l'appareil. |
| `app/src/test/kotlin/org/fossify/voicerecorder/amphi/AudioSourceResolverTest.kt` | Tests unitaires de la résolution de source micro. |
| `app/src/test/kotlin/org/fossify/voicerecorder/amphi/CaptureProfileTest.kt` | Tests unitaires du profil d'encodage champ lointain. |
| `app/src/test/kotlin/org/fossify/voicerecorder/amphi/CaptureMetadataTest.kt` | Tests unitaires du sidecar de métadonnées. |

### Fichiers modifiés

| Fichier | Objet de la modification |
|---|---|
| `.gitignore` | Exclusion des clés de signature locales (`*.jks`, `keystore.properties`), pour ne jamais committer de secret de signature. |
| `app/build.gradle.kts` | Ajout de la dépendance de test JUnit (`junit:junit:4.13.2`) ; suffixe `.amphi` sur l'`applicationId` du build `release`, pour ne pas entrer en collision d'installation avec l'application officielle Fossify ni porter son identité. |
| `app/src/main/kotlin/org/fossify/voicerecorder/activities/MainActivity.kt` | Application, au premier lancement, des réglages de capture par défaut adaptés au champ lointain. |
| `app/src/main/kotlin/org/fossify/voicerecorder/helpers/Config.kt` | Ajout de la méthode appliquant une seule fois les réglages de capture par défaut (source micro, extension, fréquence d'échantillonnage, débit) ; utilise désormais la clé de préférence nommée en constante. |
| `app/src/main/kotlin/org/fossify/voicerecorder/helpers/Constants.kt` | Ajout de la constante nommant la clé de préférence du drapeau d'application (idempotente) des défauts de capture champ lointain. |
| `app/src/main/kotlin/org/fossify/voicerecorder/recorder/MediaRecorderWrapper.kt` | Forçage de la capture en mono, adapté à la parole en champ lointain. |
| `app/src/main/kotlin/org/fossify/voicerecorder/services/RecorderService.kt` | Acquisition d'un `PARTIAL_WAKE_LOCK` pendant l'enregistrement, pour tenir sur un cours long avec l'écran éteint ; applique aussi les défauts de capture champ lointain sur le chemin widget/reprise en arrière-plan (qui n'instancie jamais `MainActivity`) ; écrit le sidecar de métadonnées de capture après finalisation du fichier audio. |
| `app/src/debug/res/values/strings.xml` | Nom affiché de la variante `debug` (`app_launcher_name`) : « Voice Recorder_debug » → « amphi_debug » (ce source set surcharge `main` pour toute variante debug, dont `coreDebug`). |
| `app/src/main/res/values/strings.xml` | Nom affiché de l'application (`app_launcher_name`) : « Voice Recorder » → « amphi ». |
| `app/src/main/res/values-fr/strings.xml` | Nom affiché de l'application en français (`app_launcher_name`) : « Enregistreur vocal » → « amphi », pour que le renommage soit aussi visible sur un appareil configuré en français. |
| `app/src/main/res/values/donottranslate.xml` | Nom interne de l'application (`app_name`) : « Fossify Voice Recorder » → « amphi ». |
| `README.md` | Réécriture pour ce fork : présentation du projet, crédit à Fossify, conformité GPL-3.0, liste des fichiers modifiés, prérequis de build. |

### Fichiers supprimés

Suppression de l'outillage GitHub hérité de l'amont qui, une fois le dépôt rendu public, aurait déclenché des sollicitations ou des exécutions vers l'infrastructure d'un tiers (Fossify) sans rapport avec ce fork :

| Fichier | Objet |
|---|---|
| `CODEOWNERS` | Désignait `@naveensingh` (mainteneur amont) en relecteur automatique de toute PR sur ce dépôt. |
| `.github/ISSUE_TEMPLATE/bug_report.yml`, `config.yml`, `feature_request.yml` | Renvoyaient les rapporteurs de bugs vers les issues et la FAQ de Fossify. |
| `.github/workflows/image-minimizer.yml`, `no-response.yml`, `pr-labeler.yml`, `pr.yml`, `prepare-release-pr.yml`, `release.yml`, `testing-build.yml`, `update-commons.yml`, `update-lint-baselines.yml`, `validate-fastlane-metadata.yml` | Workflows CI/CD amont (crons et déclencheurs de PR), dont un pipeline de publication Google Play, tous `uses: FossifyOrg/.github/…@main` avec `secrets: inherit`. |

`.github/dependabot.yml` a été conservé : il ne référence Fossify que via un nom de dépendance (`org.fossify:commons`) et ne délègue aucune exécution à l'infrastructure amont. Aucun `FUNDING.yml` ni `PULL_REQUEST_TEMPLATE` n'a été trouvé dans ce dépôt.

## Prérequis de build

- **JDK 21** — le JDK 25 casse la compatibilité avec l'Android Gradle Plugin (AGP) utilisé par ce projet.
- **`ANDROID_HOME`** pointant vers un SDK Android installé.
- L'artefact **distribué** est la variante **`coreRelease`** (`./gradlew :app:assembleCoreRelease`), minifiée (R8) et signée. La signature repose sur un fichier `keystore.properties` que chaque contributeur doit fournir lui-même (volontairement absent du dépôt) — ou, à défaut, sur les variables d'environnement `SIGNING_KEY_ALIAS`, `SIGNING_KEY_PASSWORD`, `SIGNING_STORE_FILE` et `SIGNING_STORE_PASSWORD`.
- La variante **`coreDebug`** (`./gradlew :app:assembleCoreDebug`) reste utile en développement (build non minifié, plus rapide à itérer).

## Avertissement légal

Cette application enregistre la voix de tiers — l'enseignant, mais aussi potentiellement d'autres étudiants présents dans l'amphithéâtre. **L'utilisateur de l'application est seul responsable du cadre légal dans lequel il effectue ses enregistrements** (information des personnes enregistrées, droit à l'image et à la voix, règlement de l'établissement, droit local applicable, etc.). Ni Fossify ni les contributeurs d'amphi ne sauraient être tenus responsables d'un usage non conforme.
