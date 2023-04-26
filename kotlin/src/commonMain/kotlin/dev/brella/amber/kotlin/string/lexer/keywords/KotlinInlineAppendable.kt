package dev.brella.amber.kotlin.string.lexer.keywords

import dev.brella.amber.kotlin.KotlinStringDsl

public interface KotlinInlineAppendable<SELF : KotlinInlineAppendable<SELF>> : KotlinKeywordAppendable<SELF> {
    public fun appendInline(): SELF = appendKeyword("inline")
}

@KotlinStringDsl
public inline fun <SELF : KotlinInlineAppendable<SELF>> SELF.inline(): SELF =
    appendInline()