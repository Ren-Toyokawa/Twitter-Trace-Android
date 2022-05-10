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
import com.example.twitter_trace_android.data.repository.tweet.impl.tweetLists
import com.example.twitter_trace_android.data.repository.user.UserRepository
import com.example.twitter_trace_android.data.successOr
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
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
    val selectedListId: String = ""
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
    val selectedListId: String = ""
){
    val uiState: TimelineUiState = TimelineUiState(
        isLoading = isLoading,
        user = user,
        tweetListsList = tweetListsList,
        selectedListId = selectedListId
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
            val userInfoDeferred = async { userRepository.getUserInfo("11111111") }
            val userInfo = userInfoDeferred.await().successOr(User())
            val tweetListsListDeferred = tweetRepository.getTweetListsList(userInfo.id)
            val tweetListsList = tweetListsListDeferred.successOr(mutableListOf())

            viewModelState.update {
                it.copy(
                    user = userInfo,
                    tweetListsList = tweetListsList.toMutableStateList(),
                    selectedListId = tweetListsList.first().id
                )
            }

            // 選択中のリストを追加
            loadSelectedListTweet()
        }
    }

    fun selectListTab(listId: String) {
        viewModelState.update {
            it.copy(
                selectedListId = listId
            )
        }
        loadSelectedListTweet()
    }

    private fun refreshUserInfo() {
        viewModelScope.launch {
            val userInfoResult = async { userRepository.getUserInfo("11111111") }
            val userInfo = userInfoResult.await().successOr(User())

            viewModelState.update {
                it.copy(
                    user = userInfo
                )
            }
        }
    }


    private fun refreshTweetListsList(){
        viewModelScope.launch {
            val tweetListsListResult = async { tweetRepository.getTweetListsList(uiState.value.user.id) }
            val tweetListsList = tweetListsListResult.await().successOr(mutableListOf())

            viewModelState.update {
                it.copy(
                    tweetListsList = tweetListsList.toMutableStateList(),
                    selectedListId = tweetListsList.first().id
                )
            }
        }
    }

    private fun loadSelectedListTweet(){
        val tweetListsList = uiState.value.tweetListsList

        val currentSelectedListIndex = tweetListsList.indexOfFirst { it.id == uiState.value.selectedListId }
        val currentSelectedTweetList = uiState.value.tweetListsList[currentSelectedListIndex]
        // 現在選択しているツイートリストのツイートが空の場合新規で読み込む
        if ( currentSelectedTweetList.tweets.isEmpty() ) {
            viewModelScope.launch {
                val tweetResult = async { tweetRepository.getTweets(currentSelectedTweetList.belongUserIds) }

                uiState.value.tweetListsList[currentSelectedListIndex] = tweetListsList[currentSelectedListIndex].copy(
                    tweets = tweetResult.await().successOr(emptyList())
                )

                viewModelState.update {
                    it.copy(
                        tweetListsList = tweetListsList.toMutableStateList()
                    )
                }
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
