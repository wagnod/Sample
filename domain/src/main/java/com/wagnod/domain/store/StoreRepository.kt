package com.wagnod.domain.store

import com.wagnod.core.model.Category
import com.wagnod.domain.app.ReferencedListener
import kotlinx.coroutines.flow.StateFlow

interface StoreRepository {
    suspend fun subscribeStore(): ReferencedListener
    suspend fun getStore(): StateFlow<List<Category>>
}