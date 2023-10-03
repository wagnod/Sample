package com.wagnod.data.di

import com.wagnod.core.BuildConfig
import com.wagnod.core.datastore.BasketDataStore
import com.wagnod.core.datastore.DataStoreTypes.*
import com.wagnod.data.basket.BasketDataStoreRepositoryImpl
import com.wagnod.domain.basket.BasketDataStoreRepository
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataStoreModule = module {
    /**
     * NAMES
     */
    single(named(BASKET)) {
        BuildConfig.BASKET_DATA_STORE
    }

    /**
     * INSTANCES
     */
    single<BasketDataStoreRepository> {
        val fileName = get<String>(named(BASKET))
        val dataStore = BasketDataStore(fileName)
        BasketDataStoreRepositoryImpl(
            dataStore.create(androidContext())
        )
    }
}