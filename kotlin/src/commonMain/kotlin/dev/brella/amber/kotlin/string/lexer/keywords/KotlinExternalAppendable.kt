package dev.brella.amber.kotlin.string.lexer.keywords

public interface KotlinExternalAppendable<SELF : KotlinExternalAppendable<SELF>> : KotlinKeywordAppendable<SELF> {
    public fun appendExternal(): SELF = appendKeyword("external")
}