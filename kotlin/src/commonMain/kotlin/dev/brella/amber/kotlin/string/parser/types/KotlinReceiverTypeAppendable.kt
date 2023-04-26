package dev.brella.amber.kotlin.string.parser.types

import dev.brella.amber.kotlin.KotlinAppendable
import dev.brella.amber.kotlin.KotlinStringDsl
import dev.brella.amber.kotlin.string.parser.general.KotlinDotSeparatedAppendable
import dev.brella.amber.kotlin.string.parser.general.appendDefine
import dev.brella.amber.kotlin.string.parser.modifiers.KotlinTypeModifierAppendable

public interface KotlinReceiverTypeAppendable<SELF : KotlinReceiverTypeAppendable<SELF>> : KotlinAppendable<SELF>,
    KotlinTypeModifierAppendable<SELF>, KotlinParenthesisedTypeAppendable<SELF>, KotlinNullableTypeAppendable<SELF>,
    KotlinTypeReferenceAppendable<SELF>, KotlinDotSeparatedAppendable<SELF>

@KotlinStringDsl
public inline fun <SELF : KotlinReceiverTypeAppendable<SELF>> SELF.appendReceiverType(block: SELF.() -> Unit): SELF =
    appendDefine(block)

@KotlinStringDsl
public inline fun <SELF : KotlinReceiverTypeAppendable<SELF>> SELF.receiverType(block: SELF.() -> Unit): SELF =
    appendReceiverType(block)