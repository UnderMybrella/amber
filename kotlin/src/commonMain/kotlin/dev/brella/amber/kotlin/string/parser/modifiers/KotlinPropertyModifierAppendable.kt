package dev.brella.amber.kotlin.string.parser.modifiers

import dev.brella.amber.kotlin.KotlinAppendable

public interface KotlinPropertyModifierAppendable<SELF : KotlinPropertyModifierAppendable<SELF>> :
    KotlinAppendable<SELF> {
    public fun appendConst(): SELF = appendAutoSpaced("const")
}