package com.wagnod.core.model

import kotlinx.serialization.Serializable

@Serializable
data class Item(
    override val name: String = "",
    val description: String = "",
    val id: Int = 0,
    /** count in basket **/
    val count: Int = 0
) : Product {
    companion object {
        fun getSampleData() = listOf(
            Item(
                name = "Potato",
                description = "1kg",
                count = 4
            ),
            Item(
                name = "Pink tomato",
                description = "1kg",
                count = 2
            ),
            Item(
                name = "Banana",
                description = "500g",
                count = 0
            ),
            Item(
                name = "Ginger",
                description = "250g",
                count = 1
            )
        )
    }
}
