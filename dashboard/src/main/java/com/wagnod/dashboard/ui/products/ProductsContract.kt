package com.wagnod.dashboard.ui.products

import com.wagnod.core.model.Category
import com.wagnod.core.model.Item
import com.wagnod.core_ui.view_model.ViewEvent
import com.wagnod.core_ui.view_model.ViewSideEffect
import com.wagnod.core_ui.view_model.ViewState

interface ProductsContract {

    sealed interface Event : ViewEvent {
        data class UpdateBasket(val item: Item) : Event
        data class Init(val selectedCategoryName: String) : Event
        data class CategoryChanged(val selectedCategory: String) : Event
        data class ShowBottomSheet(val item: Item) : Event
    }

    data class State(
        val isLoading: Boolean = false,
        val selectedCategory: String = "",
        val categories: List<Category> = listOf(),
        val basket: List<Item> = listOf()
    ) : ViewState

    sealed interface Effect : ViewSideEffect

    interface Listener {
        fun onCategoryChange(category: String)
        fun onBackClick()
        fun onBasketClick()
        fun onProductClick(item: Item)
    }
}