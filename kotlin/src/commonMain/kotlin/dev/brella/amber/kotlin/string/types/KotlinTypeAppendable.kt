package dev.brella.amber.kotlin.string.types

import dev.brella.amber.kotlin.KotlinAppendable
import dev.brella.amber.kotlin.KotlinStringDsl
import dev.brella.amber.kotlin.string.classes.KotlinTypeParameterAppendable
import dev.brella.amber.kotlin.string.modifiers.KotlinTypeModifierAppendable

public interface KotlinTypeAppendableAccessor {
    public val types: KotlinTypeAppendable<*>
}

@KotlinStringDsl
public inline fun <SELF : KotlinTypeAppendableAccessor> SELF.type(block: KotlinTypeAppendable<*>.() -> Unit): SELF {
    types.block()
    return this
}

public interface KotlinTypeAppendable<SELF : KotlinTypeAppendable<SELF>> : KotlinAppendable<SELF>,
    KotlinTypeModifierAppendable<SELF>, KotlinFunctionTypeAppendable<SELF>, KotlinTypeAppendableAccessor {
    override val types: KotlinTypeAppendable<*>
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