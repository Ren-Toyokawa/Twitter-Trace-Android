package com.example.twitter_trace_android.ui.timeline

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.twitter_trace_android.R
import com.example.twitter_trace_android.data.model.Tweet
import com.example.twitter_trace_android.data.repository.tweet.impl.FakeTweetRepository
import com.example.twitter_trace_android.data.repository.user.impl.FakeUserRepository
import com.example.twitter_trace_android.data.repository.user.impl.users
import com.example.twitter_trace_android.ui.theme.TwitterTraceAndroidTheme
import java.util.*

// region タイムライン画面
@Composable
fun TimelineRoute(
    viewModel: TimelineViewModel,
    uiState: TimelineUiState
) {
    TimelineScreen(
        uiState = uiState
    )
}

@Composable
fun TimelineScreen(
    uiState: TimelineUiState
) {
    Column(Modifier.padding(horizontal = 17.dp)) {
        // ヘッダー
        Header(iconImageUrl = uiState.user.userIconId)

        // リストタブ
        ListTabs(
            tweetLists = uiState.tweetListsList,
            selectedListIndex = uiState.selectedListIndex,
            Modifier.padding(top = 14.dp)
        )

        // ツイート一覧
        if (uiState.tweetListsList.isNotEmpty()) {
            val activeTweetList = uiState.tweetListsList[uiState.selectedListIndex]
            TweetLists(tweets = activeTweetList.tweets)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TwitterTraceAndroidTheme(darkTheme = true) {
        Surface {
            TimelineScreen(
                TimelineUiState(
                    user = users[0],
                    tweetListsList = emptyList<TweetList>().toMutableStateList(),
                    selectedListIndex = 0
                )
            )
        }
    }
}
// endregion

// region ヘッダー

@Composable
fun Header(iconImageUrl: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        UserIcon(iconImageUrl)
        Icon(
            painter = painterResource(id = R.drawable.ic_twitter),
            contentDescription = null,
            tint = MaterialTheme.colors.primary,
            modifier = Modifier.size(width = 26.dp, height = 22.dp)
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_recommendation),
            contentDescription = null,
            tint = MaterialTheme.colors.primary,
            modifier = Modifier.size(width = 21.dp, height = 21.dp)
        )
    }
}

@Composable
fun UserIcon(iconImageUrl: Int) {
    Image(
        painter = painterResource(iconImageUrl),
        contentDescription = "user_icon",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(28.dp)
            .clip(CircleShape)
            .border(1.dp, Color.Black.copy(alpha = 0.6f), CircleShape)
    )
}

// endregion

// region リストタブ
@Composable
fun ListTabs(tweetLists: SnapshotStateList<TweetList>, selectedListIndex: Int, modifier: Modifier = Modifier) {
    LazyRow(
        Modifier
            .fillMaxWidth()
            .composed { modifier },
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        itemsIndexed(tweetLists) {index, tweetList ->
            ListTab(
                listName = tweetList.name,
                isActive = index == selectedListIndex,
                modifier = Modifier.clickable {  }
            )
        }
    }
}

@Composable
fun ListTab(listName: String, isActive: Boolean = false, modifier: Modifier) {
    Column(
        Modifier
            .width(IntrinsicSize.Max)
            .then(modifier)
    ) {
        Text(
            text = listName,
            fontSize = 13.sp,
            color = if (isActive) Color.White else colorResource(id = R.color.passive_text),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 9.5.dp)
        )

        if (isActive) {
            Box(
                modifier = Modifier
                    .height(3.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(
                        color = colorResource(id = R.color.light_blue)
                    )
            )
        }
    }
}

// endregion

// region ツイートリスト
@Composable
fun TweetLists(tweets: List<Tweet>) {
    LazyColumn {
        items(tweets) { tweet ->
            TweetCell(tweet)
        }
    }
}

@Composable
fun TweetCell(tweet: Tweet) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 12.dp, vertical = 16.dp
            )
    ) {
        UserIcon(iconImageUrl = tweet.tweetedUser.userIconId)

        Column(
            Modifier.padding(start = 12.dp)
        ) {
            UserName(name = tweet.tweetedUser.name, userId = tweet.tweetedUser.id)
            TweetBody(tweet = tweet.tweet)
            ActionMenuView(modifier = Modifier.padding(top = 14.dp))
        }
    }
}

@Composable
fun UserName(name: String, userId: String) {
    Row {
        Text(
            text = name,
            fontSize = 13.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(
                    end = 5.dp
                )
        )
        Text(
            text = "@$userId",
            fontSize = 13.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

    }
}

@Composable
fun TweetBody(tweet: String) {
    Text(
        text = tweet,
        fontSize = 13.sp,
        color = Color.White,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    )
}

@Composable
fun ActionMenuView(modifier: Modifier = Modifier) {
    Row(
        Modifier
            .fillMaxWidth()
            .composed { modifier },
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ActionIcon(
            painterResourceId = R.drawable.ic_reply,
            count = 0
        )
        ActionIcon(
            painterResourceId = R.drawable.ic_retweet,
            count = 0
        )
        ActionIcon(
            painterResourceId = R.drawable.ic_like,
            count = 0
        )
        ActionIcon(
            painterResourceId = R.drawable.ic_baseline_share_24
        )
    }
}

@Composable
fun ActionIcon(painterResourceId: Int, count: Int? = null) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = painterResourceId),
            contentDescription = "none",
            tint = Color.LightGray,
            modifier = Modifier
                .size(width = 16.dp, height = 16.dp)
        )

        count?.let {
            Text(
                text = count.toString(),
                fontSize = 13.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(start = 13.dp)
            )
        }

    }

}

@Preview
@Composable
fun TweetCellPreview() {
    TwitterTraceAndroidTheme(darkTheme = true) {
        Surface {
            TweetCell(
                tweet = Tweet(
                    tweetedUser = users[0],
                    tweet = "test",
                    tweetedAt = Date()
                )
            )
        }
    }
}

// endregion
