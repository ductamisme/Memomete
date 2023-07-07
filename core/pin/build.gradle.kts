plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("kotlinx-serialization")
    id("org.jetbrains.compose")
}

kotlin {
    android()

    jvm("desktop")

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "core_pin"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":common:resources"))

                implementation(libs.koin.core)

                implementation(compose.ui)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.runtime)

                implementation(libs.hyperdrive.multiplatformx.api)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            kotlin.srcDirs("src/jvmMain/kotlin")
            dependencies {
//                implementation("androidx.compose.ui:ui-tooling-preview:1.1.1")
//                implementation("androidx.compose.ui:ui-tooling-common:1.1.0")
            }
        }
        val androidUnitTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                implementation(libs.ktor.client.ios)
            }
        }

        val desktopMain by getting {
            kotlin.srcDirs("src/jvmMain/kotlin")
            dependencies {
            }
        }
    }
}

android {
    val androidMinSdk: String by project
    val androidCompileSdk: String by project
    val androidTargetSdk: String by project

    namespace = "com.twoup.personalfinance.pin"
    compileSdk = androidCompileSdk.toInt()
    defaultConfig {
        minSdk = androidMinSdk.toInt()
        targetSdk = androidTargetSdk.toInt()
    }
}
