package com.wagnod.domain.basket

import com.wagnod.core.model.Item
import com.wagnod.domain.UseCase

class GetBasketUseCase(
    private val repository: BasketDataStoreRepository
) : UseCase<Unit, List<Item>> {

    override suspend fun execute(input: Unit): List<Item> {
        return repository.getBasket()
    }
}