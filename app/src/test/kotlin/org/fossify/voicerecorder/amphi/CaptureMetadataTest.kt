// Added for amphi (2026) — test de serialisation JSON du sidecar de metadonnees de capture
package org.fossify.voicerecorder.amphi

import org.junit.Assert.assertEquals
import org.junit.Test

class CaptureMetadataTest {
    @Test
    fun `serialise en JSON stable et complet`() {
        val m = CaptureMetadata(
            effectiveSource = 9,
            extension = 2,
            samplingRate = 16000,
            bitrate = 32000,
            channels = 1,
            deviceModel = "Pixel 7",
            appVersion = "1.0-amphi",
        )
        val expected = """{"appVersion":"1.0-amphi","bitrate":32000,"channels":1,""" +
            """"deviceModel":"Pixel 7","effectiveSource":9,"extension":2,"samplingRate":16000}"""
        assertEquals(expected, m.toJson())
    }
}
