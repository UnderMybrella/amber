package dev.brella.amber.kotlin.string.lexer.keywords

import dev.brella.amber.kotlin.KotlinStringDsl

public interface KotlinNoInlineAppendable<SELF : KotlinNoInlineAppendable<SELF>> : KotlinKeywordAppendable<SELF> {
    public fun appendNoInline(): SELF = appendKeyword("noinline")
}

@KotlinStringDsl
public inline fun <SELF : KotlinNoInlineAppendable<SELF>> SELF.noinline(): SELF =
    appendNoInline()

@KotlinStringDsl
public inline fun <SELF : KotlinNoInlineAppendable<SELF>> SELF.noinline(block: SELF.() -> Unit): SELF {
    appendNoInline()
    this.block()
    return this
}