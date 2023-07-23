pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }

    plugins {
        val kotlinVersion = extra["kotlin.version"] as String
        val agpVersion = extra["agp.version"] as String
        val composeVersion = extra["compose.version"] as String

        kotlin("jvm").version(kotlinVersion)
        kotlin("multiplatform").version(kotlinVersion)
        kotlin("android").version(kotlinVersion)
        id("com.android.base").version(agpVersion)
        id("com.android.application").version(agpVersion)
        id("com.android.library").version(agpVersion)
        id("org.jetbrains.compose").version(composeVersion)
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Memomate"
include(":androidApp")
include(":shared")
include(":desktopApp")
include(":data")
include(":data:model")
include(":data:local")
include(":domain")
include(":data:remote")
include(":features")
include(":features:people")
include(":core")
include(":common")
include(":libraries")
include(":common:resources")
include(":common:utils")
include(":core:pin")
include(":features:note")
include(":features:notes")
include(":features:notes")
include(":extension")
include(":image_loader")
include(":features:memomate")
