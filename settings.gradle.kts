pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

rootProject.name = "Sample"

include(":app")
include(":core-ui")
include(":dashboard")
include(":core")
include(":auth")
include(":data")
include(":domain")
include(":navigation")
