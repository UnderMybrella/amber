package dev.brella.amber.kotlin.string.parser.general

import dev.brella.amber.kotlin.KotlinAppendable
import dev.brella.amber.kotlin.KotlinStringDsl

public typealias KotlinSquareBracketGroupAppendable<SELF> = KotlinBracketGroupAppendable<SELF>

public interface KotlinBracketGroupAppendable<SELF : KotlinBracketGroupAppendable<SELF>> : KotlinAppendable<SELF>

@KotlinStringDsl
public inline fun <SELF : KotlinBracketGroupAppendable<*>> SELF.bracketGroup(block: SELF.() -> Unit): SELF =
    try {
        append('[')
        this.block()
        this
    } finally {
        append(']')
    }

@KotlinStringDsl
public inline fun <SELF : KotlinBracketGroupAppendable<*>> SELF.squareBracketGroup(block: SELF.() -> Unit): SELF =
    bracketGroup(block)