package com.wagnod.dashboard.ui.categories

import com.wagnod.core.model.Product
import com.wagnod.core_ui.view_model.BaseViewModel
import com.wagnod.dashboard.ui.categories.CategoriesContract.Effect
import com.wagnod.dashboard.ui.categories.CategoriesContract.Event
import com.wagnod.dashboard.ui.categories.CategoriesContract.State
import com.wagnod.domain.execute
import com.wagnod.domain.store.usecase.GetStoreUseCase
import kotlinx.coroutines.launch

class CategoriesViewModel(
    private val getStore: GetStoreUseCase
) : BaseViewModel<Event, State, Effect>() {

    override fun setInitialState() = State()

    override fun handleEvents(event: Event) {
        when (event) {
            is Event.Init -> init()
            is Event.OnSearchClosed -> onSearchExpanded(CategoriesContract.SearchState.SEARCH_CLOSED)
            is Event.OnSearchStateChanged -> onSearchStateChanged(event.value)
            is Event.OnSearchExpand -> onSearchExpanded(CategoriesContract.SearchState.SEARCH_EXPANDED)
        }
    }

    private fun init() = io {
        setState { copy(isLoading = true) }
        runCatching {
            getStore()
        }.onSuccess {
            setState { copy(isLoading = false) }
        }
    }

    /**
     * STORE
     */
    private suspend fun getStore() = launch {
        getStore.execute().collect { categories ->
            setState { copy(categories = categories) }
        }
    }

    private fun onSearchStateChanged(value: String) {
        val filtered = mutableListOf<Product>()

        viewState.value.categories.forEach { category ->
            val matchingItems = category.items.filter { it.name.contains(value) }
            if (matchingItems.isNotEmpty()) {
                filtered.add(category)
            }
            filtered.addAll(matchingItems)
        }

        setState { copy(searchItems = filtered, searchValue = value) }
    }

    private fun onSearchExpanded(state: CategoriesContract.SearchState) {
        setState { copy(searchState = state, searchValue = "") }
    }
}