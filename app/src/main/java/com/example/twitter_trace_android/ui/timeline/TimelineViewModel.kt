package com.example.twitter_trace_android.ui.timeline

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.twitter_trace_android.data.model.Tweet
import com.example.twitter_trace_android.data.model.User
import com.example.twitter_trace_android.data.repository.tweet.TweetRepository
import com.example.twitter_trace_android.data.repository.user.UserRepository
import com.example.twitter_trace_android.data.successOr
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// region UI State
/**
 * Timeline のホイスティング状態
 */

data class TimelineUiState(
    val isLoading: Boolean = false,
    val user: User = User(),
    val tweetListsList: SnapshotStateList<TweetList> = mutableStateListOf(),
    val selectedListIndex: Int = 0
)

/**
 *
 *
 */
data class TweetList(
    val id: String = "",
    val name: String = "",
    val belongUserIds: List<String> = listOf(),

    val tweets: List<Tweet> = listOf()
)

// endregion

// region View Model State

/**
 *
 */

private data class TimelineViewModelState(
    val isLoading: Boolean = false,
    val user: User = User(),
    val tweetListsList: SnapshotStateList<TweetList> = mutableStateListOf(),
    val selectedListIndex: Int = 0
){
    val uiState: TimelineUiState = TimelineUiState(
        isLoading = isLoading,
        user = user,
        tweetListsList = tweetListsList,
        selectedListIndex = selectedListIndex
    )
}

// endregion

// region ViewModel
/**
 * Timeline 画面の信頼できる情報源
 *
 * 下記の責任を担います(参考: https://developer.android.com/jetpack/compose/state?hl=ja#viewmodels-source-of-truth)
 * - ビジネスレイヤやデータレイヤなど、通常は階層の他のレイヤに配置されるアプリのビジネス ロジックへのアクセスを提供する。
 * - 特定の画面に表示するアプリデータ（画面または UI の状態）を準備する。
 */
class TimelineViewModel(
    private val userRepository: UserRepository,
    private val tweetRepository: TweetRepository
) : ViewModel() {
    private val viewModelState = MutableStateFlow(TimelineViewModelState())

    val uiState = viewModelState
        .map { it.uiState }
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.uiState
        )

    init {
        viewModelScope.launch {
            val userInfoResult =
                withContext(Dispatchers.Default) {
                    // TODO: 固定値除去
                    userRepository.getUserInfo("11111111")
                }

            val userInfo = userInfoResult.successOr(User())
            val tweetListsListResult =
                withContext(Dispatchers.Default) {
                    tweetRepository.getTweetListsList(userInfo.id)
                }

            val tweetListsList = tweetListsListResult.successOr(mutableListOf())

            //
            val tweetResult =
                withContext(Dispatchers.Default) {
                    // 一番最初のリスト(最新ツイート)をあらかじめ取得し、表示する
                    tweetRepository.getTweets(tweetListsList[0].belongUserIds)
                }

            tweetListsList[0] = tweetListsList[0].copy(
                tweets = tweetResult.successOr(emptyList())
            )

            viewModelState.update {
                it.copy(
                    isLoading = false,
                    user = userInfo,
                    tweetListsList = tweetListsList.toMutableStateList(), // TODO
                    selectedListIndex = 0 // TODO
                )
            }
        }
    }

    /**
     */
    companion object {
        fun provideFactory(
            userRepository: UserRepository,
            tweetRepository: TweetRepository
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return TimelineViewModel(userRepository, tweetRepository) as T
            }
        }
    }
}

// endregion
