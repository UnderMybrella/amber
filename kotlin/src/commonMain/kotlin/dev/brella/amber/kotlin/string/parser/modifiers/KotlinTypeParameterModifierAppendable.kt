package dev.brella.amber.kotlin.string.parser.modifiers

import dev.brella.amber.kotlin.KotlinAppendable
import dev.brella.amber.kotlin.string.parser.KotlinAnnotationAppendable

public interface KotlinTypeParameterModifierAppendable<SELF : KotlinTypeParameterModifierAppendable<SELF>> :
    KotlinAppendable<SELF>, KotlinReificationModifierAppendable<SELF>, KotlinVarianceModifierAppendable<SELF>,
    KotlinAnnotationAppendable<SELF>