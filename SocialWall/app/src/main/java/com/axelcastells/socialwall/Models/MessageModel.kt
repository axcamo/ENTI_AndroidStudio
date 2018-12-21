package com.axelcastells.socialwall.Models

import java.util.*

data class MessageModel(
    // Message Model
    var username: String? = null,
    var text: String? = null,
    var createdAt: Date? = null,
    var likes: Int? = null,
    var userId: String? = null
) {

}