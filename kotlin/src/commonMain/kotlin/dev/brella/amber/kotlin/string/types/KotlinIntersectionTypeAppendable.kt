package dev.brella.amber.kotlin.string.types

import dev.brella.amber.kotlin.KotlinAppendable
import dev.brella.amber.kotlin.KotlinStringDsl
import dev.brella.amber.kotlin.string.groups.KotlinAmpersandSeparatedAppendable
import dev.brella.amber.kotlin.string.modifiers.KotlinTypeModifierAppendable

public interface KotlinIntersectionTypeAppendable<SELF : KotlinIntersectionTypeAppendable<SELF>> :
    KotlinAppendable<SELF>, KotlinAmpersandSeparatedAppendable<SELF>, KotlinTypeModifierAppendable<SELF>,
    KotlinUserTypeAppendable<SELF>, KotlinParenthesisedUserTypeAppendable<SELF> {
}

@KotlinStringDsl
public inline fun <SELF : KotlinIntersectionTypeAppendable<*>> SELF.definitelyNonNullableType(
    firstTypeModifiers: KotlinTypeModifierAppendable<*>.() -> Unit = {},
    firstType: SELF.() -> Unit = {},
    secondTypeModifiers: KotlinTypeModifierAppendable<*>.() -> Unit = {},
    secondType: SELF.() -> Unit = {},
): SELF {
    this.firstTypeModifiers()
    this.firstType()
    appendAmpersand()
    this.secondTypeModifiers()
    this.secondType()

    return this
}