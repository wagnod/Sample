package com.wagnod.auth.splash.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.wagnod.auth.R
import com.wagnod.auth.splash.ui.SplashContract.*
import com.wagnod.core_ui.navigators.main.Navigator
import com.wagnod.core_ui.theme.SampleTheme
import com.wagnod.core_ui.ui.progress.StubProgressBar
import org.koin.androidx.compose.getViewModel

@Composable
fun SplashMainScreen(
    navigator: Navigator,
    viewModel: SplashViewModel = getViewModel()
) {

    val listener = object : Listener {
        override fun navigateNext() {
            navigator.navigateToHome()
        }
    }

    LaunchedEffect(Unit) {
        viewModel.setEvent(Event.Init)
    }

    SplashContent(listener)
}

@Composable
private fun SplashContent(
    listener: Listener?
) = Box(
    modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
) {
    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.splash_animation)
    )
    val logoAnimationState = animateLottieCompositionAsState(composition)

    if (logoAnimationState.progress == 0f) {
        StubProgressBar()
    }
    LottieAnimation(
        composition = composition,
        progress = { logoAnimationState.progress },
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .align(Alignment.Center)
    )

    if (logoAnimationState.isAtEnd && logoAnimationState.isPlaying) {
        listener?.navigateNext()
    }
}

@Composable
@Preview(showBackground = true)
private fun SplashPreview() = SampleTheme {
    SplashContent(listener = null)
}