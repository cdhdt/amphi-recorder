// Added for amphi (2026) — tests de la resolution de source micro
package org.fossify.voicerecorder.amphi

import org.junit.Assert.assertEquals
import org.junit.Test

class AudioSourceResolverTest {
    // Valeurs des constantes MediaRecorder.AudioSource, inlinees a la compilation
    private val UNPROCESSED = 9
    private val VOICE_RECOGNITION = 6

    @Test
    fun `choisit UNPROCESSED quand l'appareil le supporte`() {
        assertEquals(UNPROCESSED, AudioSourceResolver.resolve(unprocessedSupported = true))
    }

    @Test
    fun `retombe sur VOICE_RECOGNITION quand non supporte`() {
        assertEquals(VOICE_RECOGNITION, AudioSourceResolver.resolve(unprocessedSupported = false))
    }
}
