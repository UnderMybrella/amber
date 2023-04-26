package dev.brella.amber.kotlin.string.parser.modifiers

import dev.brella.amber.kotlin.KotlinAppendable
import dev.brella.amber.kotlin.string.lexer.keywords.*

public interface KotlinFunctionModifierAppendable<SELF : KotlinFunctionModifierAppendable<SELF>> :
    KotlinAppendable<SELF>, KotlinSuspendAppendable<SELF>, KotlinTailRecursiveAppendable<SELF>,
    KotlinOperatorAppendable<SELF>, KotlinInfixAppendable<SELF>, KotlinInlineAppendable<SELF>,
    KotlinExternalAppendable<SELF>