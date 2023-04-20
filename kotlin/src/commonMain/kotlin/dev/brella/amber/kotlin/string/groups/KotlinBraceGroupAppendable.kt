package dev.brella.amber.kotlin.string.groups

import dev.brella.amber.kotlin.KotlinAppendable
import dev.brella.amber.kotlin.KotlinStringDsl

public typealias KotlinCurlyBracketGroupAppendable<SELF> = KotlinBraceGroupAppendable<SELF>
public typealias KotlinBodyAppendable<SELF> = KotlinBraceGroupAppendable<SELF>

public interface KotlinBraceGroupAppendable<SELF : KotlinBraceGroupAppendable<SELF>> : KotlinAppendable<SELF>

@KotlinStringDsl
public inline fun <SELF : KotlinBraceGroupAppendable<*>> SELF.braceGroup(block: SELF.() -> Unit): SELF =
    try {
        appendLine('{')
        this.block()
        this
    } finally {
        appendLine('}')
    }

@KotlinStringDsl
public inline fun <SELF : KotlinBraceGroupAppendable<*>> SELF.curlyBracketGroup(block: SELF.() -> Unit): SELF =
    braceGroup(block)

@KotlinStringDsl
public inline fun <SELF : KotlinBraceGroupAppendable<*>> SELF.body(block: SELF.() -> Unit): SELF =
    braceGroup(block)