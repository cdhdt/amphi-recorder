// Added for amphi (2026) — tests de la resolution de source micro
package org.fossify.voicerecorder.amphi

import org.junit.Assert.assertEquals
import org.junit.Test

class AudioSourceResolverTest {
    // Valeurs des constantes MediaRecorder.AudioSource, inlinees a la compilation
    private val unprocessed = 9
    private val voiceRecognition = 6

    @Test
    fun `choisit UNPROCESSED quand l'appareil le supporte`() {
        assertEquals(unprocessed, AudioSourceResolver.resolve(unprocessedSupported = true))
    }

    @Test
    fun `retombe sur VOICE_RECOGNITION quand non supporte`() {
        assertEquals(voiceRecognition, AudioSourceResolver.resolve(unprocessedSupported = false))
    }
}
