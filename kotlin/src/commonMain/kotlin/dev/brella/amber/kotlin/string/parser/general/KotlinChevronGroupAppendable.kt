package dev.brella.amber.kotlin.string.parser.general

import dev.brella.amber.kotlin.KotlinAppendable
import dev.brella.amber.kotlin.KotlinStringDsl

public typealias KotlinAngleBracketGroupAppendable<SELF> = KotlinChevronGroupAppendable<SELF>

public interface KotlinChevronGroupAppendable<SELF : KotlinChevronGroupAppendable<SELF>> : KotlinAppendable<SELF>

@KotlinStringDsl
public inline fun <SELF : KotlinChevronGroupAppendable<*>> SELF.chevronGroup(block: SELF.() -> Unit): SELF =
    try {
        append('<')
        this.block()
        this
    } finally {
        append('>')
    }

@KotlinStringDsl
public inline fun <SELF : KotlinChevronGroupAppendable<*>> SELF.angleBracketGroup(block: SELF.() -> Unit): SELF =
    chevronGroup(block)