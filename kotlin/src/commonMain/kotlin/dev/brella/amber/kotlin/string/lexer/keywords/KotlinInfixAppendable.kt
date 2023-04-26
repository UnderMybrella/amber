package dev.brella.amber.kotlin.string.lexer.keywords

public interface KotlinInfixAppendable<SELF : KotlinInfixAppendable<SELF>> : KotlinKeywordAppendable<SELF> {
    public fun appendInfix(): SELF = appendKeyword("infix")
}