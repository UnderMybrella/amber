package dev.brella.amber.kotlin.string.parser.modifiers

import dev.brella.amber.kotlin.KotlinAppendable

public interface KotlinPlatformModifierAppendable<SELF : KotlinPlatformModifierAppendable<SELF>> :
    KotlinAppendable<SELF> {
    public fun appendExpect(): SELF = appendAutoSpaced("expect")
    public fun appendActual(): SELF = appendAutoSpaced("actual")
}