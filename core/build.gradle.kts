import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("libs.common")
    id("org.jetbrains.kotlin.plugin.serialization") version "1.7.21"
}

val properties = gradleLocalProperties(rootDir)


val basketName: String = properties.getProperty("BASKET_STORE_NAME")

android {
    namespace = "com.wagnod.core"

    buildTypes {
        debug {
            buildConfigField("String", "BASKET_DATA_STORE", basketName)
        }
        release {
            buildConfigField("String", "BASKET_DATA_STORE", basketName)
        }
    }
}