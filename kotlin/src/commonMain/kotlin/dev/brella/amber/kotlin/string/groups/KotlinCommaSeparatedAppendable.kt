package dev.brella.amber.kotlin.string.groups

import dev.brella.amber.kotlin.KotlinAppendable
import dev.brella.amber.kotlin.KotlinStringDsl

public interface KotlinCommaSeparatedAppendable<SELF : KotlinCommaSeparatedAppendable<SELF>> : KotlinAppendable<SELF> {
    public fun appendCommaSeparator(): SELF = appendAutoSpaced(',')
}

@KotlinStringDsl
public inline infix operator fun <SELF : KotlinCommaSeparatedAppendable<*>> SELF.rangeTo(block: SELF.() -> Unit): SELF {
    appendCommaSeparator()
    this.block()
    return this
}

@KotlinStringDsl
public inline infix fun <SELF : KotlinCommaSeparatedAppendable<*>> SELF.and(block: SELF.() -> Unit): SELF {
    appendCommaSeparator()
    this.block()
    return this
}