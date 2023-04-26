package dev.brella.amber.kotlin.string.parser.modifiers

import dev.brella.amber.kotlin.KotlinAppendable

public interface KotlinReificationModifierAppendable<SELF : KotlinReificationModifierAppendable<SELF>> :
    KotlinAppendable<SELF> {
    public fun appendReified(): SELF = appendAutoSpaced("reified")
}