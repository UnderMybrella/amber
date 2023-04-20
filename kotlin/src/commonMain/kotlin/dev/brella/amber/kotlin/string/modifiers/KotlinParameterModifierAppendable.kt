package dev.brella.amber.kotlin.string.modifiers

import dev.brella.amber.kotlin.KotlinAppendable

public interface KotlinParameterModifierAppendable<SELF : KotlinParameterModifierAppendable<SELF>> :
    KotlinAppendable<SELF> {
    public fun appendVarArg(): SELF = appendAutoSpaced("vararg")
    public fun appendNoInline(): SELF = appendAutoSpaced("noinline")
    public fun appendCrossInline(): SELF = appendAutoSpaced("crossinline")
}