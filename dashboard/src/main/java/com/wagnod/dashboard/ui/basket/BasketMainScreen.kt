package com.wagnod.dashboard.ui.basket

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wagnod.core.model.Item
import com.wagnod.core_ui.navigators.main.Navigator
import com.wagnod.core_ui.theme.textPrimary
import com.wagnod.core_ui.ui.counter.Counter
import com.wagnod.core_ui.ui.progress.CenterScreenProgressBar
import com.wagnod.core_ui.ui.toolbar.ToolbarBackButton
import com.wagnod.core_ui.ui.toolbar.ToolbarTitle
import com.wagnod.core_ui.ui.toolbar.ToolbarTop
import com.wagnod.dashboard.ui.basket.BasketContract.Event
import com.wagnod.dashboard.ui.basket.BasketContract.Listener
import com.wagnod.dashboard.ui.basket.BasketContract.State
import org.koin.androidx.compose.getViewModel

@Composable
fun BasketMainScreen(
    navigator: Navigator,
    viewModel: BasketViewModel = getViewModel()
) {
    val listener = object : Listener {

        override fun onBackClick() {
            navigator.navigateUp()
        }

        override fun onIncreaseCounterClick(item: Item) {
            viewModel.setEvent(Event.UpdateBasket(item.copy(count = item.count + 1)))
        }

        override fun onDecreaseCounterClick(item: Item) {
            viewModel.setEvent(Event.UpdateBasket(item.copy(count = item.count - 1)))
        }
    }

    LaunchedEffect(Unit) {
        viewModel.setEvent(Event.Init)
    }

    if (viewModel.viewState.value.isLoading) {
        BasketStub(listener)
    } else {
        BasketContent(state = viewModel.viewState.value, listener = listener)
    }
}

@Composable
private fun BasketContent(state: State, listener: Listener?) = Column() {
    ToolbarTop(
        title = {
            ToolbarTitle(title = "Basket")
        },
        backIcon = {
            ToolbarBackButton { listener?.onBackClick() }
        }
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 8.dp)
    ) {
        items(state.items) { item ->
            if (item.count > 0) {
                BasketItemView(item, listener)
            }
        }
    }
}

@Composable
private fun BasketStub(listener: Listener?) = Column(
    modifier = Modifier.fillMaxSize()
) {
    ToolbarTop(
        title = { ToolbarTitle(title = "Basket") },
        backIcon = { ToolbarBackButton { listener?.onBackClick() } }
    )
    CenterScreenProgressBar()
}

@Composable
private fun BasketItemView(item: Item, listener: Listener?) = Row(
    modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 6.dp, horizontal = 16.dp)
        .background(MaterialTheme.colors.background),
    horizontalArrangement = Arrangement.SpaceBetween
) {
    Text(
        text = item.name,
        modifier = Modifier
            .fillMaxHeight()
            .padding(8.dp),
        color = MaterialTheme.colors.textPrimary,
        style = MaterialTheme.typography.body1
    )

    Counter(
        onIncreaseClick = { listener?.onIncreaseCounterClick(item) },
        onDecreaseClick = { listener?.onDecreaseCounterClick(item) },
        initialCount = item.count,
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewBasket() {
    BasketContent(
        state = State(
            items = Item.getSampleData()
        ), listener = null
    )
}