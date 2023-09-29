package com.wagnod.auth.di

import com.wagnod.auth.navigation.AuthNavigatorImpl
import com.wagnod.auth.splash.ui.SplashViewModel
import com.wagnod.core_ui.navigators.AuthNavigator
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val authModule = module {

    single<AuthNavigator> { AuthNavigatorImpl() }

    viewModel {
        SplashViewModel(get())
    }
}