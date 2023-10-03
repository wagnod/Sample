package com.wagnod.dashboard.ui.basket

import com.wagnod.core.model.Item
import com.wagnod.core_ui.view_model.ViewEvent
import com.wagnod.core_ui.view_model.ViewSideEffect
import com.wagnod.core_ui.view_model.ViewState

interface BasketContract {

    sealed interface Event : ViewEvent {
        object Init : Event
        data class UpdateBasket(val item: Item) : Event
        data class RemoveFromBasket(val item: Item) : Event
    }

    data class State(
        val isLoading: Boolean = false,
        val items: List<Item> = listOf()
    ) : ViewState

    sealed interface Effect : ViewSideEffect {}

    interface Listener {
        fun onBackClick()
        fun onIncreaseCounterClick(item: Item)
        fun onDecreaseCounterClick(item: Item)
    }
}