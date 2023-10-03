package com.wagnod.core

import timber.log.Timber

fun logMessage(message: String) {
    Timber.tag("Zhopa").d(message = message)
}