package com.perea.marc.streampad.model

import com.google.gson.annotations.SerializedName

data class TWGame(
    var id: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("box_art_url") var coverUrl: String? = null
) {
    fun getStreamThumbnail(): String? {
        return coverUrl?.replace("{width}x{height}", "500x500")
    }

    fun getStreamThumbnail(width: Int, height: Int): String? {
        return coverUrl?.replace("{width}x{height}", "${width}x${height}")
    }
}

data class TWGameResponse(
    var data: ArrayList<TWGame>? = null
)