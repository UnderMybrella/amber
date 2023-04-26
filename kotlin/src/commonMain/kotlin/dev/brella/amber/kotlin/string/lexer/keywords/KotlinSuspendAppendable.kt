package dev.brella.amber.kotlin.string.lexer.keywords

import dev.brella.amber.kotlin.KotlinAppendable

public interface KotlinSuspendAppendable<SELF: KotlinSuspendAppendable<SELF>> : KotlinAppendable<SELF>,
    KotlinKeywordAppendable<SELF> {
    public fun appendSuspend(): SELF = appendKeyword("suspend")
}