package dev.brella.amber.kotlin.string.parser.modifiers

import dev.brella.amber.common.SelfAppendable
import dev.brella.amber.kotlin.string.parser.KotlinAnnotationAppendable

public interface KotlinModifierAppendable<SELF : KotlinModifierAppendable<SELF>> : SelfAppendable<SELF>,
    KotlinAnnotationAppendable<SELF>, KotlinClassModifierAppendable<SELF>, KotlinMemberModifierAppendable<SELF>,
    KotlinVisibilityModifierAppendable<SELF>, KotlinFunctionModifierAppendable<SELF>,
    KotlinPropertyModifierAppendable<SELF>, KotlinInheritanceModifierAppendable<SELF>,
    KotlinParameterModifierAppendable<SELF>, KotlinPlatformModifierAppendable<SELF>