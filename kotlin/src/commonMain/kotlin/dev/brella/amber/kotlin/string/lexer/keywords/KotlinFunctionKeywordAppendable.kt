package dev.brella.amber.kotlin.string.lexer.keywords

import dev.brella.amber.kotlin.KotlinStringDsl

public interface KotlinFunctionKeywordAppendable<SELF : KotlinFunctionKeywordAppendable<SELF>> :
    KotlinKeywordAppendable<SELF> {
    public fun appendFunctionKeyword(): SELF = appendKeyword("fun")
    public fun appendFunKeyword(): SELF = appendKeyword("fun")
}

@KotlinStringDsl
public inline fun <SELF : KotlinFunctionKeywordAppendable<SELF>> SELF.appendFunctionKeyword(block: SELF.() -> Unit): SELF {
    this.appendFunctionKeyword()
    this.block()
    return this
}

@KotlinStringDsl
public inline fun <SELF : KotlinFunctionKeywordAppendable<SELF>> SELF.appendFunKeyword(block: SELF.() -> Unit): SELF {
    this.appendFunKeyword()
    this.block()
    return this
}

@KotlinStringDsl
public inline fun <SELF : KotlinFunctionKeywordAppendable<SELF>> SELF.functionKeyword(): SELF =
    this.appendFunctionKeyword()

@KotlinStringDsl
public inline fun <SELF : KotlinFunctionKeywordAppendable<SELF>> SELF.functionKeyword(block: SELF.() -> Unit): SELF =
    appendFunctionKeyword(block)

@KotlinStringDsl
public inline fun <SELF : KotlinFunctionKeywordAppendable<SELF>> SELF.funKeyword(): SELF =
    appendFunKeyword()

@KotlinStringDsl
public inline fun <SELF : KotlinFunctionKeywordAppendable<SELF>> SELF.funKeyword(block: SELF.() -> Unit): SELF =
    appendFunKeyword(block)