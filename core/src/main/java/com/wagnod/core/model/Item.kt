package com.wagnod.core.model

import kotlinx.serialization.Serializable

@Serializable
data class Item(
    override val name: String = "",
    val description: String = "",
) : Product
