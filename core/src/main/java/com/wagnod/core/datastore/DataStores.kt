package com.wagnod.core.datastore

import android.content.Context
import androidx.datastore.dataStore
import com.wagnod.core.datastore.basket.BasketPreferenceSerializer

enum class DataStoreTypes {
    BASKET
}

class BasketDataStore(fileName: String) {

    private val Context.basketDataStore by dataStore(
        fileName = fileName,
        serializer = BasketPreferenceSerializer
    )

    fun create(context: Context) = context.basketDataStore
}