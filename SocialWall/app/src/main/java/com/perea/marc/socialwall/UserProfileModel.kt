package com.perea.marc.socialwall

data class  UserProfileModel (
    var username: String? = null,
    var userId: String? = null,
    var avatarUrl: String? = null,
    var email: String? = null
) {
    fun getChristmasUsername(): String {
        return username = "oh oh oh"
    }
}