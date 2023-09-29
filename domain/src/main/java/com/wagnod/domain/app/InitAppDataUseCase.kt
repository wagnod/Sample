package com.wagnod.domain.app

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.wagnod.domain.AppDispatchers
import com.wagnod.domain.UseCase
import com.wagnod.domain.execute
import com.wagnod.domain.store.usecase.SubscribeStoreUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch

typealias ReferencedListener = Pair<DatabaseReference, ValueEventListener>

class InitAppDataUseCase(
    private val subscribeStoreUseCase: SubscribeStoreUseCase,
    private val dispatchers: AppDispatchers
) : UseCase<Unit, Unit> {

    private val referencedListeners = mutableListOf<ReferencedListener>()

    override suspend fun execute(input: Unit) {
        init()
    }

    private fun ReferencedListener.add() = referencedListeners.add(this)

    private suspend fun init() = CoroutineScope(dispatchers.io).launch {
        runCatching {
            listOf(
                async { subscribeStoreUseCase.execute() },
            ).awaitAll()
        }
    }

    fun clearSubscribed() = CoroutineScope(dispatchers.io).launch {
        referencedListeners.map { referenceToListener ->
            async {
                with(referenceToListener) { first.removeEventListener(second) }
            }
        }.awaitAll()
    }
}