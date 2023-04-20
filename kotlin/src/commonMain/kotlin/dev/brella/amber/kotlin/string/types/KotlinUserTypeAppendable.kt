package dev.brella.amber.kotlin.string.types

import dev.brella.amber.kotlin.KotlinAppendable
import dev.brella.amber.kotlin.KotlinStringDsl
import dev.brella.amber.kotlin.string.KotlinIdentifierAppendable

public interface KotlinUserTypeAppendable<SELF : KotlinUserTypeAppendable<SELF>> : KotlinAppendable<SELF>,
    KotlinTypeProjectionAppendable<SELF>, KotlinIdentifierAppendable<SELF> {
}

@KotlinStringDsl
public inline fun <SELF : KotlinUserTypeAppendable<*>> SELF.parenthesized(block: SELF.() -> Unit): SELF =
    try {
        append('(')
        this.block()
        this
    } finally {
        append(')')
    }