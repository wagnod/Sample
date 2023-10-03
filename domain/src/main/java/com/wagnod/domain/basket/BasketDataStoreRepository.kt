package com.wagnod.domain.basket

import com.wagnod.core.model.Item

interface BasketDataStoreRepository {
    suspend fun getBasket() : List<Item>
    suspend fun addItemToBasket(item: Item)
    suspend fun updateItem(item: Item)
    suspend fun removeItem(item: Item)
}