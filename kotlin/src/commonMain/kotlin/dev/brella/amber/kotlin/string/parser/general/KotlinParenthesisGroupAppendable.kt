package dev.brella.amber.kotlin.string.parser.general

import dev.brella.amber.kotlin.KotlinAppendable
import dev.brella.amber.kotlin.KotlinStringDsl

public typealias KotlinRoundBracketGroupAppendable<SELF> = KotlinParenthesisGroupAppendable<SELF>

public interface KotlinParenthesisGroupAppendable<SELF : KotlinParenthesisGroupAppendable<SELF>> : KotlinAppendable<SELF>

@KotlinStringDsl
public inline fun <SELF : KotlinParenthesisGroupAppendable<*>> SELF.parenthesisGroup(block: SELF.() -> Unit): SELF =
    try {
        append('(')
        this.block()
        this
    } finally {
        append(')')
    }

@KotlinStringDsl
public inline fun <SELF : KotlinParenthesisGroupAppendable<*>> SELF.roundBracketGroup(block: SELF.() -> Unit): SELF =
    parenthesisGroup(block)