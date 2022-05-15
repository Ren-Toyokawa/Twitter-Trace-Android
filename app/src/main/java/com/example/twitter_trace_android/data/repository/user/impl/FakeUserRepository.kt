package com.example.twitter_trace_android.data.repository.user.impl

import com.example.twitter_trace_android.data.Result
import com.example.twitter_trace_android.data.model.User
import com.example.twitter_trace_android.data.repository.user.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FakeUserRepository @Inject constructor() : UserRepository {
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