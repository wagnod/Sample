package com.wagnod.domain.basket

import com.wagnod.core.model.Item
import com.wagnod.domain.UseCase

class AddItemToBasketUseCase(
    private val repository: BasketDataStoreRepository
) : UseCase<Item, Unit> {

    override suspend fun execute(input: Item) {
        repository.addItemToBasket(input)
    }
}