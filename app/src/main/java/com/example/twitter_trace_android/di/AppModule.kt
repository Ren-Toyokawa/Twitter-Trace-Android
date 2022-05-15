package com.example.twitter_trace_android.di

import com.example.twitter_trace_android.data.repository.tweet.TweetRepository
import com.example.twitter_trace_android.data.repository.tweet.impl.FakeTweetRepository
import com.example.twitter_trace_android.data.repository.user.UserRepository
import com.example.twitter_trace_android.data.repository.user.impl.FakeUserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserRepositoryModule {

    @Singleton
    @Provides
    fun providesUserRepository(
        fakeUserRepository: FakeUserRepository
    ): UserRepository {
        return FakeUserRepository(
        )
    }
}

@Module
@InstallIn(SingletonComponent::class)
object TweetRepositoryModule {

    @Singleton
    @Provides
    fun providesTweetRepository(
        fakeTweetRepository: FakeTweetRepository
    ): TweetRepository {
        return FakeTweetRepository()
    }
}