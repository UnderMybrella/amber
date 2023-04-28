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
import javax.inject.Inject

abstract class KotlinCodeGenTask<T> @Inject constructor(dataClass: Class<T>) :
    CodeGenTask<KotlinCodeGenSettings, T>(KotlinCodeGenSettings::class.java, dataClass) {
    @get:Input
    abstract val fileNameVersion: Property<Long>

    @get:Internal
    abstract val fileName: Property<(T) -> String>

    @get:Input
    abstract val filePackageVersion: Property<Long>

    @get:Internal
    abstract val filePackage: Property<(T) -> String>

    @get:Internal
    abstract val generator: Property<KotlinStringWriter.(T) -> Unit>

    public fun fileName(name: String): KotlinCodeGenTask<T> {
        this.fileNameVersion.set(name.hashCode().toLong())
        this.fileName.set { name }

        return this
    }

    public fun fileName(version: Long? = null, fileName: (T) -> String): KotlinCodeGenTask<T> {
        this.fileNameVersion.set(version ?: fileNameVersion.hashCode().toLong())
        this.fileName.set(fileName)

        return this
    }

    public fun filePackage(name: String): KotlinCodeGenTask<T> {
        this.filePackageVersion.set(name.hashCode().toLong())
        this.filePackage.set { name }

        return this
    }

    public fun filePackage(version: Long? = null, filePackage: (T) -> String): KotlinCodeGenTask<T> {
        this.filePackageVersion.set(version ?: filePackageVersion.hashCode().toLong())
        this.filePackage.set(filePackage)

        return this
    }

    public fun generate(version: Long? = null, generator: KotlinStringWriter.(T) -> Unit): KotlinCodeGenTask<T> {
        this.version.set(version ?: generator.hashCode().toLong())
        this.generator.set(generator)

        return this
    }

    public fun generate(
        filePackage: String,
        fileName: String,
        version: Long? = null,
        generator: KotlinStringWriter.(T) -> Unit,
    ): KotlinCodeGenTask<T> =
        filePackage(filePackage)
            .fileName(fileName)
            .generate(version, generator)

    override fun outputFile(data: T): File {
        val sourceDir = this.outputDirectory.asFile.get()

        val filePackage = this.filePackage.get()(data)
        val fileName = this.fileName.get()(data)
        val outputDir = File(sourceDir, filePackage.replace('.', '/'))
        outputDir.mkdirs()

        return File(outputDir, "${fileName}.kt")
    }

    override fun generate(output: Appendable, settings: KotlinCodeGenSettings, data: T): Unit =
        with(KotlinStringWriter(output, settings)) {
            `package`(filePackage.get()(data)).appendLine()

            generator.get()(data)
        }

    init {
        outputDirectory.convention(project.layout.buildDirectory.dir("generated/amber"))
    }
}

inline fun <reified T : Any> CodeGenerationContainer.registerKotlinCodeGenTask(
    name: String,
    crossinline configure: KotlinCodeGenTask<T>.() -> Unit,
): TaskProvider<KotlinCodeGenTask<T>> =
    register<KotlinCodeGenTask<T>>(name, T::class.java) {
        outputDirectory.convention(this@registerKotlinCodeGenTask.output)

        configure()
    }
