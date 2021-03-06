package com.example.twitter_trace_android.data.model

import androidx.annotation.DrawableRes
import com.example.twitter_trace_android.R

/**
 *
 *
 */
data class User(
    val id: String = "",
    val name: String = "",
    @DrawableRes val userIconId: Int = R.drawable.ic_launcher_foreground
)