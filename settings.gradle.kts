import dev.brella.kornea.gradle.settings.includeSubprojects

pluginManagement {
    repositories {
//        maven(url = "./build/repo")
        gradlePluginPortal()
        mavenLocal()
    }
}

plugins {
    id("dev.brella.kornea.settings") version "1.0.1"
}

rootProject.name = "amber"

includeSubprojects(emptyList(), rootDir)
