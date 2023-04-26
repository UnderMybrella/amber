package dev.brella.amber.kotlin.string.lexer.keywords

import dev.brella.amber.kotlin.KotlinAppendable

public interface KotlinKeywordAppendable<SELF: KotlinKeywordAppendable<SELF>> : KotlinAppendable<SELF> {
    public fun appendKeyword(keyword: String): SELF = appendAutoSpaced(keyword)
    public fun appendKeywordSurround(keyword: String): SELF = appendAutoSpacedSurround(keyword)
}