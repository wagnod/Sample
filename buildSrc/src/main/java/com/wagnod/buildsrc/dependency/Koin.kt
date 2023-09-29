package com.wagnod.buildsrc.dependency

import com.wagnod.buildsrc.dependency.Koin.Version.KOIN

object Koin {

    const val ANDROID = "io.insert-koin:koin-android:$KOIN"
    const val COMPOSE = "io.insert-koin:koin-androidx-compose:$KOIN"

    private object Version {
        const val KOIN = "3.2.2"
    }
}