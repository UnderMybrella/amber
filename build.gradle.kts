import dev.brella.kornea.gradle.defineVersions
import dev.brella.kornea.gradle.mavenBrella

buildscript {
    repositories {
        maven(url = "https://maven.brella.dev")
        mavenLocal()
    }
}

plugins {
    kotlin("multiplatform") version "1.8.10" apply false
    kotlin("jvm") version "1.8.10" apply false
    kotlin("plugin.serialization") version "1.8.10" apply false
    id("org.jetbrains.dokka") version "1.8.10" apply false
    id("com.gradle.plugin-publish") version "1.2.0" apply false

    id("dev.brella.kornea") version "2.1.0"
    id("dev.brella.amber") version "0.2.0"
    id("com.diffplug.spotless") version "6.18.0" apply false
}

allprojects {
    group = "dev.brella"

    repositories {
        mavenCentral()
        mavenBrella()
    }
}

configure(subprojects) {
    apply(plugin = "maven-publish")

    group = "dev.brella"

    configure<PublishingExtension> {
        repositories {
            maven(url = "${rootProject.buildDir}/repo")
        }
    }
}

defineVersions {
    ktor("2.2.4")
    korneaBase("1.3.0-alpha")
    korneaErrors("4.0.1-alpha")

    "kotest" .. "5.5.5"
}