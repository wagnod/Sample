package com.wagnod.data.basket

import androidx.datastore.core.DataStore
import com.wagnod.core.datastore.basket.BasketPreference
import com.wagnod.core.model.Item
import com.wagnod.domain.basket.BasketDataStoreRepository
import kotlinx.coroutines.flow.first

class BasketDataStoreRepositoryImpl(
    private val dataStore: DataStore<BasketPreference>
) : BasketDataStoreRepository {

    override suspend fun getBasket() = dataStore.data.first().products

    override suspend fun addItemToBasket(item: Item) {
        val items = dataStore.data.first().products.toMutableList()
        items.add(item)
        dataStore.updateData { BasketPreference(items.toList()) }
    }

    override suspend fun updateItem(item: Item) {
        val items = dataStore.data.first().products.toMutableList()
        val dsItem = items.find { it.id == item.id }
        if (dsItem != null) {
            items[items.indexOf(dsItem)] = item
        } else {
            items.add(item)
        }
        dataStore.updateData { BasketPreference(items) }
    }

    override suspend fun removeItem(item: Item) {
        val items = dataStore.data.first().products.toMutableList()
        items.removeAll { it.id == item.id }
        dataStore.updateData { BasketPreference(items) }
    }
}