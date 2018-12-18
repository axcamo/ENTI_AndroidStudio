package com.perea.marc.twitchcompanion.Model

import com.google.gson.annotations.SerializedName

/*
{
   "data": [
   {
       "id": "26007494656",
       "user_id": "23161357",
       "user_name": "LIRIK",
       "game_id": "417752",
       "community_ids": [
       "5181e78f-2280-42a6-873d-758e25a7c313",
       "848d95be-90b3-44a5-b143-6e373754c382",
       "fd0eab99-832a-4d7e-8cc0-04d73deb2e54"
       ],
       "type": "live",
       "title": "Hey Guys, It's Monday - Twitter: @Lirik",
       "viewer_count": 32575,
       "started_at": "2017-08-14T16:08:32Z",
       "language": "en",
       "thumbnail_url": "https://static-cdn.jtvnw.net/previews-ttv/live_user_lirik-{width}x{height}.jpg"
   },
   ...
   ],
   "pagination": {
   "cursor": "eyJiIjpudWxsLCJhIjp7Ik9mZnNldCI6MjB9fQ=="
   }
}
*/

data class TWStream(
    var id: String? = null,
    @SerializedName("user_id") var userId: String? = null,
    @SerializedName ("user_name") var userName: String? = null,
    @SerializedName ("game_id") var gameId: String? = null,
    var title: String? = null,
    @SerializedName ("viewer_count") var viewerCount: Int? = null,
    @SerializedName ("thumbnail_url") var thumbnailUrl: String? = null
)

data class TWStreamResponse(
    var data: ArrayList<TWStream>? = null
)