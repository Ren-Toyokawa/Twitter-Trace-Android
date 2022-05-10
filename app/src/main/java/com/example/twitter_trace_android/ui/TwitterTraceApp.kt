package com.example.twitter_trace_android.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.twitter_trace_android.ui.theme.TwitterTraceAndroidTheme
import java.lang.reflect.Modifier

@Composable
fun TwitterTraceApp() {
    Surface {
        val navController = rememberNavController()
        TwitterTraceNavGraph(navController = navController)
    }
}