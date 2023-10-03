package com.wagnod.core.datastore.basket

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerializationException
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

object BasketPreferenceSerializer: Serializer<BasketPreference> {

    override val defaultValue = BasketPreference()

    override suspend fun readFrom(input: InputStream): BasketPreference {
        try {
            val bytes = input.readBytes()
            val string = bytes.decodeToString()
            return Json.decodeFromString(string)
        } catch (error: SerializationException) {
            throw CorruptionException("Cannot read data from DataStore", error)
        }
    }

    override suspend fun writeTo(t: BasketPreference, output: OutputStream) {
        val string = Json.encodeToString(t)
        val bytes = string.encodeToByteArray()
        withContext(Dispatchers.IO) {
            output.write(bytes)
        }
    }
}
