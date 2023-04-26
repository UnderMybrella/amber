package dev.brella.amber.kotlin.string.parser.types

import dev.brella.amber.kotlin.KotlinAppendable
import dev.brella.amber.kotlin.KotlinStringDsl
import dev.brella.amber.kotlin.string.parser.modifiers.KotlinTypeModifierAppendable

public interface KotlinTypeAppendableAccessor {
    public val type: KotlinTypeAppendable<*>
}

@KotlinStringDsl
public inline fun <SELF : KotlinTypeAppendableAccessor> SELF.type(block: KotlinTypeAppendable<*>.() -> Unit): SELF {
    type.block()
    return this
}

public interface KotlinTypeAppendable<SELF : KotlinTypeAppendable<SELF>> : KotlinAppendable<SELF>,
    KotlinTypeModifierAppendable<SELF>, KotlinFunctionTypeAppendable<SELF>, KotlinParenthesisedTypeAppendable<SELF>,
    KotlinNullableTypeAppendable<SELF>, KotlinTypeReferenceAppendable<SELF>, KotlinIntersectionTypeAppendable<SELF>,
    KotlinTypeAppendableAccessor {
    override val type: KotlinTypeAppendable<*>
        get() = this
}

@KotlinStringDsl
public inline fun <SELF : KotlinTypeAppendable<*>> SELF.parenthesized(block: SELF.() -> Unit): SELF =
    try {
        append('(')
        this.block()
        this
    } finally {
        append(')')
    }

@KotlinStringDsl
public inline fun <SELF : KotlinTypeAppendable<*>> SELF.nullable(block: SELF.() -> Unit): SELF {
    this.block()
    append('?')
    return this
}