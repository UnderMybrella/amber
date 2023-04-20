package dev.brella.amber.kotlin.string.modifiers

import dev.brella.amber.kotlin.KotlinAppendable

public interface KotlinInheritanceModifierAppendable<SELF : KotlinInheritanceModifierAppendable<SELF>> :
    KotlinAppendable<SELF> {
    public fun appendAbstract(): SELF = appendAutoSpaced("abstract")
    public fun appendFinal(): SELF = appendAutoSpaced("final")
    public fun appendOpen(): SELF = appendAutoSpaced("open")
}