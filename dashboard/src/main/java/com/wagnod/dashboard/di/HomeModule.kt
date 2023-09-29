package com.wagnod.dashboard.di

import com.wagnod.core_ui.navigators.HomeNavigator
import com.wagnod.dashboard.navigation.HomeNavigatorImpl
import com.wagnod.dashboard.ui.categories.CategoriesViewModel
import com.wagnod.dashboard.ui.products.ProductsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {

    single<HomeNavigator> { HomeNavigatorImpl() }

    viewModel {
        CategoriesViewModel(get())
    }

    viewModel {
        ProductsViewModel(get())
    }
}