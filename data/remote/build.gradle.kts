plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("kotlinx-serialization")
    id("com.google.devtools.ksp") version "1.8.0-1.0.9"
    id("de.jensklingenberg.ktorfit") version "1.0.0"
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
            baseName = "remote"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":data:model"))
                implementation(libs.kotlinx.serialization.core)
                implementation(libs.ktorfit)
                implementation(libs.kotlinx.coroutines.core)
//                implementation(libs.ktor.client.core)
                implementation(libs.ktor.client.serialization)
                implementation(libs.ktor.client.content.negotiation)
                implementation(libs.ktor.serialization.kotlinx.json)
                implementation(libs.ktor.client.logging)
//                implementation(libs.ktor.client.cio)
                implementation(libs.kotlinx.datetime)
                implementation(libs.napier)
                implementation(libs.multiplatformSettings.core)
                implementation(libs.multiplatformSettings.noarg)
                implementation(libs.multiplatformSettings.coroutines)
                implementation(libs.koin.core)
                implementation(libs.stately.common)
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
                implementation(libs.ktor.client.ios)
            }
        }

//        val jvmMain by getting {
//            dependencies {
//            }
//        }

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

    namespace = "com.twoup.personalfinance.remote"
    compileSdk = androidCompileSdk.toInt()
    defaultConfig {
        minSdk = androidMinSdk.toInt()
        targetSdk = androidTargetSdk.toInt()
    }
}


val ktorfitVersion = "1.0.0"
dependencies {
    add("kspCommonMainMetadata", "de.jensklingenberg.ktorfit:ktorfit-ksp:$ktorfitVersion")
    add("kspDesktop", "de.jensklingenberg.ktorfit:ktorfit-ksp:$ktorfitVersion")
    add("kspAndroid", "de.jensklingenberg.ktorfit:ktorfit-ksp:$ktorfitVersion")
    add("kspIosX64", "de.jensklingenberg.ktorfit:ktorfit-ksp:$ktorfitVersion")
//    add("kspJs", "de.jensklingenberg.ktorfit:ktorfit-ksp:$ktorfitVersion")
    add("kspIosSimulatorArm64", "de.jensklingenberg.ktorfit:ktorfit-ksp:$ktorfitVersion")
}
