package com.example.twitter_trace_android.data.repository.tweet.impl

import com.example.twitter_trace_android.data.Result
import com.example.twitter_trace_android.data.model.Tweet
import com.example.twitter_trace_android.data.repository.tweet.TweetRepository
import com.example.twitter_trace_android.ui.timeline.TweetList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FakeTweetRepository @Inject constructor() : TweetRepository {
    override suspend fun getTweets(userIds: List<String>): Result<List<Tweet>> {
        return withContext(Dispatchers.IO) {
            val tweetsResult = tweets.filter { userIds.contains(it.tweetedUser.id) }
            if (tweetsResult.isEmpty()) {
                Result.Error(IllegalArgumentException("Tweets not found"))
            } else {
                Result.Success(tweetsResult)
            }
        }
    }

    override suspend fun getTweetListsList(userId: String): Result<MutableList<TweetList>> {
        return withContext(Dispatchers.IO) {
            if (tweetListLists.containsKey(userId)) {
                val tweetListListsResult = tweetListLists.getValue(userId)
                Result.Success(tweetListListsResult)
            } else {
                Result.Error(IllegalArgumentException("List Not Found"))
            }
        }
    }
}