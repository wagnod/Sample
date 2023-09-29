package com.wagnod.auth.splash

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.wagnod.auth.navigation.AuthScreenType
import com.wagnod.auth.splash.ui.SplashMainScreen
import com.wagnod.core_ui.base_screen.Screen
import com.wagnod.core_ui.navigators.main.Navigator

sealed class AuthScreen<T>(
    override val route: String,
    override val rootRoute: String = Companion.rootRoute,
): Screen<T>() {

    object SplashScreen: AuthScreen<Unit>(AuthScreenType.SplashScreen.route, rootRoute) {
        override fun navigate(args: Unit, navController: NavController) {
            navController.navigate(route)
        }
        override fun createScreen(navGraphBuilder: NavGraphBuilder, navigator: Navigator) {
            navGraphBuilder.composable(route) {
                SplashMainScreen(navigator)
            }
        }
    }

    companion object {
        const val rootRoute = "auth"
    }
}