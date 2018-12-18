package com.perea.marc.socialwall

import java.util.*

data class MessageModel(
    var username: String? = null,
    var text: String? = null,
    var createAt: Date? = null,
    var likes: Int? = null,
    var userId: String? = null
)