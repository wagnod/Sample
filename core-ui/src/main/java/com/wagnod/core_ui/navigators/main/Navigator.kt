package com.wagnod.core_ui.navigators.main

import androidx.lifecycle.LiveData
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.wagnod.core_ui.base_screen.Screen
import com.wagnod.core_ui.navigators.AuthNavigator
import com.wagnod.core_ui.navigators.HomeNavigator

interface Navigator {

    val authNavigator: AuthNavigator
    val homeNavigator: HomeNavigator

    fun navigateUp()
    fun setGraphs(navGraphBuilder: NavGraphBuilder)

    fun navigateToHome()

    fun navigateToRoute(route: String)
    fun setNavController(navHostController: NavHostController)

    fun currentScreen(): Screen<*>

    fun <T> setScreenResult(key: String, result: T)
    fun <T> getLiveDataResult(key: String): LiveData<T>?
}