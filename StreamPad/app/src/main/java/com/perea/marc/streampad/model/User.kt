package com.perea.marc.streampad.model

import com.google.gson.annotations.SerializedName

data class TWUser(
    var id: String? = null,
    var login: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("display_name") var displayName: String? = null,
    var description: String? = null,
    @SerializedName("view_count") var viewCount : Int? = null,
    var email: String? = null,
    @SerializedName("profile_image_url") var profileImageUrl: String? = null
) {
    fun getProfileThumbnail(): String? {
        return profileImageUrl?.replace("{width}x{height}", "500x500")
    }

    fun getProfileThumbnail(width: Int, height: Int): String? {
        return profileImageUrl?.replace("{width}x{height}", "${width}x${height}")
    }
}

data class TWUserResponse(
    var data: ArrayList<TWUser>? = null
)