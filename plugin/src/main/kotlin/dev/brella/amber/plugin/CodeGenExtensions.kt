package dev.brella.amber.plugin

import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.api.plugins.ExtensionContainer
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.findByType
import kotlin.reflect.jvm.jvmName

public inline fun <reified T : Any> ExtensionContainer.getOrCreate(name: String = T::class.run { simpleName ?: qualifiedName ?: jvmName }, vararg args: Any): T =
    findByType<T>() ?: create<T>(name, constructionArguments = args)

public fun Project.hierarchy(): List<Project> {
    val parents: MutableList<Project> = ArrayList()
    parents.add(this)

    var parent = parent
    while (parent != null) {
        parents.add(parent)
        parent = parent.parent
    }

    return parents
}

public inline fun <reified T : Any> Iterable<ExtensionAware>.foldExtensions(initial: T?, block: (child: T, parent: T?) -> T): T? =
    fold<ExtensionAware, T?>(null) { acc, ext -> block(ext.extensions.getOrCreate<T>(), acc) }