package com.wagnod.dashboard

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.wagnod.core_ui.Keys.ARG_SELECTED_CATEGORY
import com.wagnod.core_ui.base_screen.Screen
import com.wagnod.core_ui.navigators.main.Navigator
import com.wagnod.dashboard.navigation.DashboardScreenType
import com.wagnod.dashboard.ui.categories.CategoriesMainScreen
import com.wagnod.dashboard.ui.products.ProductsMainScreen

sealed class DashboardScreen<T>(
    override val route: String,
    override val rootRoute: String = Companion.rootRoute
) : Screen<T>() {

    object CategoriesScreen : DashboardScreen<Unit>(DashboardScreenType.CategoriesScreen.route, rootRoute) {

        override fun navigate(args: Unit, navController: NavController) {
            navController.navigateAndMakeStart(route)
        }

        override fun createScreen(navGraphBuilder: NavGraphBuilder, navigator: Navigator) {
            navGraphBuilder.composable(route) {
                CategoriesMainScreen(navigator)
            }
        }
    }

    object ProductsScreen : DashboardScreen<String>(DashboardScreenType.ProductsScreen.route, rootRoute) {

        override fun navigate(args: String, navController: NavController) {
            navController.navigate(
                route = route.replace("{${ARG_SELECTED_CATEGORY}}", args)
            )
        }
        override fun createScreen(navGraphBuilder: NavGraphBuilder, navigator: Navigator) {
            navGraphBuilder.composable(
                route = route,
                arguments = listOf(
                    navArgument(ARG_SELECTED_CATEGORY) { type = NavType.StringType }
                )
            ) {
                val selected = it.arguments?.getString(ARG_SELECTED_CATEGORY) ?: ""
                ProductsMainScreen(selected, navigator)
            }
        }
    }

    companion object {
        const val rootRoute = "dashboard"
    }
}