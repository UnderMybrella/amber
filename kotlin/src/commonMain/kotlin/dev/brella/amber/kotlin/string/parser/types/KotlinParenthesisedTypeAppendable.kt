package dev.brella.amber.kotlin.string.parser.types

import dev.brella.amber.kotlin.KotlinAppendable
import dev.brella.amber.kotlin.KotlinStringDsl
import dev.brella.amber.kotlin.string.parser.general.KotlinParenthesisGroupAppendable
import dev.brella.amber.kotlin.string.parser.general.parenthesisGroup

public interface KotlinParenthesisedTypeAppendable<SELF : KotlinParenthesisedTypeAppendable<SELF>> :
    KotlinAppendable<SELF>, KotlinParenthesisGroupAppendable<SELF>, KotlinTypeAppendableAccessor

@KotlinStringDsl
public inline fun <SELF : KotlinParenthesisedTypeAppendable<*>> SELF.parenthesizedType(block: KotlinTypeAppendable<*>.() -> Unit): SELF =
    parenthesisGroup { type(block) }