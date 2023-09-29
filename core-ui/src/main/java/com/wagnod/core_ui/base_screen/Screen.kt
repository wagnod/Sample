package com.wagnod.core_ui.base_screen

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.wagnod.core_ui.navigators.main.Navigator

abstract class Screen<T> {
    abstract val route: String
    abstract val rootRoute: String?

    abstract fun navigate(args: T, navController: NavController)
    abstract fun createScreen(navGraphBuilder: NavGraphBuilder, navigator: Navigator)

    protected fun NavController.navigateAndMakeStart(route: String) {
        navigate(route) {
            popUpTo(graph.startDestinationId) { inclusive = true }
        }
        graph.setStartDestination(route)
    }

    protected fun NavController.navigateOrPopup(
        route: String,
        action: () -> Unit = { navigate(route) }
    ) {
        currentBackStack.value.find { it.destination.route == route }
            ?.let { popBackStack(route, inclusive = false, saveState = true) }
            ?: run { action() }
    }
}