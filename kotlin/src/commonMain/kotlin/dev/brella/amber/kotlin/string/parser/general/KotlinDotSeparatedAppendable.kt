package dev.brella.amber.kotlin.string.parser.general

import dev.brella.amber.kotlin.KotlinAppendable
import dev.brella.amber.kotlin.KotlinStringDsl

public interface KotlinDotSeparatedAppendable<SELF : KotlinDotSeparatedAppendable<SELF>> : KotlinAppendable<SELF> {
    public fun appendDot(): SELF = append('.')
}

@KotlinStringDsl
public inline fun <SELF : KotlinDotSeparatedAppendable<SELF>> SELF.appendAccess(block: SELF.() -> Unit): SELF {
    this.appendDot()
    this.block()
    return this
}

@KotlinStringDsl
public inline fun <SELF : KotlinDotSeparatedAppendable<SELF>> SELF.appendDefine(block: SELF.() -> Unit): SELF {
    this.block()
    this.appendDot()
    return this
}

@KotlinStringDsl
public inline fun <SELF : KotlinDotSeparatedAppendable<SELF>> SELF.dot(): SELF =
    appendDot()

@KotlinStringDsl
public inline fun <SELF : KotlinDotSeparatedAppendable<SELF>> SELF.access(block: SELF.() -> Unit): SELF =
    appendAccess(block)

@KotlinStringDsl
public inline fun <SELF : KotlinDotSeparatedAppendable<SELF>> SELF.define(block: SELF.() -> Unit): SELF =
    appendDefine(block)