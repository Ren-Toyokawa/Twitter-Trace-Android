package com.example.twitter_trace_android.data.repository.tweet

import com.example.twitter_trace_android.data.Result
import com.example.twitter_trace_android.data.model.Tweet
import com.example.twitter_trace_android.ui.timeline.TweetList

/**
 *
 */
interface TweetRepository {
    /**
     * 指定したユーザーのツイートを取得する
     */
    suspend fun getTweets(userIds: List<String>): Result<List<Tweet>>

    suspend fun getTweetListsList(userID: String): Result<MutableList<TweetList>>
}


