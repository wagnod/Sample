package com.wagnod.data.di

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.wagnod.data.firebase.FirebaseReferencesRepositoryImpl
import com.wagnod.data.store.StoreRepositoryImpl
import com.wagnod.domain.AppDispatchers
import com.wagnod.domain.AppDispatchersImpl
import com.wagnod.domain.firebase.FirebaseReferencesRepository
import com.wagnod.domain.store.StoreRepository
import org.koin.dsl.module

val dataModule = module {
    /**
     * DISPATCHERS
     */
    single<AppDispatchers> {
        AppDispatchersImpl()
    }

    /**
     * FIREBASE
     */
    factory {
        Firebase.database
    }
    factory {
        Firebase.storage
    }

    single<FirebaseReferencesRepository> {
        FirebaseReferencesRepositoryImpl(get())
    }

    /**
     * STORE
     */
    single<StoreRepository> { StoreRepositoryImpl(get(), get()) }
}