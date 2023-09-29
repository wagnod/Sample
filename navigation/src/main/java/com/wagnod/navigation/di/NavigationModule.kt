package com.wagnod.navigation.di

import com.wagnod.auth.di.authModule
import com.wagnod.core_ui.di.coreUIModule
import com.wagnod.core_ui.navigators.main.Navigator
import com.wagnod.data.di.dataModule
import com.wagnod.domain.di.domainModule
import com.wagnod.dashboard.di.homeModule
import com.wagnod.navigation.NavigatorImpl
import org.koin.dsl.module

val navigationModule = module {

    single<Navigator> {
        NavigatorImpl(get(), get())
    }
}

val uiModules = listOf(
    navigationModule,
    authModule,
    homeModule
)

val domainModules = listOf(
    domainModule
)

val dataModules = listOf(
    dataModule
)

val appModules = coreUIModule + uiModules + domainModules + dataModules