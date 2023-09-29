@file:OptIn(ExperimentalMaterialApi::class)

package com.wagnod.sample.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.wagnod.core_ui.navigators.main.Navigator
import com.wagnod.core_ui.theme.SampleTheme
import com.wagnod.core_ui.ui.bottom_sheet.BottomSheet
import com.wagnod.core_ui.ui.bottom_sheet.ShowBottomSheetHelper
import com.wagnod.core_ui.ui.snack_bar.ShowSnackBarHelper
import com.wagnod.core_ui.ui.snack_bar.TopSnackBar
import com.wagnod.domain.app.InitAppDataUseCase
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val navigator by inject<Navigator>()
    private val initAppDataUseCase by inject<InitAppDataUseCase>()
    private val showSnackBarHelper by inject<ShowSnackBarHelper>()
    private val showBottomSheetHelper by inject<ShowBottomSheetHelper>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            val scope = rememberCoroutineScope()
            val scaffoldState = rememberScaffoldState()
            val bottomSheetState = rememberModalBottomSheetState(
                initialValue = ModalBottomSheetValue.Hidden,
                skipHalfExpanded = true
            )

            showSnackBarHelper.init(scope, scaffoldState)
            showBottomSheetHelper.init(scope, bottomSheetState)

            LaunchedEffect(Unit) {
                snapshotFlow { bottomSheetState.currentValue }
                    .collect {
                        if (it == ModalBottomSheetValue.Hidden) showBottomSheetHelper.closeBottomSheet()
                    }
            }

            val bottomSheetContent: @Composable () -> Unit = {
                showBottomSheetHelper.content.collectAsState(null).value?.let { params ->
                    BottomSheet(params)
                }
                Spacer(modifier = Modifier.height(1.dp))
            }

            val errorShackBarContent: @Composable () -> Unit = {
                showSnackBarHelper.text.collectAsState(null).value.let { params ->
                    AnimatedVisibility(
                        visible = params != null,
                        enter = fadeIn() + slideInVertically(
                            animationSpec = tween(durationMillis = 350, easing = LinearEasing)
                        ),
                        exit = fadeOut() + slideOutVertically(
                            animationSpec = tween(durationMillis = 350, easing = LinearEasing)
                        )
                    ) {
                        params?.let {
                            TopSnackBar(it)
                        }
                    }
                }
            }

            MainActivityScreen(
                navigator = navigator,
                navController = navController,
                scaffoldState = scaffoldState,
                bottomSheetState = bottomSheetState,
                bottomSheetContent = bottomSheetContent,
                errorShackBarContent = errorShackBarContent,
            ) {
                NavHost(
                    navController = navController,
                    startDestination = "splash_screen",
                    builder = {
                        navigator.setGraphs(this)
                    }
                )
            }
        }
    }

    override fun onDestroy() {
        initAppDataUseCase.clearSubscribed()
        super.onDestroy()
    }
}

@Composable
fun MainActivityScreen(
    navigator: Navigator,
    navController: NavHostController,
    scaffoldState: ScaffoldState,
    bottomSheetState: ModalBottomSheetState,
    bottomSheetContent: @Composable () -> Unit,
    errorShackBarContent: @Composable () -> Unit,
    setGraphs: @Composable () -> Unit
) {
    navigator.setNavController(navController)

    SampleTheme {
        ModalBottomSheetLayout(
            sheetState = bottomSheetState,
            sheetShape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp),
            sheetContent = { bottomSheetContent() }
        ) {
            Scaffold(
                scaffoldState = scaffoldState,
                bottomBar = {
                    Spacer(
                        modifier = Modifier
                            .navigationBarsPadding()
                            .imePadding()
                            .fillMaxWidth()
                    )
                }
            ) {
                Box(
                    modifier = Modifier.padding(it)
                ) {
                    setGraphs()
                    errorShackBarContent()
                }
            }
        }
    }
}