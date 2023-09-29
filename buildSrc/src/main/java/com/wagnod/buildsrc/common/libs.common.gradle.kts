import com.wagnod.buildsrc.dependency.BuildConfig
import com.wagnod.buildsrc.extensions.dataStoreDependencies
import com.wagnod.buildsrc.extensions.koinDependencies
import com.wagnod.buildsrc.extensions.firebaseDependencies
import com.wagnod.buildsrc.extensions.unitTestDependencies
import com.wagnod.buildsrc.extensions.networkDependencies
import com.wagnod.buildsrc.extensions.dateTimeDependencies
import com.wagnod.buildsrc.extensions.logDependencies

plugins {
    id("com.android.library")
    id("kotlin-android")
}

android {
    compileSdk = BuildConfig.COMPILE_SDK
    defaultConfig {
        minSdk = BuildConfig.MIN_SDK
        targetSdk = BuildConfig.TARGET_SDK

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlin.RequiresOptIn"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    logDependencies()
    koinDependencies()
    networkDependencies()
    firebaseDependencies()
    unitTestDependencies()
    dataStoreDependencies()
    dateTimeDependencies()
}