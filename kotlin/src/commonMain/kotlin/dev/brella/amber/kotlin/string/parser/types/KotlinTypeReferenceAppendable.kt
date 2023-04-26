package dev.brella.amber.kotlin.string.parser.types

import dev.brella.amber.kotlin.KotlinAppendable

public interface KotlinTypeReferenceAppendable<SELF : KotlinTypeReferenceAppendable<SELF>> : KotlinAppendable<SELF>,
    KotlinUserTypeAppendable<SELF> {
    public fun appendDynamic(): SELF = append("dynamic")
}