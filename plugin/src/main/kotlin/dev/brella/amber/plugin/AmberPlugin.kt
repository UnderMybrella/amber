package dev.brella.amber.plugin

import dev.brella.amber.plugin.settings.KotlinCodeGenSettingsGradle
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.withType

class AmberPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        val hierarchy = target.hierarchy().reversed()

        val selfKotlin = hierarchy.foldExtensions<KotlinCodeGenSettingsGradle>(null) { child, parent -> child convention parent }

        target.tasks.withType<KotlinCodeGenTask> {
            extensions.getOrCreate<KotlinCodeGenSettingsGradle>(
                //This is a workaround for gradle#18213 (https://github.com/gradle/gradle/issues/18213) that causes properties to fail on some extension aware objects
                args = arrayOf(target.objects)
            ) convention selfKotlin
        }
    }
}