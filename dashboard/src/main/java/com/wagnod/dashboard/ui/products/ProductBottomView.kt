package com.wagnod.dashboard.ui.products

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wagnod.core.model.Item
import com.wagnod.core_ui.theme.SampleTheme
import com.wagnod.core_ui.theme.footnoteRegular
import com.wagnod.core_ui.theme.graphicsBlue
import com.wagnod.core_ui.theme.graphicsRed
import com.wagnod.core_ui.theme.textPrimary

@Composable
fun ProductBottomContent(
    item: Item,
    updateBasket: (Item) -> Unit,
) = Column(
    modifier = Modifier
        .fillMaxWidth()
        .padding(start = 16.dp, end = 16.dp, top = 8.dp)
) {
    Card(item)
    BasketButton(item, updateBasket)
}

@Composable
private fun Card(item: Item) = Column() {
    Text(
        text = item.name,
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.background)
            .padding(8.dp),
        color = MaterialTheme.colors.textPrimary,
        style = MaterialTheme.typography.h1
    )
    Text(
        text = item.description,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        color = MaterialTheme.colors.textPrimary,
        style = MaterialTheme.typography.footnoteRegular
    )
}

@Composable
private fun BasketButton(
    item: Item,
    updateBasket: (Item) -> Unit
) {
    val color: Color
    val text: String
    if (item.count > 0) {
        color = MaterialTheme.colors.graphicsRed
        text = "Remove from cart"
    } else {
        color = MaterialTheme.colors.graphicsBlue
        text = "Add to cart"
    }

    Button(
        onClick = { updateBasket.invoke(item) },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = color,
            disabledBackgroundColor = color
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 8.dp)
            .clip(RoundedCornerShape(8.dp))
    ) {
        Text(
            text = text,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .align(Alignment.CenterVertically),
            color = Color.White,
            style = MaterialTheme.typography.footnoteRegular
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductBottomPreview() = SampleTheme {
    ProductBottomContent(item = Item.getSampleData()[2]) {}
}