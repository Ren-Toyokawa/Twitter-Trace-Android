package com.example.twitter_trace_android.data.repository.user.impl

import com.example.twitter_trace_android.R
import com.example.twitter_trace_android.data.model.User

// region Users
val OsamuDazai: User = User(
    id = "11111111",
    name = "太宰治",
    userIconId = R.drawable.dazai_osamu
)


val users: MutableList<User> = mutableListOf(
    OsamuDazai
)
// endregion