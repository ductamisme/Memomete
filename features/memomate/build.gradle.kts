plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("kotlinx-serialization")
    id("org.jetbrains.compose")
    id("com.squareup.sqldelight")
    id("dev.icerock.mobile.multiplatform-resources")
}

kotlin {
    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "memomate"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":domain"))
                implementation(project(":data:model"))
                implementation(project(":data:local"))
                implementation(project(":extension"))

                api(libs.uuid)

                api(libs.kotlinx.coroutines.core)
                api(libs.kotlinx.datetime)
                api(libs.multiplatformSettings.core)

                implementation(libs.stately.common)
                implementation(libs.koin.core)

                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.materialIconsExtended)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)

                implementation("com.squareup.sqldelight:runtime:1.5.5")
                implementation("com.squareup.sqldelight:coroutines-extensions:1.5.5")
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")

                implementation(compose.ui)
                implementation(compose.material)

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

                api(libs.load.image)

            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("com.squareup.sqldelight:android-driver:1.5.5")
                implementation("androidx.appcompat:appcompat:1.6.1")
                implementation("androidx.activity:activity-compose:1.7.2")
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
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependencies {
                implementation("com.squareup.sqldelight:native-driver:1.5.5")
            }
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    val androidMinSdk: String by project
    val androidCompileSdk: String by project
    val androidTargetSdk: String by project

    namespace = "com.aicontent.memomate"
    compileSdk = androidCompileSdk.toInt()
    defaultConfig {
        minSdk = androidMinSdk.toInt()
        targetSdk = androidTargetSdk.toInt()
    }
}

sqldelight {
    database("ContactDatabase") {
        packageName = "com.plcoding.contactscomposemultiplatform.database"
        sourceFolders = listOf("sqldelight")
    }
}

dependencies {
    implementation("androidx.core:core:1.10.1")
    commonMainApi("dev.icerock.moko:mvvm-core:0.16.1")
    commonMainApi("dev.icerock.moko:mvvm-compose:0.16.1")
    commonMainApi("dev.icerock.moko:mvvm-flow:0.16.1")
    commonMainApi("dev.icerock.moko:mvvm-flow-compose:0.16.1")
}

