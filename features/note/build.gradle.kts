plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("kotlinx-serialization")
    id("org.jetbrains.compose")
    id("dev.icerock.mobile.multiplatform-resources")
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
            baseName = "note"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":domain"))
                implementation(project(":data:model"))
                implementation(project(":data:local"))

                api(libs.uuid)

                api(libs.kotlinx.coroutines.core)
                api(libs.kotlinx.datetime)
                api(libs.multiplatformSettings.core)

                implementation(libs.stately.common)
                implementation(libs.koin.core)

                implementation(compose.ui)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.runtime)

                implementation(libs.hyperdrive.multiplatformx.api)
                implementation(libs.datetime)

                implementation(libs.voyager.core)
                implementation(libs.voyager.koin)
                implementation(libs.voyager.tabNavigator)
                implementation(libs.voyager.navigator)
                implementation(libs.voyager.bottomSheetNavigator)
                implementation(libs.voyager.bottomSheetNavigator)
                implementation(libs.voyager.transitions)

                implementation(libs.moko.compose.resource)
//                api(libs.moko.compose.resource)
                api(project(":common:resources"))
                implementation(libs.napier)


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

    namespace = "com.aicontent.note"
    compileSdk = androidCompileSdk.toInt()
    defaultConfig {
        minSdk = androidMinSdk.toInt()
        targetSdk = androidTargetSdk.toInt()
    }
}