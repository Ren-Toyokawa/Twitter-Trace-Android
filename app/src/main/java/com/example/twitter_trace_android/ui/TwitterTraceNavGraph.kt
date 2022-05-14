package com.example.twitter_trace_android.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.twitter_trace_android.data.repository.tweet.impl.FakeTweetRepository
import com.example.twitter_trace_android.data.repository.user.impl.FakeUserRepository
import com.example.twitter_trace_android.ui.timeline.TimelineRoute
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
//            val timelineViewModel:TimelineViewMode by viewModels()

            TimelineRoute(
                viewModel = timelineViewModel
            )
        }
    }
}


/**
 */
object TwitterTraceDestinations {
    const val TIMELINE_ROUTE = "timeline"
}

class TwitterTraceActions(navController: NavHostController) {
    val navigateToTimeline: () -> Unit = {
        navController.navigate(TwitterTraceDestinations.TIMELINE_ROUTE) {
            // ユーザーがアイテムを選択するときに、バックスタックに宛先の大きなスタックが構築されないように、グラフの開始宛先までポップアップします。
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // 同じアイテムを再選択するときは、同じ宛先の複数のコピーを避けてください
            launchSingleTop = true
            // 以前に選択したアイテムを再選択すると、状態が復元されます
            restoreState = true
        }
    }

}