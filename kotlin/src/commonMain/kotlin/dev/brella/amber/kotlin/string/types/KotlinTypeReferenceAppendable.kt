package dev.brella.amber.kotlin.string.types

import dev.brella.amber.kotlin.KotlinAppendable
import dev.brella.amber.kotlin.KotlinStringDsl

public interface KotlinTypeReferenceAppendable<SELF : KotlinTypeReferenceAppendable<SELF>> : KotlinAppendable<SELF>,
    KotlinUserTypeAppendable<SELF> {
    public fun appendDynamic(): SELF = append("dynamic")
}

@KotlinStringDsl
public inline fun <SELF : KotlinTypeReferenceAppendable<*>> SELF.nullable(block: SELF.() -> Unit): SELF {
    this.block()
    append('?')
    return this
}