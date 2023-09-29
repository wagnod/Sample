package com.wagnod.dashboard.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.wagnod.core_ui.navigators.HomeNavigator
import com.wagnod.core_ui.navigators.main.Navigator
import com.wagnod.dashboard.DashboardScreen.*

class HomeNavigatorImpl : HomeNavigator {

    private lateinit var navController: NavController

    override fun setNavController(curNavController: NavController) {
        navController = curNavController
    }

    override fun toProducts(selectedCategory: String) {
        ProductsScreen.navigate(selectedCategory, navController)
    }

    override fun graph(builder: NavGraphBuilder, navigator: Navigator) {
        builder.navigation(CategoriesScreen.route, CategoriesScreen.rootRoute) {
            CategoriesScreen.createScreen(builder, navigator)
            ProductsScreen.createScreen(builder, navigator)
        }
    }
}