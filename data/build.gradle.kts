plugins {
    id("libs.common")
}

android {
    namespace = "com.wagnod.data"
}

dependencies {
    implementation(project(mapOf("path" to ":core")))
    implementation(project(mapOf("path" to ":domain")))
}