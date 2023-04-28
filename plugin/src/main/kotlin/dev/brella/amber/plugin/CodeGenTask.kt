package dev.brella.amber.plugin

import dev.brella.amber.common.CodeGenSettings
import org.gradle.api.DefaultTask
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.Property
import org.gradle.api.provider.SetProperty
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import org.gradle.kotlin.dsl.create
import java.io.File
import javax.inject.Inject

abstract class CodeGenTask<in T : CodeGenSettings, V> @Inject constructor(
    private val settingsClass: Class<T>,
    private val dataClass: Class<V>,
) : DefaultTask() {
    @get:Inject
    protected abstract val objectFactory: ObjectFactory

    @get:Input
    abstract val version: Property<Long>

    @Suppress("unused")
    @get:OutputDirectory
    abstract val outputDirectory: DirectoryProperty

    @Suppress("LeakingThis")
    @get:Input
    val dataset: SetProperty<V> = objectFactory.setProperty(dataClass)

    abstract fun outputFile(data: V): File
    abstract fun generate(output: Appendable, settings: T, data: V)

    @TaskAction
    open fun build() {
        dataset.get().forEach { data ->
            outputFile(data)
                .printWriter()
                .use { writer -> generate(writer, extensions.getByType(settingsClass), data) }
        }
    }

    init {
        group = "codegen"
    }
}