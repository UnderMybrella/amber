package dev.brella.amber.kotlin.string.lexer.keywords

import dev.brella.amber.kotlin.KotlinStringDsl

public interface KotlinVariableArgumentsAppendable<SELF : KotlinVariableArgumentsAppendable<SELF>> :
    KotlinKeywordAppendable<SELF> {
    public fun appendVariableArguments(): SELF = appendKeyword("vararg")
    public fun appendVarArg(): SELF = appendKeyword("vararg")
}

@KotlinStringDsl
public inline fun <SELF : KotlinVariableArgumentsAppendable<SELF>> SELF.variableArguments(): SELF =
    appendVariableArguments()

@KotlinStringDsl
public inline fun <SELF : KotlinVariableArgumentsAppendable<SELF>> SELF.variableArguments(block: SELF.() -> Unit): SELF {
    this.appendVariableArguments()
    this.block()
    return this
}

@KotlinStringDsl
public inline fun <SELF : KotlinVariableArgumentsAppendable<SELF>> SELF.vararg(): SELF =
    appendVarArg()

@KotlinStringDsl
public inline fun <SELF : KotlinVariableArgumentsAppendable<SELF>> SELF.vararg(block: SELF.() -> Unit): SELF {
    this.appendVarArg()
    this.block()
    return this
}