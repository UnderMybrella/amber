package dev.brella.amber.kotlin.string.types

import dev.brella.amber.kotlin.KotlinAppendable
import dev.brella.amber.kotlin.KotlinStringDsl
import dev.brella.amber.kotlin.string.groups.KotlinParenthesisGroupAppendable
import dev.brella.amber.kotlin.string.groups.parenthesisGroup

public interface KotlinParenthesisedUserTypeAppendable<SELF : KotlinParenthesisedUserTypeAppendable<SELF>> :
    KotlinAppendable<SELF>, KotlinUserTypeAppendable<SELF>, KotlinParenthesisGroupAppendable<SELF>

@KotlinStringDsl
public inline fun <SELF : KotlinParenthesisedUserTypeAppendable<*>> SELF.parenthesisedUserType(block: SELF.() -> Unit): SELF =
    parenthesisGroup(block)