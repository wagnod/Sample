package com.wagnod.core.model

import kotlinx.serialization.Serializable

@Serializable
data class Category(
    override val name: String = "",
    val image: String = "",
    val items: List<Item> = listOf()
): Product {
    companion object {
        fun getSampleData() = listOf(
            Category(
                name = "Fruits & Vegetables",
                items = listOf(
                    Item(
                        name = "Potato",
                        description = "1kg",
                    ),
                    Item(
                        name = "Pink tomato",
                        description = "1kg",
                    ),
                    Item(
                        name = "Banana",
                        description = "1kg",
                    ),
                    Item(
                        name = "Ginger",
                        description = "250g",
                    )
                ),
            ),
            Category(
                name = "Beverages",
                items = listOf(
                    Item(
                        name = "Borjomi mineral water",
                        description = "500ml",
                    ),
                    Item(
                        name = "Erdinger white beer jar",
                        description = "500ml",
                    ),
                    Item(
                        name = "Kindzmarauli wine",
                        description = "0.75l",
                    ),
                    Item(
                        name = "Coca cola",
                        description = "330ml",
                    ),
                )
            ),
            Category(
                name = "Frozen",
                items = listOf(
                    Item(
                        name = "Salmon slices",
                        description = "270g",
                    ),
                    Item(
                        name = "Bauer Mexican mix of vegitables",
                        description = "400g",
                    ),
                )
            ),
            Category(
                name = "Bakery",
                items = listOf(
                    Item(
                        name = "White bread",
                        description = "380g",
                    ),
                    Item(
                        name = "Bun with raisins",
                        description = "150g",
                    ),
                )
            ),
            Category(
                name = "Pantry",
                items = listOf(
                    Item(
                        name = "Barila Pasta N13",
                        description = "450g",
                    ),
                    Item(
                        name = "Flour",
                        description = "1kg",
                    ),
                    Item(
                        name = "Sugar",
                        description = "900g",
                    ),
                )
            ),
            Category(
                name = "Dairy & Eggs",
                items = listOf(
                    Item(
                        name = "Parmalat milk 1.8%",
                        description = "1l",
                    ),
                    Item(
                        name = "Eggs",
                        description = "10pcs",
                    ),
                    Item(
                        name = "Cottage cheese",
                        description = "180g",
                    ),
                )
            )
        )
    }
}
