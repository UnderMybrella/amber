package dev.brella.amber.kotlin.string

import dev.brella.amber.kotlin.KotlinAppendable

public interface KotlinIdentifierAppendable<SELF: KotlinIdentifierAppendable<SELF>> : KotlinAppendable<SELF> {
    public fun appendIdentifier(identifier: String): SELF = append(identifier)
}