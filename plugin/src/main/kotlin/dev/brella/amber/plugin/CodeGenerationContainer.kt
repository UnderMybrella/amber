package dev.brella.amber.plugin

import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.file.Directory
import org.gradle.api.file.SourceDirectorySet
import org.gradle.api.provider.Provider
import org.gradle.api.tasks.TaskContainer
import org.gradle.api.tasks.TaskProvider

class CodeGenerationContainer(
    val tasks: TaskContainer,
    val output: Provider<Directory>,
    val source: SourceDirectorySet,
) {
    public val registeredTasks: MutableSet<TaskProvider<*>> = HashSet()

    public fun <T : Task> setup(task: TaskProvider<T>): TaskProvider<T> {
        registeredTasks.add(task)
        source.srcDir(task)
        return task
    }

    inline fun <reified T : Task> register(name: String, noinline configuration: T.() -> Unit): TaskProvider<T> =
        setup(tasks.register(name, T::class.java, configuration))

    inline fun <reified T : Task> register(
        name: String,
        vararg args: Any?,
        noinline configuration: T.() -> Unit,
    ): TaskProvider<T> =
        setup(tasks.register(name, T::class.java, *args).apply { configure(configuration) })

    fun configureTasks(configure: Task.() -> Unit) =
        registeredTasks.forEach { task -> task.configure(configure) }
}

public inline fun Project.codeGenerationContainer(
    name: String,
    output: Provider<Directory> = layout.buildDirectory.dir("generated/$name"),
    configure: CodeGenerationContainer.() -> Unit,
): CodeGenerationContainer =
    CodeGenerationContainer(tasks, output, objects.sourceDirectorySet(name, name))
        .apply(configure)