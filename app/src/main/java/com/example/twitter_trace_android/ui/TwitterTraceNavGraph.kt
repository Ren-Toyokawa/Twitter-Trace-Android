package com.example.twitter_trace_android.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.twitter_trace_android.TwitterTraceDestinations
import com.example.twitter_trace_android.data.repository.tweet.impl.FakeTweetRepository
import com.example.twitter_trace_android.data.repository.user.impl.FakeUserRepository
import com.example.twitter_trace_android.ui.timeline.TimelineRoute
import com.example.twitter_trace_android.ui.timeline.TimelineScreen
import com.example.twitter_trace_android.ui.timeline.TimelineUiState
import com.example.twitter_trace_android.ui.timeline.TimelineViewModel

@Composable
fun TwitterTraceNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = TwitterTraceDestinations.TIMELINE_ROUTE,
        modifier = modifier
    ) {
        composable(TwitterTraceDestinations.TIMELINE_ROUTE) {
            // TODO: hiltでもっと良くかけないか調査
            val timelineViewModel:TimelineViewModel = viewModel(
                factory = TimelineViewModel.provideFactory(
                    FakeUserRepository(),
                    FakeTweetRepository()
                )
            )

            TimelineRoute(
                viewModel = timelineViewModel
            )
        }
    }
}