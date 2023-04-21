package dev.brella.amber.kotlin.string.types

import dev.brella.amber.kotlin.KotlinAppendable
import dev.brella.amber.kotlin.KotlinStringDsl
import dev.brella.amber.kotlin.string.groups.KotlinNullableAppendable

public interface KotlinNullableTypeAppendable<SELF : KotlinNullableTypeAppendable<SELF>> : KotlinAppendable<SELF>,
    KotlinNullableAppendable<SELF>, KotlinTypeReferenceAppendable<SELF>, KotlinParenthesisedTypeAppendable<SELF>

@KotlinStringDsl
public inline fun <SELF : KotlinNullableTypeAppendable<*>> SELF.nullableType(block: SELF.() -> Unit): SELF {
    this.block()
    append('?')
    return this
}