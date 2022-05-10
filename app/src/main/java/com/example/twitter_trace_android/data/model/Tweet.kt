package com.example.twitter_trace_android.data.model

import java.util.*

/**
 * リストやタイムラインに表示するつぶやき
 */
data class Tweet(
    val tweetedUser: User,
    val tweet: String,
    val tweetedAt: Date,
    val reply: Boolean = false,
    val retweeted: Boolean = false,
    val liked: Boolean = false,
    val replyCount: Int = 0,
    val retweetCount: Int = 0,
    val likeCount: Int = 0
)

