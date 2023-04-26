package dev.brella.amber.kotlin.string.parser.intersections

import dev.brella.amber.kotlin.KotlinAppendable
import dev.brella.amber.kotlin.KotlinStringDsl
import dev.brella.amber.kotlin.string.parser.general.KotlinColonSeparatedAppendable
import dev.brella.amber.kotlin.string.parser.general.appendColon
import dev.brella.amber.kotlin.string.parser.types.KotlinTypeAppendable

public interface KotlinColonTypeAppendable<SELF : KotlinColonTypeAppendable<SELF>> : KotlinAppendable<SELF>,
    KotlinColonSeparatedAppendable<SELF>, KotlinTypeAppendable<SELF>

@KotlinStringDsl
public inline fun <SELF : KotlinColonTypeAppendable<SELF>> SELF.appendColonType(block: SELF.() -> Unit): SELF =
    appendColon(block)

@KotlinStringDsl
public inline fun <SELF : KotlinColonTypeAppendable<SELF>> SELF.colonType(block: SELF.() -> Unit): SELF =
    appendColon(block)