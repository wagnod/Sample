package com.wagnod.dashboard.ui.categories

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.coil.CoilImage
import com.wagnod.core.model.Category
import com.wagnod.core.model.Item
import com.wagnod.core.model.Product
import com.wagnod.core_ui.navigators.main.Navigator
import com.wagnod.core_ui.theme.SampleTheme
import com.wagnod.core_ui.theme.footnoteRegular
import com.wagnod.core_ui.theme.textPrimary
import com.wagnod.core_ui.ui.toolbar.ToolbarTitle
import com.wagnod.core_ui.ui.toolbar.ToolbarTop
import com.wagnod.core_ui.ui.progress.CenterScreenProgressBar
import com.wagnod.core_ui.ui.search_bar.ExpandableSearchView
import com.wagnod.dashboard.R
import com.wagnod.dashboard.ui.categories.CategoriesContract.*
import org.koin.androidx.compose.getViewModel

@Composable
fun CategoriesMainScreen(
    navigator: Navigator,
    viewModel: CategoriesViewModel = getViewModel()
) {
    val listener = object : Listener {
        override fun onCategoryClick(category: String) {
            viewModel.setEvent(Event.OnSearchClosed)
            navigator.homeNavigator.toProducts(category)
        }

        override fun onSearchStateChanged(value: String) {
            viewModel.setEvent(Event.OnSearchStateChanged(value))
        }

        override fun onSearchDisplayClosed() {
            viewModel.setEvent(Event.OnSearchClosed)
        }

        override fun onSearchExpand() {
            viewModel.setEvent(Event.OnSearchExpand)
        }
    }

    LaunchedEffect(Unit) {
        viewModel.setEvent(Event.Init)
    }

    if (viewModel.viewState.value.isLoading) {
        StoreStub()
    } else {
        CategoriesMainScreenContent(
            state = viewModel.viewState.value,
            listener = listener
        )
    }
}

@Composable
private fun CategoriesMainScreenContent(
    state: State,
    listener: Listener?
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Toolbar(state, listener)

        when (state.searchState) {
            SearchState.SEARCH_CLOSED -> {
                StoreCategories(state, listener)
            }

            SearchState.SEARCH_EXPANDED -> {
                StoreSearch(state, listener)
            }
        }

    }
}

@Composable
private fun CategoryView(
    category: Category,
    onClick: () -> Unit
) = Column(
    modifier = Modifier
        .fillMaxWidth()
        .clickable { onClick.invoke() }
        .padding(vertical = 4.dp, horizontal = 16.dp)
        .background(MaterialTheme.colors.background)
) {
    CoilImage(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .clip(RoundedCornerShape(16.dp)),
        imageModel = { category.image },
        previewPlaceholder = R.drawable.product_placeholder
    )

    Text(
        text = category.name,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        color = MaterialTheme.colors.textPrimary,
        style = MaterialTheme.typography.body1
    )
}

@Composable
private fun Toolbar(state: State, listener: Listener?) {
    ExpandableSearchView(
        searchDisplay = state.searchValue,
        text = "Shop",
        onSearchStateChanged = { listener?.onSearchStateChanged(it) },
        onSearchClosed = { listener?.onSearchDisplayClosed() },
        onSearchExpand = { listener?.onSearchExpand() }
    )
}

@Composable
private fun StoreSearch(state: State, listener: Listener?) = LazyVerticalGrid(
    columns = GridCells.Fixed(1),
    modifier = Modifier.padding(16.dp)
) {
    items(state.searchItems) { item ->
        ProductView(item = item, listener)
    }
}

@Composable
private fun StoreCategories(state: State, listener: Listener?) = LazyVerticalGrid(
    columns = GridCells.Fixed(2),
    modifier = Modifier.padding(16.dp)
) {
    items(state.categories) { item ->
        CategoryView(category = item) {
            listener?.onCategoryClick(item.name)
        }
    }
}

@Composable
private fun ProductView(item: Product, listener: Listener?) = Column(
    modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 4.dp, horizontal = 16.dp)
        .background(MaterialTheme.colors.background)
        .clickable { if (item is Category) listener?.onCategoryClick(item.name) }
) {
    Text(
        text = item.name,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        color = MaterialTheme.colors.textPrimary,
        style = MaterialTheme.typography.body1
    )

    if (item is Item) {
        Text(
            text = item.description,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            color = MaterialTheme.colors.textPrimary,
            style = MaterialTheme.typography.footnoteRegular
        )
    }
}

/**
 * LOADING STUB
 */
@Composable
private fun StoreStub() = Column(
    modifier = Modifier.fillMaxSize()
) {
    ToolbarTop(title = { ToolbarTitle(title = "Shop") })
    CenterScreenProgressBar()
}

@Composable
@Preview(showBackground = true)
private fun CategoriesPreview() = SampleTheme {
    CategoriesMainScreenContent(
        state = State(
            categories = Category.getSampleData()
        ),
        listener = null
    )
}

@Composable
@Preview(showBackground = true)
private fun CategoriesStubPreview() = SampleTheme {
    StoreStub()
}