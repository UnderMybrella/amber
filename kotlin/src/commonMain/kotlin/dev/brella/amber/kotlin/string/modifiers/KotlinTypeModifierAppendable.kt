package dev.brella.amber.kotlin.string.modifiers

import dev.brella.amber.kotlin.KotlinAppendable
import dev.brella.amber.kotlin.string.KotlinAnnotationAppendable

public interface KotlinTypeModifierAppendable<SELF : KotlinTypeModifierAppendable<SELF>> : KotlinAppendable<SELF>,
    KotlinAnnotationAppendable<SELF> {
    public fun appendSuspend(): SELF = appendAutoSpaced("suspend")
}