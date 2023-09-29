package com.wagnod.dashboard.ui.categories

import com.wagnod.core.model.Category
import com.wagnod.core.model.Product
import com.wagnod.core_ui.view_model.ViewEvent
import com.wagnod.core_ui.view_model.ViewSideEffect
import com.wagnod.core_ui.view_model.ViewState

interface CategoriesContract {

    sealed interface Event : ViewEvent {
        object Init : Event
        data class OnSearchStateChanged(val value: String) : Event
        object OnSearchClosed : Event
        object OnSearchExpand : Event
    }

    data class State(
        val categories: List<Category> = listOf(),
        val isLoading: Boolean = false,
        val searchItems: List<Product> = listOf(),
        val searchValue: String = "",
        val searchState: SearchState = SearchState.SEARCH_CLOSED
    ) : ViewState

    sealed interface Effect : ViewSideEffect {}

    interface Listener {
        fun onCategoryClick(category: String)
        fun onSearchStateChanged(value: String)
        fun onSearchDisplayClosed()
        fun onSearchExpand()
    }

    enum class SearchState {
        SEARCH_EXPANDED,
        SEARCH_CLOSED
    }
}