package com.wagnod.core.extensions

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

inline fun <reified T> createEventListener(
    crossinline callback: suspend (value: T) -> Unit
) = object : ValueEventListener {

    override fun onCancelled(p0: DatabaseError) {}

    override fun onDataChange(snapshot: DataSnapshot) {
        snapshot.getValue<T>().let { value ->
            if (value != null) {
                CoroutineScope(Dispatchers.IO).launch {
                    callback.invoke(value)
                }
            } else {
                Timber.tag("Zhopa").d("createEventListener(), value == null")
            }
        }
    }
}