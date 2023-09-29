package com.wagnod.navigation

import androidx.lifecycle.LiveData
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.wagnod.auth.splash.AuthScreen
import com.wagnod.core_ui.navigators.AuthNavigator
import com.wagnod.core_ui.navigators.HomeNavigator
import com.wagnod.core_ui.navigators.main.Navigator
import com.wagnod.dashboard.DashboardScreen

class NavigatorImpl(
    override val authNavigator: AuthNavigator,
    override val homeNavigator: HomeNavigator
) : Navigator {

    private lateinit var navController: NavHostController

    private val navigators = listOf(
        authNavigator, homeNavigator
    )

    override fun navigateUp() {
        navController.navigateUp()
    }

    override fun setGraphs(navGraphBuilder: NavGraphBuilder) {
        navigators.forEach {
            it.graph(navGraphBuilder, this@NavigatorImpl)
        }
    }

    override fun navigateToHome() {
        DashboardScreen.CategoriesScreen.navigate(Unit, navController)
    }

    override fun navigateToRoute(route: String) {
        navController.navigate(route) {
            popUpTo(navController.graph.startDestinationId) { inclusive = true }
        }
        navController.graph.setStartDestination(route)
    }

    override fun setNavController(navHostController: NavHostController) {
        navController = navHostController

        navigators.forEach {
            it.setNavController(navController)
        }
    }

    override fun currentScreen() = when (navController.currentDestination?.route) {
        AuthScreen.SplashScreen.route -> AuthScreen.SplashScreen
        else -> AuthScreen.SplashScreen
    }

    override fun <T> setScreenResult(key: String, result: T) {
        navController.previousBackStackEntry?.savedStateHandle?.set(key, result)
    }

    override fun <T> getLiveDataResult(key: String): LiveData<T>? {
        return navController.currentBackStackEntry?.savedStateHandle?.getLiveData(key)
    }
}