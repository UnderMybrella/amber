package dev.brella.amber.kotlin.string.modifiers

import dev.brella.amber.kotlin.KotlinAppendable
import dev.brella.amber.kotlin.string.KotlinAnnotationAppendable

public interface KotlinTypeParameterModifierAppendable<SELF : KotlinTypeParameterModifierAppendable<SELF>> :
    KotlinAppendable<SELF>, KotlinReificationModifierAppendable<SELF>, KotlinVarianceModifierAppendable<SELF>,
    KotlinAnnotationAppendable<SELF>