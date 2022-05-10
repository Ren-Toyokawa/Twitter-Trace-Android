package com.example.twitter_trace_android.data.repository.user

import com.example.twitter_trace_android.data.Result
import com.example.twitter_trace_android.data.model.User

interface UserRepository {
    suspend fun getUserInfo(userId: String): Result<User>
}