package com.example.twitter_trace_android

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController


/**
 * Destinations used in the [JetnewsApp].
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