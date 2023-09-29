package com.wagnod.auth.splash.ui

import com.wagnod.core_ui.view_model.ViewEvent
import com.wagnod.core_ui.view_model.ViewSideEffect
import com.wagnod.core_ui.view_model.ViewState

interface SplashContract {

    sealed interface Event : ViewEvent {
        object Init : Event
    }

    object State : ViewState

    sealed interface Effect : ViewSideEffect

    interface Listener {
        fun navigateNext()
    }
}