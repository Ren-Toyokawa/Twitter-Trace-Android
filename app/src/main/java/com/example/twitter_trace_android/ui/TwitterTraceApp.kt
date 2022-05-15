package com.example.twitter_trace_android.ui

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController

@Composable
fun TwitterTraceApp() {
    Surface {
        val navController = rememberNavController()
        TwitterTraceNavGraph(navController = navController)
    }
}