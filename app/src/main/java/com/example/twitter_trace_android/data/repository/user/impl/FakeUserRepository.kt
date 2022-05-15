package com.example.twitter_trace_android.data.repository.user.impl

import com.example.twitter_trace_android.data.Result
import com.example.twitter_trace_android.data.model.User
import com.example.twitter_trace_android.data.repository.tweet.TweetRepository
import com.example.twitter_trace_android.data.repository.tweet.impl.FakeTweetRepository
import com.example.twitter_trace_android.data.repository.tweet.impl.tweetLists
import com.example.twitter_trace_android.data.repository.user.UserRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

class FakeUserRepository @Inject constructor(): UserRepository {
    override suspend fun getUserInfo(userId: String): Result<User> {
        return withContext(Dispatchers.IO) {
            val user = users.find { it.id == userId }
            if (user == null) {
                Result.Error(IllegalArgumentException("Tweet List not found"))
            } else {
                Result.Success(user)
            }
        }
    }
}