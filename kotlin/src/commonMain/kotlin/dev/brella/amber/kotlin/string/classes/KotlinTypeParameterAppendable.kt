package dev.brella.amber.kotlin.string.classes

import dev.brella.amber.kotlin.KotlinAppendable
import dev.brella.amber.kotlin.KotlinStringDsl
import dev.brella.amber.kotlin.string.KotlinIdentifierAppendable
import dev.brella.amber.kotlin.string.groups.KotlinChevronGroupAppendable
import dev.brella.amber.kotlin.string.groups.KotlinCommaSeparatedAppendable
import dev.brella.amber.kotlin.string.groups.chevronGroup
import dev.brella.amber.kotlin.string.modifiers.KotlinTypeParameterModifierAppendable
import dev.brella.amber.kotlin.string.types.KotlinFunctionTypeParameterAppendable
import dev.brella.amber.kotlin.string.types.KotlinTypeAppendable
import dev.brella.amber.kotlin.string.types.type

public interface KotlinTypeParameterAppendable<SELF : KotlinTypeParameterAppendable<SELF>> : KotlinAppendable<SELF>,
    KotlinCommaSeparatedAppendable<SELF>, KotlinChevronGroupAppendable<SELF>,
    KotlinTypeParameterModifierAppendable<SELF>, KotlinIdentifierAppendable<SELF>, KotlinTypeAppendable<SELF> {
    public fun appendTypeParameterSeparator(): SELF = appendCommaSeparator()
}

@KotlinStringDsl
public inline fun <SELF : KotlinTypeParameterAppendable<*>> SELF.appendSuperType(block: KotlinTypeAppendable<*>.() -> Unit): SELF {
    append(':')
    this.block()
    return this
}

@KotlinStringDsl
public inline fun <SELF : KotlinTypeParameterAppendable<*>> SELF.typeParameters(block: SELF.() -> Unit): SELF =
    chevronGroup(block)

@KotlinStringDsl
public inline fun <SELF : KotlinTypeParameterAppendable<*>> SELF.typeParameter(
    typeParameterModifiers: KotlinTypeParameterModifierAppendable<*>.() -> Unit = { },
    identifier: String,
): SELF {
    this.typeParameterModifiers()
    this.appendIdentifier(identifier)

    return this
}

@KotlinStringDsl
public inline fun <SELF : KotlinTypeParameterAppendable<*>> SELF.typeParameter(
    typeParameterModifiers: KotlinTypeParameterModifierAppendable<*>.() -> Unit = { },
    identifier: String,
    supertype: KotlinTypeAppendable<*>.() -> Unit
): SELF {
    this.typeParameterModifiers()
    this.appendIdentifier(identifier)
    this.appendSuperType(supertype)

    return this
}