package dev.brella.amber.kotlin.string.lexer.keywords

public interface KotlinOperatorAppendable<SELF : KotlinOperatorAppendable<SELF>> : KotlinKeywordAppendable<SELF> {
    public fun appendOperator(): SELF = appendKeyword("operator")
}