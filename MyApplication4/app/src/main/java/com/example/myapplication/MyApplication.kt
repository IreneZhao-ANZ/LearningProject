package com.example.myapplication

import android.app.Application
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import dagger.hilt.android.HiltAndroidApp
import dev.enro.animation.direction
import dev.enro.annotations.NavigationComponent
import dev.enro.core.NavigationDirection
import dev.enro.core.controller.NavigationApplication
import dev.enro.core.controller.createNavigationController
import dev.enro.core.controller.createNavigationModule

val specificNavigationModule = createNavigationModule {
    animations {
        // Configure the default animations for destinations that are pushed
        direction(
            direction = NavigationDirection.Push,
            entering = fadeIn(animationSpec = tween(220, delayMillis = 90)),
            exiting = fadeOut(),
            returnEntering = fadeIn(),
            returnExiting = fadeOut(),
        )

    }
}

@HiltAndroidApp
@NavigationComponent
class MyApplication : Application(), NavigationApplication {
    override val navigationController = createNavigationController {
        module(specificNavigationModule) // install the module defined outside of the application
        animations {
            direction(
                direction = NavigationDirection.Push,
                entering = fadeIn(animationSpec = tween(220, delayMillis = 90)),
                exiting = fadeOut(),
                returnEntering = fadeIn(),
                returnExiting = fadeOut(),
            )
        }
    }
}
