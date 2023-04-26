package dev.brella.amber.kotlin.string.parser.classes.members

import dev.brella.amber.kotlin.KotlinAppendable
import dev.brella.amber.kotlin.KotlinStringDsl
import dev.brella.amber.kotlin.string.lexer.keywords.KotlinFunctionKeywordAppendable
import dev.brella.amber.kotlin.string.parser.KotlinIdentifierAppendable
import dev.brella.amber.kotlin.string.parser.classes.KotlinTypeParameterAppendable
import dev.brella.amber.kotlin.string.parser.intersections.KotlinColonTypeAppendable
import dev.brella.amber.kotlin.string.parser.modifiers.KotlinModifierAppendable
import dev.brella.amber.kotlin.string.parser.types.KotlinReceiverTypeAppendable

public interface KotlinFunctionDeclarationAppendable<SELF : KotlinFunctionDeclarationAppendable<SELF>> :
    KotlinAppendable<SELF>, KotlinModifierAppendable<SELF>, KotlinFunctionKeywordAppendable<SELF>,
    KotlinTypeParameterAppendable<SELF>, KotlinReceiverTypeAppendable<SELF>, KotlinIdentifierAppendable<SELF>,
    KotlinFunctionValueParameterAppendable<SELF>, KotlinColonTypeAppendable<SELF>

@KotlinStringDsl
public inline fun <SELF : KotlinFunctionDeclarationAppendable<*>> SELF.buildFunctionDeclaration(block: KotlinFunctionDeclarationAppendable<*>.() -> Unit): SELF {
    this.block()
    return this
}

@KotlinStringDsl
public inline fun <SELF : KotlinFunctionDeclarationAppendable<*>> SELF.appendFunctionDeclaration(block: SELF.() -> Unit): SELF {
    this.block()
    return this
}

@KotlinStringDsl
public inline fun <SELF : KotlinFunctionDeclarationAppendable<*>> SELF.appendFunctionDeclaration(
    name: String,
    modifiers: KotlinModifierAppendable<*>.() -> Unit = {},
    typeParameters: KotlinTypeParameterAppendable<*>.() -> Unit = {},
    receiverType: KotlinReceiverTypeAppendable<*>.() -> Unit = {},
    parameters: KotlinFunctionValueParameterAppendable<*>.() -> Unit = {},
    returnType: KotlinColonTypeAppendable<*>.() -> Unit = {},
    block: SELF.() -> Unit,
): SELF {
    this.modifiers()
    this.appendFunKeyword()
    this.typeParameters()
    this.receiverType()
    this.appendIdentifier(name)
    this.parameters()
    this.returnType()
    this.block()
    return this
}