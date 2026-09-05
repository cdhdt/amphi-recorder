// Added for amphi (2026) — profil d'encodage pour la parole en champ lointain
package org.fossify.voicerecorder.amphi

import org.fossify.voicerecorder.helpers.EXTENSION_M4A
import org.fossify.voicerecorder.helpers.EXTENSION_OGG

/** Capture mono : seule source de verite, lue par le wrapper et les metadonnees. */
const val AMPHI_CHANNELS = 1

data class CaptureProfile(
    val extension: Int,
    val samplingRate: Int,
    val bitrate: Int,
)

/** @param sdkInt Build.VERSION.SDK_INT, injecte pour la testabilite. */
fun farFieldProfile(sdkInt: Int): CaptureProfile =
    if (sdkInt >= 29) {
        CaptureProfile(EXTENSION_OGG, samplingRate = 16000, bitrate = 32000)
    } else {
        CaptureProfile(EXTENSION_M4A, samplingRate = 16000, bitrate = 64000)
    }
