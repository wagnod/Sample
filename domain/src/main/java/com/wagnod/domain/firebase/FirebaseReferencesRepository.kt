package com.wagnod.domain.firebase

import com.google.firebase.database.DatabaseReference

interface FirebaseReferencesRepository {
    fun getStoreReference(): DatabaseReference
}