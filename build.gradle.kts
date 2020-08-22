plugins {
    id("com.android.application") version "4.2.0-alpha07" apply false
    kotlin("android") version "1.4-M3" apply false
}

allprojects {
    repositories {
        mavenCentral()
        maven(url = "https://dl.bintray.com/kotlin/kotlin-eap/")
        jcenter()
        google()
    }
}

buildscript {
    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.28.3-alpha")
    }
}