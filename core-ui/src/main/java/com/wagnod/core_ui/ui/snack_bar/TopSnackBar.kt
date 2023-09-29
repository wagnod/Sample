package com.wagnod.core_ui.ui.snack_bar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

@Composable
fun TopSnackBar(params: SnackBarParams) {
    val statusBarHeight = WindowInsets.statusBars.getTop(LocalDensity.current)

    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
            .padding(top = statusBarHeight.dp)
            .background(params.toStatusBarColor)
    ) {
        Text(
            text = params.text,
            color = Color.White,
            style = MaterialTheme.typography.body2,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
    }
}