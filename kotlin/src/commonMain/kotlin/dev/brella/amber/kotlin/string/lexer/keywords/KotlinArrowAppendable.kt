package dev.brella.amber.kotlin.string.lexer.keywords

import dev.brella.amber.kotlin.KotlinStringDsl

public interface KotlinArrowAppendable<SELF : KotlinArrowAppendable<SELF>> : KotlinKeywordAppendable<SELF> {
    public fun appendArrow(): SELF = appendKeywordSurround("->")
}

@KotlinStringDsl
public inline fun <SELF : KotlinArrowAppendable<SELF>> SELF.appendArrow(block: SELF.() -> Unit): SELF {
    this.appendArrow()
    this.block()
    return this
}

@KotlinStringDsl
public inline fun <SELF : KotlinArrowAppendable<SELF>> SELF.arrow(): SELF =
    appendArrow()

@KotlinStringDsl
public inline fun <SELF : KotlinArrowAppendable<SELF>> SELF.arrow(block: SELF.() -> Unit): SELF =
    appendArrow(block)