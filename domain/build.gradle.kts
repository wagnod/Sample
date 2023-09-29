plugins {
    id("libs.common")
    id("org.jetbrains.kotlin.plugin.serialization") version "1.7.21"
}

android {
    namespace = "com.wagnod.domain"
}

dependencies {
    implementation(project(mapOf("path" to ":core")))
}