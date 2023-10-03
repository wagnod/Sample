package com.wagnod.dashboard.ui.products

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wagnod.core.model.Category
import com.wagnod.core.model.Item
import com.wagnod.core_ui.navigators.main.Navigator
import com.wagnod.core_ui.theme.footnoteRegular
import com.wagnod.core_ui.theme.graphicsSecondary
import com.wagnod.core_ui.theme.graphicsTertiary
import com.wagnod.core_ui.theme.textPrimary
import com.wagnod.core_ui.ui.progress.CenterScreenProgressBar
import com.wagnod.core_ui.ui.toolbar.ToolbarBackButton
import com.wagnod.core_ui.ui.toolbar.ToolbarTitle
import com.wagnod.core_ui.ui.toolbar.ToolbarTop
import com.wagnod.dashboard.R
import com.wagnod.dashboard.ui.products.ProductsContract.Event
import com.wagnod.dashboard.ui.products.ProductsContract.Listener
import com.wagnod.dashboard.ui.products.ProductsContract.State
import org.koin.androidx.compose.getViewModel

@Composable
fun ProductsMainScreen(
    selectedCategory: String,
    navigator: Navigator,
    viewModel: ProductsViewModel = getViewModel()
) {
    val listener = object : Listener {
        override fun onCategoryChange(category: String) {
            viewModel.setEvent(Event.CategoryChanged(category))
        }

        override fun onBackClick() {
            navigator.navigateUp()
        }

        override fun onBasketClick() {
            navigator.homeNavigator.toBasket()
        }

        override fun onProductClick(item: Item) {
            viewModel.setEvent(Event.ShowBottomSheet(item))
        }
    }

    LaunchedEffect(Unit) {
        viewModel.setEvent(Event.Init(selectedCategory))
    }

    if (viewModel.viewState.value.isLoading) {
        ProductsStub(selectedCategory, listener)
    } else {
        ProductsContent(state = viewModel.viewState.value, listener)
    }
}

@Composable
private fun ProductsContent(state: State, listener: Listener?) = Column() {
    ToolbarTop(
        title = { ToolbarTitle(title = state.selectedCategory) },
        backIcon = { ToolbarBackButton { listener?.onBackClick() } },
        actions = {
            Box(
                modifier = Modifier
                    .padding(end = 6.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .clickable { listener?.onBasketClick() }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.basket),
                    contentDescription = "Toolbar back icon",
                    modifier = Modifier
                        .size(40.dp)
                        .padding(6.dp)
                )
            }
        }
    )

    val categoriesNames = state.categories.map { it.name }
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
    ) {
        items(categoriesNames) { category ->
            CategoryChip(
                name = category,
                listener = listener,
                selected = (category == state.selectedCategory)
            )
        }
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(1),
        modifier = Modifier.padding(16.dp)
    ) {
        val category = state.categories.find { it.name == state.selectedCategory } ?: Category()

        items(category.items) { item ->
            ProductView(item, listener)
        }
    }
}

@Composable
private fun CategoryChip(
    name: String,
    listener: Listener?,
    selected: Boolean = false
) {
    val background =
        if (selected) MaterialTheme.colors.graphicsSecondary else MaterialTheme.colors.graphicsTertiary
    Box(
        modifier = Modifier
            .padding(horizontal = 6.dp, vertical = 3.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(background)
            .padding(horizontal = 4.dp)
            .clickable { listener?.onCategoryChange(name) },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = name,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            color = MaterialTheme.colors.textPrimary,
            style = MaterialTheme.typography.body1
        )
    }
}

@Composable
private fun ProductView(item: Item, listener: Listener?) = Column(
    modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 4.dp, horizontal = 16.dp)
        .background(MaterialTheme.colors.background)
        .clickable { listener?.onProductClick(item) }
) {
    Text(
        text = item.name,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        color = MaterialTheme.colors.textPrimary,
        style = MaterialTheme.typography.body1
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

/**
 * LOADING STUB
 */
@Composable
private fun ProductsStub(
    name: String,
    listener: Listener?
) = Column(
    modifier = Modifier.fillMaxSize()
) {
    ToolbarTop(
        title = { ToolbarTitle(title = name) },
        backIcon = { ToolbarBackButton { listener?.onBackClick() } }
    )
    CenterScreenProgressBar()
}


@Preview(showBackground = true)
@Composable
private fun ProductPreview() {
    ProductsContent(
        state = State(
            selectedCategory = "Fruits & Vegetables",
            categories = Category.getSampleData()
        ),
        listener = null
    )
}