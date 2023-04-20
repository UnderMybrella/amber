package dev.brella.amber.kotlin.string.types

import dev.brella.amber.kotlin.KotlinAppendable
import dev.brella.amber.kotlin.KotlinStringDsl
import dev.brella.amber.kotlin.string.modifiers.KotlinTypeModifierAppendable

public interface KotlinReceiverTypeAppendable<SELF : KotlinReceiverTypeAppendable<SELF>> : KotlinAppendable<SELF>,
    KotlinTypeModifierAppendable<SELF>, KotlinTypeReferenceAppendable<SELF> {

}

@KotlinStringDsl
public inline fun <SELF : KotlinReceiverTypeAppendable<*>> SELF.parenthesized(block: SELF.() -> Unit): SELF =
    try {
        append('(')
        this.block()
        this
    } finally {
        append(')')
    }

@KotlinStringDsl
public inline fun <SELF : KotlinReceiverTypeAppendable<*>> SELF.nullable(block: SELF.() -> Unit): SELF {
    this.block()
    append('?')
    return this
}