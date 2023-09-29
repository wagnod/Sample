plugins {
    id("libs.ui")
}

android {
    namespace = "com.wagnod.auth"
}

dependencies {
    implementation(project(mapOf("path" to ":core")))
    implementation(project(mapOf("path" to ":core-ui")))
    implementation(project(mapOf("path" to ":domain")))
}