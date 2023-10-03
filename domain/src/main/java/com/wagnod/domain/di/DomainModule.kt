package com.wagnod.domain.di

import com.wagnod.domain.app.InitAppDataUseCase
import com.wagnod.domain.basket.AddItemToBasketUseCase
import com.wagnod.domain.basket.GetBasketUseCase
import com.wagnod.domain.basket.RemoveItemFromBasketUseCase
import com.wagnod.domain.basket.UpdateBasketUseCase
import com.wagnod.domain.store.usecase.GetStoreUseCase
import com.wagnod.domain.store.usecase.SubscribeStoreUseCase
import org.koin.dsl.module

val domainModule = module {

    /**
     * APP
     */
    single { InitAppDataUseCase(get(), get()) }


    /**
     * STORE
     */
    factory { SubscribeStoreUseCase(get()) }
    factory { GetStoreUseCase(get()) }

    /**
     * BASKET DATA STORE
     */
    factory { GetBasketUseCase(get()) }
    factory { UpdateBasketUseCase(get()) }
    factory { AddItemToBasketUseCase(get()) }
    factory { RemoveItemFromBasketUseCase(get()) }
}