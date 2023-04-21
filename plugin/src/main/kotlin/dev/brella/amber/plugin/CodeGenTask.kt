package dev.brella.amber.plugin

import dev.brella.amber.common.CodeGenSettings
import org.gradle.api.DefaultTask
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import org.gradle.kotlin.dsl.create
import java.io.File

abstract class CodeGenTask<in T: CodeGenSettings>(private val settingsClass: Class<T>) : DefaultTask() {
    @get:Input
    abstract val version: Property<Long>

    @Suppress("unused")
    @get:OutputDirectory
    abstract val outputDirectory: DirectoryProperty

    abstract fun outputFile(): File
    abstract fun generate(output: Appendable, settings: T)

    @TaskAction
    open fun build() {
        outputFile()
            .printWriter()
            .use { writer -> generate(writer, extensions.getByType(settingsClass)) }
    }

    init {
        group = "codegen"
    }
}