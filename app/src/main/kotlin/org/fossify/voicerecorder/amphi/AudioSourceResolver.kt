// Added for amphi (2026) — resolution de la source micro pour le champ lointain
package org.fossify.voicerecorder.amphi

import android.media.MediaRecorder

object AudioSourceResolver {
    /**
     * Choisit la meilleure source micro pour la parole en champ lointain.
     * Appelee une seule fois, au premier lancement : le resultat est ecrit
     * en clair dans les preferences, donc l'utilisateur peut ensuite en
     * changer librement depuis l'ecran de reglages existant.
     *
     * @param unprocessedSupported resultat de la capacite appareil
     *   (PROPERTY_SUPPORT_AUDIO_SOURCE_UNPROCESSED == "true").
     */
    fun resolve(unprocessedSupported: Boolean): Int =
        if (unprocessedSupported) {
            MediaRecorder.AudioSource.UNPROCESSED
        } else {
            MediaRecorder.AudioSource.VOICE_RECOGNITION
        }
}
