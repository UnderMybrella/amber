package dev.brella.amber.kotlin.string

import dev.brella.amber.common.SelfAppendable

public interface KotlinAnnotationAppendable<SELF: KotlinAnnotationAppendable<SELF>> : SelfAppendable<SELF> {

}