package dev.brella.amber.kotlin.string.lexer.keywords

import dev.brella.amber.kotlin.KotlinStringDsl

public interface KotlinCrossInlineAppendable<SELF : KotlinCrossInlineAppendable<SELF>> : KotlinKeywordAppendable<SELF> {
    public fun appendCrossInline(): SELF = appendKeyword("crossinline")
}

@KotlinStringDsl
public inline fun <SELF : KotlinCrossInlineAppendable<SELF>> SELF.crossinline(): SELF =
    appendCrossInline()

@KotlinStringDsl
public inline fun <SELF : KotlinCrossInlineAppendable<SELF>> SELF.crossinline(block: SELF.() -> Unit): SELF {
    this.appendCrossInline()
    this.block()
    return this
}