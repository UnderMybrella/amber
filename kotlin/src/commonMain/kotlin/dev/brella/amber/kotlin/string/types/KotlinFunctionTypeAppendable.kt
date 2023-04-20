package dev.brella.amber.kotlin.string.types

import dev.brella.amber.kotlin.KotlinAppendable
import dev.brella.amber.kotlin.KotlinStringDsl

public interface KotlinFunctionTypeAppendable<SELF : KotlinFunctionTypeAppendable<SELF>> : KotlinAppendable<SELF>,
    KotlinReceiverTypeAppendable<SELF>, KotlinTypeAppendableAccessor {
    public fun appendFunctionTypeReturn(): SELF = appendAutoSpaced("->")
}

@KotlinStringDsl
public inline fun <SELF : KotlinFunctionTypeAppendable<*>> SELF.appendReceiverType(block: KotlinReceiverTypeAppendable<*>.() -> Unit): SELF {
    this.block()
    append('.')
    return this
}

@KotlinStringDsl
public inline fun <SELF : KotlinFunctionTypeAppendable<*>> SELF.appendFunctionTypeReturn(block: KotlinTypeAppendable<*>.() -> Unit): SELF {
    appendFunctionTypeReturn()
    this.type(block)
    return this
}