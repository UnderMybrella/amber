package dev.brella.amber.kotlin.string.parser.modifiers

import dev.brella.amber.kotlin.KotlinAppendable
import dev.brella.amber.kotlin.string.lexer.keywords.KotlinSuspendAppendable
import dev.brella.amber.kotlin.string.parser.KotlinAnnotationAppendable

public interface KotlinTypeModifierAppendable<SELF : KotlinTypeModifierAppendable<SELF>> : KotlinAppendable<SELF>,
    KotlinAnnotationAppendable<SELF>, KotlinSuspendAppendable<SELF>