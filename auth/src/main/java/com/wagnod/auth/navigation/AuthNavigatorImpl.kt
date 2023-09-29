package com.wagnod.auth.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.wagnod.auth.splash.AuthScreen.SplashScreen
import com.wagnod.core_ui.navigators.AuthNavigator
import com.wagnod.core_ui.navigators.main.Navigator

class AuthNavigatorImpl : AuthNavigator {

    private lateinit var navController: NavController

    override fun setNavController(curNavController: NavController) {
        navController = curNavController
    }

    override fun graph(builder: NavGraphBuilder, navigator: Navigator) {
        builder.navigation(SplashScreen.route, SplashScreen.rootRoute) {
            SplashScreen.createScreen(builder, navigator)
        }
    }
}