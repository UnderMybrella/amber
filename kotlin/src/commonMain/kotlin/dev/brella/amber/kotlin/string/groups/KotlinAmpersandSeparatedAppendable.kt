package dev.brella.amber.kotlin.string.groups

import dev.brella.amber.kotlin.KotlinAppendable
import dev.brella.amber.kotlin.KotlinStringDsl

public interface KotlinAmpersandSeparatedAppendable<SELF : KotlinAmpersandSeparatedAppendable<SELF>> :
    KotlinAppendable<SELF> {
    public fun appendAmpersand(): SELF = appendAutoSpacedSurround('&')
}

@KotlinStringDsl
public inline infix fun <SELF : KotlinAmpersandSeparatedAppendable<*>> SELF.and(block: SELF.() -> Unit): SELF {
    appendAmpersand()
    this.block()
    return this
}