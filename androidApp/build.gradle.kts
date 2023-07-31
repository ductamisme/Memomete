plugins {
    kotlin("multiplatform")
    id("com.android.application")
    id("org.jetbrains.compose")
//    id("org.jetbrains.kotlin.android") version ("1.8.0")
}

kotlin {
    android()
    sourceSets {
        val androidMain by getting {
            dependencies {
                implementation(project(":shared"))
//                implementation(project(":share-ui"))
//                implementation(libs.hyperdrive.multiplatformx.api)
                implementation(compose.ui)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.runtime)
//                implementation("androidx.activity:activity-compose:1.6.1")
//                implementation(libs.koin.core)
                implementation(libs.koin.android)
                implementation(libs.koin.androidx.compose)
//                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0")

            }
        }
    }
}

android {
    namespace = "com.aicontent.memomate.android"
    compileSdk = 33
    defaultConfig {
        applicationId = "com.aicontent.memomate"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}
