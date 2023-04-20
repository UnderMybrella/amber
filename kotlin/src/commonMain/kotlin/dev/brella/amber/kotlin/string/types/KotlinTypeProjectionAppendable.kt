package dev.brella.amber.kotlin.string.types

import dev.brella.amber.kotlin.KotlinAppendable
import dev.brella.amber.kotlin.KotlinStringDsl
import dev.brella.amber.kotlin.string.KotlinAnnotationAppendable
import dev.brella.amber.kotlin.string.modifiers.KotlinVarianceModifierAppendable

public interface KotlinTypeProjectionAppendable<SELF : KotlinTypeProjectionAppendable<SELF>> : KotlinAppendable<SELF>,
    KotlinVarianceModifierAppendable<SELF>, KotlinAnnotationAppendable<SELF>, KotlinTypeAppendableAccessor {
    public fun appendTypeArgumentSeparator(): SELF = appendAutoSpaced(',')
    public fun appendStarProjection(): SELF = append("*")
}

@KotlinStringDsl
public inline fun <SELF : KotlinTypeProjectionAppendable<*>, T> SELF.typeArguments(block: SELF.() -> T): T =
    try {
        append('<')
        this.block()
    } finally {
        append('>')
    }

@KotlinStringDsl
public inline operator fun <SELF : KotlinTypeProjectionAppendable<*>> SELF.rangeTo(block: SELF.() -> Unit): SELF {
    appendTypeArgumentSeparator()
    this.block()
    return this
}