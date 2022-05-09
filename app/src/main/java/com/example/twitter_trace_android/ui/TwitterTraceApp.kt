package com.example.twitter_trace_android.ui

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.twitter_trace_android.ui.theme.TwitterTraceAndroidTheme

@Composable
fun TwitterTraceApp() {
    TwitterTraceAndroidTheme(darkTheme = true) {
        Surface {
            val navController = rememberNavController()
            TwitterTraceNavGraph(navController = navController)
        }
    }
}