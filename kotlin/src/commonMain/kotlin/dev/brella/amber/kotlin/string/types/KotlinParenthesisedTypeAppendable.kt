package dev.brella.amber.kotlin.string.types

import dev.brella.amber.kotlin.KotlinAppendable
import dev.brella.amber.kotlin.KotlinStringDsl
import dev.brella.amber.kotlin.string.groups.KotlinParenthesisGroupAppendable
import dev.brella.amber.kotlin.string.groups.parenthesisGroup

public interface KotlinParenthesisedTypeAppendable<SELF : KotlinParenthesisedTypeAppendable<SELF>> :
    KotlinAppendable<SELF>, KotlinParenthesisGroupAppendable<SELF>, KotlinTypeAppendableAccessor

@KotlinStringDsl
public inline fun <SELF : KotlinParenthesisedTypeAppendable<*>> SELF.parenthesizedType(block: KotlinTypeAppendable<*>.() -> Unit): SELF =
    parenthesisGroup { type(block) }