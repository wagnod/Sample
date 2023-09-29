import com.wagnod.buildsrc.extensions.accompanistDependencies
import com.wagnod.buildsrc.extensions.animationDependencies
import com.wagnod.buildsrc.extensions.commonUiDependencies
import com.wagnod.buildsrc.extensions.navigationDependencies
import org.gradle.kotlin.dsl.dependencies
import com.wagnod.buildsrc.dependency.Basic.Version
import com.wagnod.buildsrc.extensions.unitTestDependencies

plugins {
    id("libs.common")
}

android {
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Version.COMPOSE
    }
}

dependencies {
    accompanistDependencies()
    animationDependencies()
    commonUiDependencies()
    navigationDependencies()
}