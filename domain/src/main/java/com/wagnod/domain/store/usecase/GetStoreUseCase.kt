package com.wagnod.domain.store.usecase

import com.wagnod.core.model.Category
import com.wagnod.domain.UseCase
import com.wagnod.domain.store.StoreRepository
import kotlinx.coroutines.flow.StateFlow

class GetStoreUseCase(
    private val repository: StoreRepository
): UseCase<Unit, StateFlow<List<Category>>> {

    override suspend fun execute(input: Unit): StateFlow<List<Category>> {
        return repository.getStore()
    }
}