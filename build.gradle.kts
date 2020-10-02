plugins {
    id("com.android.application") version "4.2.0-alpha13" apply false
    kotlin("android") version "1.4.10" apply false
}

allprojects {
    repositories {
        mavenCentral()
        jcenter()
        google()
    }
}

buildscript {
    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.28.3-alpha")
    }
}