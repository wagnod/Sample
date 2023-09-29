package com.wagnod.dashboard.ui.products

import com.wagnod.core_ui.view_model.BaseViewModel
import com.wagnod.dashboard.ui.products.ProductsContract.Effect
import com.wagnod.dashboard.ui.products.ProductsContract.Event
import com.wagnod.dashboard.ui.products.ProductsContract.State
import com.wagnod.domain.execute
import com.wagnod.domain.store.usecase.GetStoreUseCase
import kotlinx.coroutines.launch

class ProductsViewModel(
    private val getStore: GetStoreUseCase
) : BaseViewModel<Event, State, Effect>() {

    override fun setInitialState() = State()

    override fun handleEvents(event: Event) {
        when(event) {
            is Event.Init -> init(event.selectedCategoryName)
            is Event.CategoryChanged -> changeCategory(event.selectedCategory)
        }
    }

    private fun init(
        selectedCategoryName: String
    ) = io {
        setState { copy(isLoading = true) }
        runCatching {
            getStore(selectedCategoryName)
        }.onSuccess {
            setState { copy(isLoading = false) }
        }
    }

    /**
     * STORE
     */
    private suspend fun getStore(
        selectedCategoryName: String
    ) = launch {
        getStore.execute().collect { categories ->
            setState { copy(categories = categories) }
            setState { copy(selectedCategory = selectedCategoryName) }
        }
    }

    private fun changeCategory(
        selectedCategory: String
    ) {
        setState { copy(selectedCategory = selectedCategory) }
    }
}