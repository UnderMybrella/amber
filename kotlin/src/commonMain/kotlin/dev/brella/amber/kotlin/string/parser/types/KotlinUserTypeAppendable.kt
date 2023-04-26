package dev.brella.amber.kotlin.string.parser.types

import dev.brella.amber.kotlin.KotlinAppendable
import dev.brella.amber.kotlin.KotlinStringDsl
import dev.brella.amber.kotlin.string.parser.KotlinIdentifierAppendable

public interface KotlinUserTypeAppendable<SELF : KotlinUserTypeAppendable<SELF>> : KotlinAppendable<SELF>,
    KotlinTypeProjectionAppendable<SELF>, KotlinIdentifierAppendable<SELF>

@KotlinStringDsl
public inline fun <SELF : KotlinUserTypeAppendable<*>> SELF.userType(identifier: String): SELF {
    appendIdentifier(identifier)
    return this
}

@KotlinStringDsl
public inline fun <SELF : KotlinUserTypeAppendable<*>> SELF.userType(identifier: String, typeArguments: KotlinTypeProjectionAppendable<*>.() -> Unit): SELF {
    appendIdentifier(identifier)
    this.typeArguments(typeArguments)
    return this
}