package dev.brella.amber.kotlin.string.modifiers

import dev.brella.amber.kotlin.KotlinAppendable

public interface KotlinMemberModifierAppendable<SELF : KotlinMemberModifierAppendable<SELF>> :
    KotlinAppendable<SELF> {
    public fun appendOverride(): SELF = appendAutoSpaced("override")
    public fun appendLateInit(): SELF = appendAutoSpaced("lateinit")
}