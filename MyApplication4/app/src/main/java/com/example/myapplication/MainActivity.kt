package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.example.myapplication.ui.navigation.HomeDestination
import dagger.hilt.android.AndroidEntryPoint
import dev.enro.core.compose.rememberNavigationContainer
import dev.enro.core.container.EmptyBehavior


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val container = rememberNavigationContainer(
                root = HomeDestination,
                emptyBehavior = EmptyBehavior.CloseParent,
            )
            Box(modifier = Modifier.fillMaxSize()) {
                container.Render()
            }
        }
    }
}


