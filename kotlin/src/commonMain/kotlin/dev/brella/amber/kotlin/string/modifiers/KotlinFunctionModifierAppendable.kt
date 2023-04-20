package dev.brella.amber.kotlin.string.modifiers

import dev.brella.amber.kotlin.KotlinAppendable

public interface KotlinFunctionModifierAppendable<SELF: KotlinFunctionModifierAppendable<SELF>> : KotlinAppendable<SELF> {
    public fun appendTailRec(): SELF = appendAutoSpaced("tailrec")
    public fun appendOperator(): SELF = appendAutoSpaced("operator")
    public fun appendInfix(): SELF = appendAutoSpaced("infix")
    public fun appendInline(): SELF = appendAutoSpaced("inline")
    public fun appendExternal(): SELF = appendAutoSpaced("external")
    public fun appendSuspend(): SELF = appendAutoSpaced("suspend")
}