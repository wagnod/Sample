package com.wagnod.core_ui.ui.snack_bar

import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarDuration
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class ShowSnackBarHelper{

    private lateinit var scope: CoroutineScope
    private lateinit var scaffoldState: ScaffoldState

    private val _text: Channel<SnackBarParams?> = Channel()
    val text = _text.receiveAsFlow()

    fun init(coroutineScope: CoroutineScope, state: ScaffoldState) {
        scope = coroutineScope
        scaffoldState = state
    }

    fun showSnackBar(text: String) = scope.launch {
        scaffoldState.snackbarHostState.showSnackbar(
            message = text,
            duration = SnackbarDuration.Short
        )
    }

    fun showErrorSnackBar(params: SnackBarParams) = scope.launch {
        show(params)
        delay(4000)
        hide(params)
        delay(250)
        params.endCallback()
    }

    fun showSuccessSnackBar(params: SnackBarParams) {
        val updatedParams = params.copy(error = false)
        showErrorSnackBar(updatedParams)
    }

    private suspend fun show(params: SnackBarParams) {
        _text.send(params)
    }

    private suspend fun hide(params: SnackBarParams) {
        _text.send(null)
    }
}