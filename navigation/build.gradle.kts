plugins {
    id("libs.ui")
}

android {
    namespace = "com.wagnod.navigation"
}

dependencies {
    implementation(project(mapOf("path" to ":core-ui")))
    implementation(project(mapOf("path" to ":core")))
    implementation(project(mapOf("path" to ":auth")))
    implementation(project(mapOf("path" to ":dashboard")))
    implementation(project(mapOf("path" to ":data")))
    implementation(project(mapOf("path" to ":domain")))
}