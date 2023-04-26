package dev.brella.amber.kotlin.string.lexer.keywords

public interface KotlinTailRecursiveAppendable<SELF : KotlinTailRecursiveAppendable<SELF>> :
    KotlinKeywordAppendable<SELF> {
    public fun appendTailRec(): SELF = appendKeyword("tailrec")
    public fun appendTailRecursive(): SELF = appendKeyword("tailrec")
}