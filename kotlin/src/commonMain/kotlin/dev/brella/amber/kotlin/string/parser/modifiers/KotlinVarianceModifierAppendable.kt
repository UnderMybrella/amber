package dev.brella.amber.kotlin.string.parser.modifiers

import dev.brella.amber.kotlin.KotlinAppendable

public interface KotlinVarianceModifierAppendable<SELF : KotlinVarianceModifierAppendable<SELF>> :
    KotlinAppendable<SELF> {
    public fun appendIn(): SELF = appendAutoSpaced("in")
    public fun appendOut(): SELF = appendAutoSpaced("out")
}