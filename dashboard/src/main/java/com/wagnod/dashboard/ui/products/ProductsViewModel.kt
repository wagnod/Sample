package com.wagnod.dashboard.ui.products

import androidx.compose.ui.unit.dp
import com.wagnod.core.model.Item
import com.wagnod.core_ui.ui.bottom_sheet.BottomSheetParams
import com.wagnod.core_ui.ui.bottom_sheet.ShowBottomSheetHelper
import com.wagnod.core_ui.view_model.BaseViewModel
import com.wagnod.dashboard.ui.products.ProductsContract.Effect
import com.wagnod.dashboard.ui.products.ProductsContract.Event
import com.wagnod.dashboard.ui.products.ProductsContract.State
import com.wagnod.domain.basket.AddItemToBasketUseCase
import com.wagnod.domain.basket.GetBasketUseCase
import com.wagnod.domain.basket.RemoveItemFromBasketUseCase
import com.wagnod.domain.execute
import com.wagnod.domain.store.usecase.GetStoreUseCase
import kotlinx.coroutines.launch

class ProductsViewModel(
    private val getStore: GetStoreUseCase,
    private val getBasket: GetBasketUseCase,
    private val addItemToBasket: AddItemToBasketUseCase,
    private val removeItemFromBasket: RemoveItemFromBasketUseCase,
    private val showBottomSheetHelper: ShowBottomSheetHelper
) : BaseViewModel<Event, State, Effect>() {

    override fun setInitialState() = State()

    override fun handleEvents(event: Event) {
        when(event) {
            is Event.Init -> init(event.selectedCategoryName)
            is Event.CategoryChanged -> changeCategory(event.selectedCategory)
            is Event.ShowBottomSheet -> showBottomSheet(event.item)
            is Event.UpdateBasket -> updateBasket(event.item)
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
        val basket = getBasket.execute()
        setState { copy(basket = basket) }
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

    private fun updateBasket(item: Item) = io {
        val fromBasket = viewState.value.basket.find { it.id == item.id } ?: item
        if (fromBasket.count > 0) {
            removeItemFromBasket.execute(fromBasket)
            val upd = viewState.value.basket.toMutableList().also { it.remove(fromBasket) }
            setState { copy(basket = upd) }
        } else {
            addItemToBasket.execute(fromBasket.copy(count = 1))
            val upd = viewState.value.basket.toMutableList().also { it.add(fromBasket.copy(count = 1)) }
            setState { copy(basket = upd) }
        }
        showBottomSheetHelper.closeBottomSheet(300)
    }

    private fun showBottomSheet(item: Item) {
        val updated = viewState.value.basket.find { it.id == item.id } ?: item
        val bottomSheetParams = BottomSheetParams(
            topPadding = 160.dp,
            content =  { ProductBottomContent(updated) {
                setEvent(Event.UpdateBasket(updated))
            } }
        )
        showBottomSheetHelper.showBottomSheet(bottomSheetParams)
    }
}