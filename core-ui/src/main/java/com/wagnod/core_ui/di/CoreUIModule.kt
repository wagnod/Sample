package com.wagnod.core_ui.di

import com.wagnod.core_ui.ui.bottom_sheet.ShowBottomSheetHelper
import com.wagnod.core_ui.ui.snack_bar.ShowSnackBarHelper
import org.koin.dsl.module

val coreUIModule = module {
    single { ShowBottomSheetHelper() }
    single { ShowSnackBarHelper() }
}