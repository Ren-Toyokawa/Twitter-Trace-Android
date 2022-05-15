package com.example.twitter_trace_android.data.repository.tweet.impl

import com.example.twitter_trace_android.R
import com.example.twitter_trace_android.data.model.Tweet
import com.example.twitter_trace_android.data.model.User
import com.example.twitter_trace_android.ui.timeline.TweetList
import java.util.*


// region Tweet Lists
val tweetLists: MutableList<TweetList> = mutableListOf(
    // 最新ツイート
    TweetList(
        id = "11111111",
        name = "最新ツイート",
        belongUserIds = listOf("11111111", "11111112", "11111113")
    )
)

val tweetListLists: Map<String, MutableList<TweetList>> = mutableMapOf(
    "11111111" to mutableListOf(
        TweetList(
            id = "11111111",
            name = "最新ツイート",
            belongUserIds = listOf("11111111")
        ),
        TweetList(
            id = "11111112",
            name = "テスト",
            belongUserIds = listOf("11111112")
        )
    )
)
// endregion


// region Tweets
val tweets: List<Tweet> = listOf(
    Tweet(
        tweetedUser = User(
            id = "11111111",
            name = "太宰治",
            userIconId = R.drawable.dazai_osamu
        ),
        tweet = "古い友人にウィスキーやら毛布やらを奪われた挙句、威張るなと吐き捨てられたのだが...",
        tweetedAt = Date(),
        reply = false,
        retweeted = false,
        liked = false,
        replyCount = 0,
        retweetCount = 0,
        likeCount = 0
    ),
    Tweet(
        tweetedUser = User(
            id = "11111112",
            name = "太宰治@裏垢",
            userIconId = R.drawable.dazai_osamu
        ),
        tweet = "井伏先生に借りた借金をまだ返していないことに気がついた",
        tweetedAt = Date(),
        reply = false,
        retweeted = false,
        liked = false,
        replyCount = 0,
        retweetCount = 0,
        likeCount = 0
    )
)
// endregion
