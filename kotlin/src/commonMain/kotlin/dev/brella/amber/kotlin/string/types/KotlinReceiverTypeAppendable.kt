package dev.brella.amber.kotlin.string.types

import dev.brella.amber.kotlin.KotlinAppendable
import dev.brella.amber.kotlin.KotlinStringDsl
import dev.brella.amber.kotlin.string.groups.KotlinParenthesisGroupAppendable
import dev.brella.amber.kotlin.string.groups.parenthesisGroup
import dev.brella.amber.kotlin.string.modifiers.KotlinTypeModifierAppendable

public interface KotlinReceiverTypeAppendable<SELF : KotlinReceiverTypeAppendable<SELF>> : KotlinAppendable<SELF>,
    KotlinTypeModifierAppendable<SELF>, KotlinParenthesisedTypeAppendable<SELF>, KotlinNullableTypeAppendable<SELF>, KotlinTypeReferenceAppendable<SELF>