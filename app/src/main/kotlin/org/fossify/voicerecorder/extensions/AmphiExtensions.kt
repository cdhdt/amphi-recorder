// Added for amphi (2026) — detection de la capacite UNPROCESSED de l'appareil
package org.fossify.voicerecorder.extensions

import android.content.Context
import android.media.AudioManager

fun Context.unprocessedAudioSupported(): Boolean {
    val am = getSystemService(Context.AUDIO_SERVICE) as? AudioManager ?: return false
    return am.getProperty(AudioManager.PROPERTY_SUPPORT_AUDIO_SOURCE_UNPROCESSED) == "true"
}
