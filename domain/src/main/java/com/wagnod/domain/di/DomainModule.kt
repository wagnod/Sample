package com.wagnod.domain.di

import com.wagnod.domain.app.InitAppDataUseCase
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
}