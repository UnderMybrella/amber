package dev.brella.amber.kotlin.string.parser.modifiers

import dev.brella.amber.kotlin.KotlinAppendable

public interface KotlinClassModifierAppendable<SELF: KotlinClassModifierAppendable<SELF>> : KotlinAppendable<SELF> {
    public fun appendEnum(): SELF = appendAutoSpaced("enum")
    public fun appendSealed(): SELF = appendAutoSpaced("sealed")
    public fun appendAnnotation(): SELF = appendAutoSpaced("annotation")
    public fun appendData(): SELF = appendAutoSpaced("data")
    public fun appendInner(): SELF = appendAutoSpaced("inner")
    public fun appendValue(): SELF = appendAutoSpaced("value")
}