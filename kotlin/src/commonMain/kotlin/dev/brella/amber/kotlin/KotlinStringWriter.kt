package dev.brella.amber.kotlin

import dev.brella.amber.common.appendLine
import dev.brella.amber.kotlin.string.parser.classes.KotlinTypeParameterAppendable
import dev.brella.amber.kotlin.string.parser.classes.members.KotlinFunctionDeclarationAppendable
import dev.brella.amber.kotlin.string.parser.modifiers.KotlinModifierAppendable

@KotlinStringDsl
public open class KotlinStringWriter(
    private val base: Appendable,
    public override val settings: KotlinCodeGenSettings,
) : KotlinAppendable<KotlinStringWriter>, KotlinModifierAppendable<KotlinStringWriter>,
    KotlinTypeParameterAppendable<KotlinStringWriter>, KotlinFunctionDeclarationAppendable<KotlinStringWriter> {
    public companion object {
        public const val ABSTRACT: String = "abstract"
        public const val ANNOTATION: String = "annotation"
        public const val BY: String = "by"
        public const val CATCH: String = "catch"
        public const val COMPANION: String = "companion"
        public const val CONSTRUCTOR: String = "constructor"
        public const val CROSSINLINE: String = "crossinline"
        public const val DATA: String = "data"
        public const val DYNAMIC: String = "dynamic"
        public const val ENUM: String = "enum"
        public const val EXTERNAL: String = "external"
    }

    @PublishedApi
    internal var indentLevel: Int = 0

    override fun append(value: Char): KotlinStringWriter {
        base.append(value)
        return this
    }

    override fun append(value: CharSequence?): KotlinStringWriter {
        base.append(value)
        return this
    }

    override fun append(value: CharSequence?, startIndex: Int, endIndex: Int): KotlinStringWriter {
        base.append(value, startIndex, endIndex)
        return this
    }

    public fun appendIndent(level: Int = 0): KotlinStringWriter {
        repeat(indentLevel + level) { append(settings.indent) }
        return this
    }

    @KotlinStringDsl
    public inline fun <T> indented(block: KotlinStringWriter.() -> T): T =
        try {
            indentLevel++
            this.block()
        } finally {
            indentLevel--
        }

    public fun appendPackage(filePackage: String): KotlinStringWriter =
        appendIndent()
            .append("package ")
            .appendLine(filePackage)

    public fun appendImport(import: String): KotlinStringWriter =
        appendIndent()
            .append("import ")
            .appendLine(import)

    @KotlinStringDsl
    public inline fun appendClass(
        name: String,
        modifiers: List<String>? = null,
        typeParameters: List<String>? = null,
        primaryConstructor: String? = null,
        delegationSpecifiers: List<String>? = null,
        typeConstraints: List<String>? = null,
        body: KotlinStringWriter.() -> Unit = {},
    ): KotlinStringWriter =
        appendClass(
            name,
            { modifiers?.forEach { modifier -> append(modifier).append(' ') } },
            { typeParameters?.joinTo(this, prefix = "<", postfix = ">") },
            {
                if (primaryConstructor != null) {
                    append(' ')
                    append(primaryConstructor)
                }
            },
            { delegationSpecifiers?.joinTo(this, prefix = " : ") },
            { typeConstraints?.joinTo(this, prefix = " where ") },
            body
        )

    @KotlinStringDsl
    public inline fun appendClass(
        name: String,
        modifiers: KotlinModifierAppendable<*>.() -> Unit = {},
        typeParameters: KotlinTypeParameterAppendable<*>.() -> Unit = {},
        primaryConstructor: KotlinStringWriter.() -> Unit = {},
        delegationSpecifiers: KotlinStringWriter.() -> Unit = {},
        typeConstraints: KotlinStringWriter.() -> Unit = {},
        body: KotlinStringWriter.() -> Unit = {},
    ): KotlinStringWriter {
        appendIndent()
        this.modifiers()
        append("class ")
        append(name)
        this.typeParameters()
        this.primaryConstructor()
        this.delegationSpecifiers()
        this.typeConstraints()

        appendLine("{")
        indented(body)
        appendLine("}")
        return this
    }
}

public inline infix fun KotlinStringWriter.`package`(filePackage: String): KotlinStringWriter =
    appendPackage(filePackage)

public inline infix fun KotlinStringWriter.import(import: String): KotlinStringWriter = appendImport(import)

@KotlinStringDsl
public inline fun KotlinStringWriter.`class`(
    name: String,
    modifiers: List<String>? = null,
    typeParameters: List<String>? = null,
    primaryConstructor: String? = null,
    delegationSpecifiers: List<String>? = null,
    typeConstraints: List<String>? = null,
    body: KotlinStringWriter.() -> Unit = {},
): KotlinStringWriter =
    appendClass(name, modifiers, typeParameters, primaryConstructor, delegationSpecifiers, typeConstraints, body)