package dev.brella.amber.kotlin.string.parser.modifiers

import dev.brella.amber.kotlin.KotlinAppendable

public interface KotlinVisibilityModifierAppendable<SELF : KotlinVisibilityModifierAppendable<SELF>> :
    KotlinAppendable<SELF> {
    public fun appendPublic(): SELF = appendAutoSpaced("public")
    public fun appendPrivate(): SELF = appendAutoSpaced("private")
    public fun appendInternal(): SELF = appendAutoSpaced("internal")
    public fun appendProtected(): SELF = appendAutoSpaced("protected")
}