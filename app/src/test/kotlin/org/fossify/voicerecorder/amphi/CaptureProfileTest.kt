package org.fossify.voicerecorder.amphi

import org.fossify.voicerecorder.helpers.EXTENSION_M4A
import org.fossify.voicerecorder.helpers.EXTENSION_OGG
import org.junit.Assert.assertEquals
import org.junit.Test

class CaptureProfileTest {
    @Test
    fun `API 29+ utilise Opus 16kHz 32kbps`() {
        val p = farFieldProfile(sdkInt = 29)
        assertEquals(EXTENSION_OGG, p.extension)
        assertEquals(16000, p.samplingRate)
        assertEquals(32000, p.bitrate)
    }

    @Test
    fun `sous API 29 retombe sur M4A 16kHz 64kbps`() {
        val p = farFieldProfile(sdkInt = 28)
        assertEquals(EXTENSION_M4A, p.extension)
        assertEquals(16000, p.samplingRate)
        assertEquals(64000, p.bitrate)
    }

    @Test
    fun `la capture est mono`() {
        assertEquals(1, AMPHI_CHANNELS)
    }
}
