package com.wagnod.core_ui.ui.toolbar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wagnod.core_ui.theme.SampleTheme
import com.wagnod.core_ui.theme.calloutMedium
import com.wagnod.core_ui.theme.mainBackgroundColor
import com.wagnod.core_ui.theme.textPrimary

@Composable
fun ToolbarTop(
    modifier: Modifier = Modifier,
    title: @Composable RowScope.() -> Unit = {},
    backIcon: @Composable BoxScope.() -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    showElevation: Boolean = false,
    blackColors: Boolean = false
) {
    val backgroundColor = when {
        blackColors -> MaterialTheme.colors.mainBackgroundColor
        else -> Color.White
    }
    Surface(
        modifier = modifier,
        elevation = if (showElevation) 4.dp else 0.dp
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .background(backgroundColor)
                .height(56.dp)
        ) {
            Box(modifier = Modifier.align(Alignment.CenterStart)) {
                backIcon()
            }
            Row(modifier = Modifier.align(Alignment.Center)) {
                title()
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
            ) {
                actions()
            }
        }
    }
}

@Composable
fun ToolbarTitle(
    title: String,
    textColor: Color = MaterialTheme.colors.textPrimary
) = Row(
    verticalAlignment = Alignment.CenterVertically
) {
    Text(
        text = title,
        color = textColor,
        style = MaterialTheme.typography.calloutMedium
    )
}

@Composable
fun ToolbarBackButton(
    blackColors: Boolean = false,
    onBack: () -> Unit
) {
    val iconColor = when {
        blackColors -> Color.White
        else -> Color.Black
    }
    Box(
        modifier = Modifier
            .padding(start = 6.dp)
            .clip(RoundedCornerShape(14.dp))
            .clickable { onBack() }
    ) {
        Icon(
            painter = rememberVectorPainter(Icons.Default.ArrowBack),
            contentDescription = "Toolbar back icon",
            tint = iconColor,
            modifier = Modifier.padding(6.dp)
        )
    }
}

@Composable
fun ToolbarSearchButton(
    blackColors: Boolean = false,
    onSearch: () -> Unit
) {
    val iconColor = when {
        blackColors -> Color.White
        else -> Color.Black
    }
    Box(
        modifier = Modifier
            .padding(end = 6.dp)
            .clip(RoundedCornerShape(14.dp))
            .clickable { onSearch() }
    ) {
        Icon(
            painter = rememberVectorPainter(Icons.Default.Search),
            contentDescription = "Toolbar search icon",
            tint = iconColor,
            modifier = Modifier.padding(6.dp)
        )
    }
}

@Composable
@Preview
private fun PreviewToolbarTop() = SampleTheme {
    ToolbarTop(
        title = { ToolbarTitle(title = "Shop") },
        backIcon = { ToolbarBackButton() {} },
        actions = { ToolbarSearchButton() {} }
    )
}