import dev.brella.kornea.gradle.mavenBrella
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val enableDokka = project.property("enableDokka").toString().toBoolean()

plugins {
    kotlin("jvm")
    id("org.jetbrains.dokka")
    id("com.gradle.plugin-publish")
    id("dev.brella.kornea")
    `kotlin-dsl`
}

version = "0.2.0"

if (enableDokka) {
    java {
        withSourcesJar()
        withJavadocJar()
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

repositories {
    mavenCentral()
    gradlePluginPortal()
    mavenBrella()
}

dependencies {
    api(project(":amber-common"))
    api(project(":amber-kotlin"))
    compileOnly("org.jetbrains.kotlin:kotlin-gradle-plugin-api:1.8.10")
//    compileOnly("com.diffplug.spotless:spotless-plugin-gradle:6.18.0")
    if (enableDokka) dokkaHtmlPlugin("org.jetbrains.dokka:javadoc-plugin:1.8.10")
}

tasks.test {
    useJUnitPlatform()
}

gradlePlugin {
    website.set("https://github.com/UnderMybrella/amber")
    vcsUrl.set("https://github.com/UnderMybrella/amber")

    plugins {
        create("amber") {
            id = "dev.brella.amber"
            displayName = "Amber"
            implementationClass = "dev.brella.amber.plugin.AmberPlugin"
            description = "Code Generation plugin for Gradle"
            tags.set(listOf("kotlin", "kornea"))
        }
    }
}

//registerFillReadmeTask("fillReadme") {
//    inputFile.set(File(projectDir, "README_TEMPLATE.md"))
//    outputFile.set(File(projectDir, "README.md"))
//
//    version("%VERSION%")
//}

if (enableDokka) {
    tasks.named<Jar>("javadocJar") {
        from(tasks.named("dokkaJavadoc"))
    }
}