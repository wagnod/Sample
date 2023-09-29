package com.wagnod.buildsrc.dependency

import com.wagnod.buildsrc.dependency.DataStore.Version.DATASTORE_VER

object DataStore {

    const val DATASTORE = "androidx.datastore:datastore:$DATASTORE_VER"

    private object Version {
        const val DATASTORE_VER = "1.0.0"
    }
}