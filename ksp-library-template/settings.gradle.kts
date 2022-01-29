
pluginManagement {
    repositories {

        mavenLocal()
        gradlePluginPortal()
        google()
    }
    val kotlinVersion: String by settings
    val kspVersion: String by settings
    plugins {
        id("com.google.devtools.ksp") version kspVersion
        kotlin("jvm") version kotlinVersion
    }

}

rootProject.name = "ksp-library-template"


include(":androidApp")
include(":shared_kmm")
include(":ksp-annotation")

include(":common-core")
include(":ksp-compiler-shared")

