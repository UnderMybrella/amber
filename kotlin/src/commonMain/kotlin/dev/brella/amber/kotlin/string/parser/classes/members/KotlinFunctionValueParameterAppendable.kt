package dev.brella.amber.kotlin.string.parser.classes.members

import dev.brella.amber.kotlin.KotlinAppendable
import dev.brella.amber.kotlin.KotlinStringDsl
import dev.brella.amber.kotlin.string.parser.general.KotlinCommaSeparatedAppendable
import dev.brella.amber.kotlin.string.parser.general.KotlinParenthesisGroupAppendable
import dev.brella.amber.kotlin.string.parser.general.parenthesisGroup
import dev.brella.amber.kotlin.string.parser.modifiers.KotlinParameterModifierAppendable

public interface KotlinFunctionValueParameterAppendable<SELF : KotlinFunctionValueParameterAppendable<SELF>> :
    KotlinAppendable<SELF>, KotlinParenthesisGroupAppendable<SELF>, KotlinCommaSeparatedAppendable<SELF>,
    KotlinParameterModifierAppendable<SELF>, KotlinParameterAppendable<SELF>

@KotlinStringDsl
public inline fun <SELF : KotlinFunctionValueParameterAppendable<*>> SELF.appendFunctionValueParameters(block: SELF.() -> Unit): SELF =
    parenthesisGroup(block)

@KotlinStringDsl
public inline fun <SELF : KotlinFunctionValueParameterAppendable<*>> SELF.functionValueParameters(block: SELF.() -> Unit): SELF =
    appendFunctionValueParameters(block)