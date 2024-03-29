package com.wagnod.dashboard.di

import com.wagnod.core_ui.navigators.HomeNavigator
import com.wagnod.dashboard.navigation.HomeNavigatorImpl
import com.wagnod.dashboard.ui.basket.BasketViewModel
import com.wagnod.dashboard.ui.categories.CategoriesViewModel
import com.wagnod.dashboard.ui.products.ProductsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {

    single<HomeNavigator> { HomeNavigatorImpl() }

    /**
     * CATEGORIES
     */
    viewModel {
        CategoriesViewModel(get())
    }

    /**
     * PRODUCTS
     */
    viewModel {
        ProductsViewModel(
            get(),
            get(),
            get(),
            get(),
            get()
        )
    }

    /**
     * BASKET
     */
    viewModel {
        BasketViewModel(
            get(),
            get(),
            get()
        )
    }
}