package dev.brella.amber.kotlin.string.groups

import dev.brella.amber.kotlin.KotlinAppendable
import dev.brella.amber.kotlin.KotlinStringDsl

public interface KotlinNullableAppendable<SELF : KotlinNullableAppendable<SELF>> : KotlinAppendable<SELF> {
    public fun appendNullable(): SELF = append('?')
}

@KotlinStringDsl
public inline fun <SELF : KotlinNullableAppendable<*>> SELF.appendNullable(block: SELF.() -> Unit): SELF {
    this.block()
    appendNullable()
    return this
}