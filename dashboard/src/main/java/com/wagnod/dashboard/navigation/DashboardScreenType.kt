package com.wagnod.dashboard.navigation

import com.wagnod.core_ui.Keys

enum class DashboardScreenType(val route: String) {
    CategoriesScreen("categories"),
    ProductsScreen("product?selectedCategory={${Keys.ARG_SELECTED_CATEGORY}}")
}