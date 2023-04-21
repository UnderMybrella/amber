package dev.brella.amber.plugin

import dev.brella.amber.kotlin.KotlinCodeGenSettings
import dev.brella.amber.kotlin.KotlinStringWriter
import dev.brella.amber.kotlin.import
import dev.brella.amber.kotlin.`package`
import org.gradle.api.Project
import org.gradle.api.file.SourceDirectorySet
import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.TaskProvider
import org.gradle.kotlin.dsl.register
import java.io.File

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

    public fun generate(version: Long? = null, generator: KotlinStringWriter.() -> Unit): KotlinCodeGenTask {
        this.version.set(version ?: generator.hashCode().toLong())
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

inline fun CodeGenerationContainer.registerKotlinCodeGenTask(
    name: String,
    crossinline configure: KotlinCodeGenTask.() -> Unit,
): TaskProvider<KotlinCodeGenTask> =
    register<KotlinCodeGenTask>(name) {
        outputDirectory.convention(this@registerKotlinCodeGenTask.output)

        configure()
    }
