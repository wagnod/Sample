package com.wagnod.data.firebase

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.wagnod.domain.firebase.FirebaseReferencesRepository

class FirebaseReferencesRepositoryImpl(
    private val database: FirebaseDatabase
): FirebaseReferencesRepository {

    override fun getStoreReference(): DatabaseReference {
        return database.getReference(STORE_REFERENCE)
    }

    private companion object {
        const val STORE_REFERENCE = "store"
    }
}