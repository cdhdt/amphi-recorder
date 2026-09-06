// Added for amphi (2026) — tracabilite de la configuration de capture (banc WER)
package org.fossify.voicerecorder.amphi

data class CaptureMetadata(
    val effectiveSource: Int,
    val extension: Int,
    val samplingRate: Int,
    val bitrate: Int,
    val channels: Int,
    val deviceModel: String,
    val appVersion: String,
) {
    /** JSON a cles triees, sans dependance externe, pour un diff stable. */
    fun toJson(): String {
        fun esc(s: String) = s.replace("\\", "\\\\").replace("\"", "\\\"")
        return buildString {
            append("{")
            append("\"appVersion\":\"").append(esc(appVersion)).append("\",")
            append("\"bitrate\":").append(bitrate).append(",")
            append("\"channels\":").append(channels).append(",")
            append("\"deviceModel\":\"").append(esc(deviceModel)).append("\",")
            append("\"effectiveSource\":").append(effectiveSource).append(",")
            append("\"extension\":").append(extension).append(",")
            append("\"samplingRate\":").append(samplingRate)
            append("}")
        }
    }
}

// Added for amphi (2026)
fun writeSidecar(context: android.content.Context, recordingUri: android.net.Uri, meta: CaptureMetadata) {
    val json = meta.toJson()
    val name = recordingUri.lastPathSegment?.substringAfterLast('/')?.substringBeforeLast('.')
        ?: "recording"
    // Ecrit dans le stockage externe prive de l'app (accessible via MTP / adb pull sans
    // permission depuis l'API 19), avec repli sur le stockage interne si indisponible.
    val base = context.getExternalFilesDir(null) ?: context.filesDir
    val dir = java.io.File(base, "capture-meta").apply { mkdirs() }
    java.io.File(dir, "$name.json").writeText(json)
}
