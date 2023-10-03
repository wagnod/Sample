package com.wagnod.core.datastore.basket

import com.wagnod.core.model.Item
import kotlinx.serialization.Serializable

@Serializable
data class BasketPreference(
    val products: List<Item> = listOf()
)