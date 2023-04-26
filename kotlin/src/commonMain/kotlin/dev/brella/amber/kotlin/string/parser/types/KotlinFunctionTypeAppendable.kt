package dev.brella.amber.kotlin.string.parser.types

import dev.brella.amber.kotlin.KotlinAppendable
import dev.brella.amber.kotlin.KotlinStringDsl
import dev.brella.amber.kotlin.string.lexer.keywords.KotlinArrowAppendable

public interface KotlinFunctionTypeAppendable<SELF : KotlinFunctionTypeAppendable<SELF>> : KotlinAppendable<SELF>,
    KotlinReceiverTypeAppendable<SELF>, KotlinTypeAppendableAccessor, KotlinArrowAppendable<SELF> {
    public fun appendFunctionTypeReturn(): SELF = appendArrow()
}

@KotlinStringDsl
public inline fun <SELF : KotlinFunctionTypeAppendable<*>> SELF.appendFunctionTypeReturn(block: KotlinTypeAppendable<*>.() -> Unit): SELF {
    appendFunctionTypeReturn()
    this.type(block)
    return this
}

@KotlinStringDsl
public inline fun <SELF : KotlinFunctionTypeAppendable<SELF>> SELF.functionTypeReturn(block: KotlinTypeAppendable<*>.() -> Unit): SELF =
    appendFunctionTypeReturn(block)