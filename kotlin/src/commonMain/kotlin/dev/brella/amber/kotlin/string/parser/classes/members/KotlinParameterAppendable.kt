package dev.brella.amber.kotlin.string.parser.classes.members

import dev.brella.amber.kotlin.KotlinAppendable
import dev.brella.amber.kotlin.KotlinStringDsl
import dev.brella.amber.kotlin.string.parser.KotlinIdentifierAppendable
import dev.brella.amber.kotlin.string.parser.general.KotlinColonSeparatedAppendable
import dev.brella.amber.kotlin.string.parser.general.colon
import dev.brella.amber.kotlin.string.parser.types.KotlinTypeAppendable
import dev.brella.amber.kotlin.string.parser.types.KotlinTypeAppendableAccessor
import dev.brella.amber.kotlin.string.parser.types.type

public interface KotlinParameterAppendable<SELF : KotlinParameterAppendable<SELF>> : KotlinAppendable<SELF>,
    KotlinIdentifierAppendable<SELF>, KotlinColonSeparatedAppendable<SELF>, KotlinTypeAppendableAccessor

@KotlinStringDsl
public inline fun <SELF : KotlinParameterAppendable<SELF>> SELF.appendParameter(name: String, type: KotlinTypeAppendable<*>.() -> Unit): SELF {
    append(name)
    colon { type(type) }
    return this
}

@KotlinStringDsl
public inline fun <SELF : KotlinParameterAppendable<SELF>> SELF.parameter(name: String, type: KotlinTypeAppendable<*>.() -> Unit): SELF =
    appendParameter(name, type)