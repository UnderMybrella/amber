package dev.brella.amber.plugin

import dev.brella.amber.common.CodeGenSettings
import dev.brella.amber.kotlin.*
import org.gradle.api.DefaultTask
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.file.SourceDirectorySet
import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.*
import org.gradle.configurationcache.extensions.capitalized
import org.gradle.kotlin.dsl.*
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSetContainer
import org.jetbrains.kotlin.gradle.tasks.KotlinCompileTool
import java.io.File
import kotlin.reflect.KClass
import kotlin.text.appendLine

abstract class KotlinCodeGenTask : CodeGenTask<KotlinCodeGenSettings>(KotlinCodeGenSettings::class.java) {
    @get:Input
    abstract val fileName: Property<String>

    @get:Input
    abstract val filePackage: Property<String>

    @get:Input
    @get:Optional
    abstract val imports: ListProperty<String>

    @get:Internal
    abstract val generator: Property<KotlinStringWriter.() -> Unit>

    public fun generate(version: Long, generator: KotlinStringWriter.() -> Unit): KotlinCodeGenTask {
        this.version.set(version)
        this.generator.set(generator)

        return this
    }

    override fun outputFile(): File {
        val sourceDir = this.outputDirectory.asFile.get()

        val filePackage = this.filePackage.get()
        val fileName = this.fileName.get()
        val outputDir = File(sourceDir, filePackage.replace('.', '/'))
        outputDir.mkdirs()

        return File(outputDir, "${fileName}.kt")
    }

    override fun generate(output: Appendable, settings: KotlinCodeGenSettings): Unit =
        with(KotlinStringWriter(output, settings)) {
            `package`(filePackage.get()).appendLine()

            imports.orNull?.forEach(this::import)
            appendLine()

            generator.get()()
        }

    init {
        outputDirectory.convention(project.layout.buildDirectory.dir("generated/amber"))
    }
}

fun Project.registerKotlinCodeGenTask(
    name: String,
    sourceSet: SourceDirectorySet,
    configure: KotlinCodeGenTask.() -> Unit,
): TaskProvider<KotlinCodeGenTask> =
    tasks.register(name, configure)
        .also(sourceSet::srcDir)
