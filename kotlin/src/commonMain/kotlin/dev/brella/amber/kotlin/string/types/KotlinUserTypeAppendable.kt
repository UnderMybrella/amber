package dev.brella.amber.kotlin.string.types

import dev.brella.amber.kotlin.KotlinAppendable
import dev.brella.amber.kotlin.KotlinStringDsl
import dev.brella.amber.kotlin.string.KotlinIdentifierAppendable
import dev.brella.amber.kotlin.string.groups.KotlinParenthesisGroupAppendable
import dev.brella.amber.kotlin.string.groups.parenthesisGroup

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