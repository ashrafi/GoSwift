rootProject.name = "GoSwift"

include(":app")

pluginManagement {
    repositories {
        maven(url = "https://dl.bintray.com/kotlin/kotlin-eap/")
        gradlePluginPortal()
        jcenter()
        google()
    }

    resolutionStrategy {
        eachPlugin {
            if (requested.id.id == "com.android.application") {
                useModule("com.android.tools.build:gradle:${requested.version}")
            }
            if (requested.id.id == "dagger.hilt.android.plugin") {
                //com.google.dagger:hilt-android-gradle-plugin:2.28-alpha
                useModule("com.google.dagger:hilt-android-gradle-plugin:2.28.3-alpha")//${requested.version}")
            }
        }
    }
}
