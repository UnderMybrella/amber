package dev.brella.amber.kotlin.string.types

import dev.brella.amber.kotlin.KotlinAppendable
import dev.brella.amber.kotlin.KotlinStringDsl
import dev.brella.amber.kotlin.string.KotlinAnnotationAppendable
import dev.brella.amber.kotlin.string.groups.KotlinChevronGroupAppendable
import dev.brella.amber.kotlin.string.groups.KotlinCommaSeparatedAppendable
import dev.brella.amber.kotlin.string.groups.chevronGroup
import dev.brella.amber.kotlin.string.modifiers.KotlinVarianceModifierAppendable

public interface KotlinTypeProjectionAppendable<SELF : KotlinTypeProjectionAppendable<SELF>> : KotlinAppendable<SELF>,
    KotlinChevronGroupAppendable<SELF>, KotlinCommaSeparatedAppendable<SELF>, KotlinVarianceModifierAppendable<SELF>,
    KotlinAnnotationAppendable<SELF>, KotlinTypeAppendableAccessor {
    public fun appendTypeArgumentSeparator(): SELF = appendCommaSeparator()
    public fun appendStarProjection(): SELF = append("*")
}

@KotlinStringDsl
public inline fun <SELF : KotlinTypeProjectionAppendable<*>> SELF.typeArguments(block: SELF.() -> Unit): SELF =
    chevronGroup(block)