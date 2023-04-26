package dev.brella.amber.kotlin.string.parser.general

import dev.brella.amber.kotlin.KotlinAppendable
import dev.brella.amber.kotlin.KotlinStringDsl

public interface KotlinColonSeparatedAppendable<SELF : KotlinColonSeparatedAppendable<SELF>> : KotlinAppendable<SELF> {
    public fun appendColon(): SELF = appendAutoSpaced(':')
    public fun appendColonJoin(): SELF = appendAutoSpacedSurround(':')
}

@KotlinStringDsl
public inline fun <SELF : KotlinColonSeparatedAppendable<SELF>> SELF.appendColon(block: SELF.() -> Unit): SELF {
    this.appendColon()
    this.block()
    return this
}

@KotlinStringDsl
public inline fun <SELF : KotlinColonSeparatedAppendable<SELF>> SELF.colon(block: SELF.() -> Unit): SELF =
    appendColon(block)

public inline fun <SELF : KotlinColonSeparatedAppendable<SELF>> SELF.colon(): SELF =
    appendColon()

@KotlinStringDsl
public inline fun <SELF : KotlinColonSeparatedAppendable<SELF>> SELF.appendColonJoin(block: SELF.() -> Unit): SELF {
    this.appendColonJoin()
    this.block()
    return this
}

@KotlinStringDsl
public inline fun <SELF : KotlinColonSeparatedAppendable<SELF>> SELF.colonJoin(block: SELF.() -> Unit): SELF =
    appendColonJoin(block)

@KotlinStringDsl
public inline fun <SELF : KotlinColonSeparatedAppendable<SELF>> SELF.colonJoin(): SELF =
    appendColonJoin()