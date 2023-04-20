package dev.brella.amber.kotlin.string.classes

import dev.brella.amber.kotlin.KotlinAppendable
import dev.brella.amber.kotlin.KotlinStringDsl
import dev.brella.amber.kotlin.string.KotlinIdentifierAppendable
import dev.brella.amber.kotlin.string.modifiers.KotlinTypeParameterModifierAppendable
import dev.brella.amber.kotlin.string.types.KotlinFunctionTypeParameterAppendable
import dev.brella.amber.kotlin.string.types.KotlinTypeAppendable

public interface KotlinTypeParameterAppendable<SELF : KotlinTypeParameterAppendable<SELF>> : KotlinAppendable<SELF>,
    KotlinTypeParameterModifierAppendable<SELF>, KotlinIdentifierAppendable<SELF>, KotlinTypeAppendable<SELF> {
    public fun appendTypeParameterSeparator(): SELF = appendAutoSpaced(',')
}

@KotlinStringDsl
public inline fun <SELF : KotlinTypeParameterAppendable<*>> SELF.appendSuperType(block: KotlinTypeAppendable<*>.() -> Unit): SELF {
    append(':')
    this.block()
    return this
}

@KotlinStringDsl
public inline fun <SELF : KotlinTypeParameterAppendable<*>> SELF.typeParameters(block: SELF.() -> Unit): SELF =
    try {
        append('<')
        this.block()
        this
    } finally {
        append('>')
    }

@KotlinStringDsl
public inline operator fun <SELF : KotlinTypeParameterAppendable<*>> SELF.rangeTo(block: SELF.() -> Unit): SELF {
    appendTypeParameterSeparator()
    this.block()
    return this
}