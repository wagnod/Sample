package com.wagnod.dashboard.ui.basket

import com.wagnod.core.model.Item
import com.wagnod.core_ui.view_model.BaseViewModel
import com.wagnod.dashboard.ui.basket.BasketContract.*
import com.wagnod.domain.basket.GetBasketUseCase
import com.wagnod.domain.basket.RemoveItemFromBasketUseCase
import com.wagnod.domain.basket.UpdateBasketUseCase
import com.wagnod.domain.execute

class BasketViewModel(
    private val getBasket: GetBasketUseCase,
    private val updateBasket: UpdateBasketUseCase,
    private val removeItemFromBasket: RemoveItemFromBasketUseCase
) : BaseViewModel<Event, State, Effect>() {

    override fun setInitialState() = State()

    override fun handleEvents(event: Event) {
        when (event) {
            is Event.Init -> init()
            is Event.UpdateBasket -> updateBasket(event.item)
            is Event.RemoveFromBasket -> removeBasket(event.item)
        }
    }

    private fun init() = io {
        setState { copy(isLoading = true) }
        runCatching {
            getBasket()
        }.onSuccess {
            setState {
                copy(isLoading = false)
            }
        }
    }

    private suspend fun getBasket() {
        val products = getBasket.execute()
        setState { copy(items = products) }
    }

    private fun updateBasket(item: Item) = io {
        if (item.count > 0) {
            updateBasket.execute(item)
            val stateItems = viewState.value.items.toMutableList()
            val dsItem = stateItems.find { it.id == item.id }
            stateItems[stateItems.indexOf(dsItem)] = item
            setState { copy(items = stateItems) }
        } else {
            removeBasket(item)
        }
    }

    private fun removeBasket(item: Item) = io {
        removeItemFromBasket.execute(item)
        val upd = viewState.value.items.toMutableList()
        upd.removeAll { it.id == item.id }
        setState { copy(items = upd) }
    }
}