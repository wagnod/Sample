package com.wagnod.core_ui.navigators

import com.wagnod.core_ui.navigators.module.ModuleNavigator

interface HomeNavigator : ModuleNavigator {
    fun toProducts(selectedCategory: String)
}