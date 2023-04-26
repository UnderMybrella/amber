package dev.brella.amber.kotlin.string.parser.types

import dev.brella.amber.kotlin.KotlinAppendable
import dev.brella.amber.kotlin.KotlinStringDsl
import dev.brella.amber.kotlin.string.parser.general.KotlinCommaSeparatedAppendable
import dev.brella.amber.kotlin.string.parser.general.KotlinParenthesisGroupAppendable
import dev.brella.amber.kotlin.string.parser.general.parenthesisGroup

public interface KotlinFunctionTypeParameterAppendable<SELF : KotlinFunctionTypeParameterAppendable<SELF>> :
    KotlinAppendable<SELF>, KotlinParenthesisGroupAppendable<SELF>, KotlinCommaSeparatedAppendable<SELF>,
    KotlinTypeAppendableAccessor {
    public fun appendFunctionTypeParameterSeparator(): SELF = appendCommaSeparator()
}

@KotlinStringDsl
public inline fun <SELF : KotlinFunctionTypeParameterAppendable<*>> SELF.functionTypeParameters(block: SELF.() -> Unit): SELF =
    parenthesisGroup(block)