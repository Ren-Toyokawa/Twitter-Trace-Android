package com.example.twitter_trace_android.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Modifier
import com.example.twitter_trace_android.ui.TwitterTraceApp
import com.example.twitter_trace_android.ui.theme.TwitterTraceAndroidTheme
import com.example.twitter_trace_android.ui.timeline.TimelineViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.internal.GeneratedComponent

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TwitterTraceAndroidTheme(darkTheme = true) {
                Box(
                    modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colors.background)
                ){
                    TwitterTraceApp()
                }

            }
        }
    }
}