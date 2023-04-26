package dev.brella.amber.kotlin.string.parser.modifiers

import dev.brella.amber.kotlin.KotlinAppendable
import dev.brella.amber.kotlin.string.lexer.keywords.KotlinCrossInlineAppendable
import dev.brella.amber.kotlin.string.lexer.keywords.KotlinNoInlineAppendable
import dev.brella.amber.kotlin.string.lexer.keywords.KotlinVariableArgumentsAppendable

public interface KotlinParameterModifierAppendable<SELF : KotlinParameterModifierAppendable<SELF>> :
    KotlinAppendable<SELF>, KotlinVariableArgumentsAppendable<SELF>, KotlinNoInlineAppendable<SELF>,
    KotlinCrossInlineAppendable<SELF>