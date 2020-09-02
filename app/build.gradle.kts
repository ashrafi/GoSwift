plugins {
    id("com.android.application")
    id ("dagger.hilt.android.plugin")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

val composeVersion = "1.0.0-alpha01"
val coroutinesVersion = "1.3.9-native-mt"
val roomVersion = "2.2.5"
val archLifecycleVersion = "2.2.0"
val filamentVersion = "1.8.1"
val retrofitVersion = "2.9.0"
val okhttp3Version = "4.8.1"
val conscryptVersion = "2.5.0"
val moshiVersion = "1.10.0"
val workmanVersion = "2.4.0"
val pagingVersion = "2.1.2"
val hiltVersion = "2.28.3-alpha"
val hiltCompiler = "1.0.0-alpha02"
val glideVersion = "4.11.0"
val coilVersion = "0.11.0"

dependencies {

    // Kotlin components
    implementation(kotlin("stdlib"))
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")

    // Material
    implementation("com.google.android.material:material:1.2.0")

    // -- remove all livedata
    //implementation "androidx.ui:ui-livedata:$ui_version"
    //implementation  "androidx.lifecycle:lifecycle-livedata-ktx:$rootProject.archLifecycleVersion"

    // KTX
    implementation("androidx.core:core-ktx:1.3.1")
    implementation("androidx.appcompat:appcompat:1.2.0")

    // ROOM
    implementation("androidx.room:room-runtime:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")
    kapt("androidx.room:room-compiler:$roomVersion")
    annotationProcessor("androidx.room:room-compiler:$roomVersion")

    // Lifecycle Components
    implementation("androidx.lifecycle:lifecycle-extensions:$archLifecycleVersion")
    kapt("androidx.lifecycle:lifecycle-common-java8:$archLifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$archLifecycleVersion")
    // inject the ModelView
    implementation ("androidx.hilt:hilt-lifecycle-viewmodel:$hiltCompiler")//1.0.0-alpha02'
    // When using Kotlin.
    kapt ("androidx.hilt:hilt-compiler:$hiltCompiler")//1.0.0-alpha02'

    // Compose
    implementation("androidx.compose.animation:animation:$composeVersion")
    implementation("androidx.compose.foundation:foundation:$composeVersion")
    implementation("androidx.compose.foundation:foundation-layout:$composeVersion")
    implementation("androidx.compose.material:material:$composeVersion")
    implementation("androidx.compose.material:material-icons-extended:$composeVersion")
    implementation("androidx.compose.runtime:runtime:$composeVersion")
    implementation("androidx.compose.runtime:runtime-livedata:$composeVersion")
    implementation("androidx.compose.ui:ui:$composeVersion")
    implementation("androidx.ui:ui-tooling:$composeVersion")

    // Retrofit
    // retrofit2
    implementation ("com.squareup.retrofit2:retrofit:$retrofitVersion")//2.9.0'
    implementation ("com.squareup.retrofit2:converter-moshi:$retrofitVersion") //2.9.0
    implementation ("com.squareup.okhttp3:okhttp:$okhttp3Version")
    implementation ("com.squareup.okhttp3:logging-interceptor:$okhttp3Version")//4.7.2'
    implementation ("com.squareup.retrofit2:converter-gson:$retrofitVersion")//2.9.0'
    implementation ("org.conscrypt:conscrypt-android:$conscryptVersion")//2.4.0'

    // moshi
    implementation ("com.squareup.moshi:moshi-kotlin:$moshiVersion")//1.9.2'

    // Workmanager
    // optional - GCMNetworkManager support
    implementation ("androidx.work:work-gcm:$workmanVersion")
    // Kotlin + coroutines
    implementation ("androidx.work:work-runtime-ktx:$workmanVersion")

    // Paging lib
    implementation ("androidx.paging:paging-runtime:$pagingVersion")

    // hilt
    implementation ("com.google.dagger:hilt-android:$hiltVersion")//2.28.3-alpha'
    kapt ("com.google.dagger:hilt-android-compiler:$hiltVersion")//2.28.3-alpha'

    // Glide
    implementation ("com.github.bumptech.glide:glide:$glideVersion")//4.11.0'
    kapt ("com.github.bumptech.glide:compiler:$glideVersion")

    // coil
    implementation  ("io.coil-kt:coil:$coilVersion")

    // Testing
    // For instrumentation tests
    androidTestImplementation  ("com.google.dagger:hilt-android-testing:$hiltVersion")
    kaptAndroidTest ("com.google.dagger:hilt-android-compiler:$hiltVersion")

    // For local unit tests
    testImplementation ("com.google.dagger:hilt-android-testing:$hiltVersion")
    kaptTest ("com.google.dagger:hilt-android-compiler:$hiltVersion")

}

android {
    compileSdkVersion(29)
    buildToolsVersion = "29.0.3"

    defaultConfig {
        applicationId = "com.ylabz.goswift"
        minSdkVersion(25)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"

        javaCompileOptions {
            annotationProcessorOptions {
                argument("room.incremental", "true")
            }
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    sourceSets["main"].java.srcDir("src/main/kotlin")

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerVersion = "1.4.0"
        kotlinCompilerExtensionVersion = composeVersion
    }

    kapt {
        correctErrorTypes = true
    }

    packagingOptions {
        exclude("META-INF/atomicfu.kotlin_module")
    }

    aaptOptions {
        noCompress("ktx", "glb")
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
        freeCompilerArgs = listOf("-Xallow-jvm-ir-dependencies", "-Xskip-prerelease-check", "-Xskip-metadata-version-check")
    }
}
