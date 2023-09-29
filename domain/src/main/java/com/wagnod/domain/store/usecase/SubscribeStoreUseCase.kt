package com.wagnod.domain.store.usecase

import com.wagnod.domain.UseCase
import com.wagnod.domain.app.ReferencedListener
import com.wagnod.domain.store.StoreRepository

class SubscribeStoreUseCase(
    private val repository: StoreRepository
) : UseCase<Unit, ReferencedListener> {

    override suspend fun execute(input: Unit): ReferencedListener {
        return repository.subscribeStore()
    }
}