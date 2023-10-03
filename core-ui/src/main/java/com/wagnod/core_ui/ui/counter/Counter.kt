package com.wagnod.core_ui.ui.counter

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wagnod.core_ui.theme.SampleTheme
import com.wagnod.core_ui.theme.backgroundBrandTransparent
import com.wagnod.core_ui.theme.link

@Composable
fun Counter(
    onIncreaseClick: () -> Unit,
    onDecreaseClick: () -> Unit,
    modifier: Modifier = Modifier,
    initialCount: Int = 0
) {
    val counter = remember { mutableStateOf(initialCount) }

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(color = MaterialTheme.colors.backgroundBrandTransparent)
    ) {
        Box(
            modifier = Modifier
                .padding(start = 6.dp)
                .clip(RoundedCornerShape(3.dp))
                .clickable {
                    onDecreaseClick.invoke()
                    counter.value -= 1
                }
        ) {
            Icon(
                painter = rememberVectorPainter(Icons.Default.Remove),
                contentDescription = "Decrease counter",
                tint = MaterialTheme.colors.link,
                modifier = Modifier.padding(6.dp)
            )
        }

        Box(
            modifier = Modifier
                .padding(start = 6.dp)
                .clip(RoundedCornerShape(3.dp))
        ) {
            Text(
                text = counter.value.toString(),
                color = MaterialTheme.colors.link,
                modifier = Modifier.padding(6.dp)
            )
        }

        Box(
            modifier = Modifier
                .padding(start = 6.dp)
                .clip(RoundedCornerShape(3.dp))
                .clickable {
                    onIncreaseClick.invoke()
                    counter.value += 1
                }
        ) {
            Icon(
                painter = rememberVectorPainter(Icons.Default.Add),
                contentDescription = "Increase counter",
                tint = MaterialTheme.colors.link,
                modifier = Modifier.padding(6.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CounterPreview() = SampleTheme() {
    Counter(onIncreaseClick = {}, onDecreaseClick = {})
}