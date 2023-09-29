package com.wagnod.data.store

import com.wagnod.core.extensions.createEventListener
import com.wagnod.core.model.Category
import com.wagnod.domain.AppDispatchers
import com.wagnod.domain.app.ReferencedListener
import com.wagnod.domain.firebase.FirebaseReferencesRepository
import com.wagnod.domain.store.StoreRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext

class StoreRepositoryImpl(
    private val references: FirebaseReferencesRepository,
    private val dispatchers: AppDispatchers
): StoreRepository {

    private val storeFlow: MutableStateFlow<List<Category>> = MutableStateFlow(listOf())

    override suspend fun subscribeStore() = withContext(dispatchers.io) {
        val listener = createEventListener<List<Category>> { value ->
            storeFlow.emit(value)
        }
        val reference = references.getStoreReference().also { ref ->
            ref.addValueEventListener(listener)
        }
        ReferencedListener(reference, listener)
    }

    override suspend fun getStore() = storeFlow
}