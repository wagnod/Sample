package com.wagnod.core_ui.navigators.module

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.wagnod.core_ui.navigators.main.Navigator

interface ModuleNavigator {
    fun setNavController(curNavController: NavController)
    fun graph(builder: NavGraphBuilder, navigator: Navigator)
}