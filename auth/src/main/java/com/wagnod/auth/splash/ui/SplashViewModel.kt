package com.wagnod.auth.splash.ui

import com.wagnod.auth.splash.ui.SplashContract.*
import com.wagnod.core_ui.view_model.BaseViewModel
import com.wagnod.domain.app.InitAppDataUseCase
import com.wagnod.domain.execute

class SplashViewModel(
    private val initAppDataUseCase: InitAppDataUseCase
) : BaseViewModel<Event, State, Effect>() {
    override fun setInitialState() = State

    override fun handleEvents(event: Event) {
        when(event) {
            is Event.Init -> { initAppData() }
        }
    }

    private fun initAppData() = io {
        runCatching {
            initAppDataUseCase.execute()
        }
    }

}