package dev.brella.amber.kotlin.string.types

import dev.brella.amber.kotlin.KotlinAppendable
import dev.brella.amber.kotlin.KotlinStringDsl
import dev.brella.amber.kotlin.string.groups.KotlinCommaSeparatedAppendable
import dev.brella.amber.kotlin.string.groups.KotlinParenthesisGroupAppendable
import dev.brella.amber.kotlin.string.groups.parenthesisGroup

public interface KotlinFunctionTypeParameterAppendable<SELF : KotlinFunctionTypeParameterAppendable<SELF>> :
    KotlinAppendable<SELF>, KotlinParenthesisGroupAppendable<SELF>, KotlinCommaSeparatedAppendable<SELF>,
    KotlinTypeAppendableAccessor {
    public fun appendFunctionTypeParameterSeparator(): SELF = appendCommaSeparator()
}

@KotlinStringDsl
public inline fun <SELF : KotlinFunctionTypeParameterAppendable<*>> SELF.functionTypeParameters(block: SELF.() -> Unit): SELF =
    parenthesisGroup(block)