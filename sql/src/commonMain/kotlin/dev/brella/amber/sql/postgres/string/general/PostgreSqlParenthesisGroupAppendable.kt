package dev.brella.amber.sql.postgres.string.general

import dev.brella.amber.sql.SqlStringDsl
import dev.brella.amber.sql.postgres.PostgreSqlAppendable

public typealias PostgreSqlRoundBracketGroupAppendable<SELF> = PostgreSqlParenthesisGroupAppendable<SELF>

public interface PostgreSqlParenthesisGroupAppendable<SELF : PostgreSqlParenthesisGroupAppendable<SELF>> :
    PostgreSqlAppendable<SELF> {
    public fun appendOpenParenthesis(): SELF = append('(')
    public fun appendCloseParenthesis(): SELF = append(')')
}

public inline fun <SELF : PostgreSqlParenthesisGroupAppendable<*>> SELF.appendOpenRoundBracket(): SELF {
    appendOpenParenthesis()
    return this
}
public inline fun <SELF : PostgreSqlParenthesisGroupAppendable<*>> SELF.openParenthesis(): SELF {
    appendOpenParenthesis()
    return this
}
public inline fun <SELF : PostgreSqlParenthesisGroupAppendable<*>> SELF.openRoundBracket(): SELF {
    appendOpenParenthesis()
    return this
}

public inline fun <SELF : PostgreSqlParenthesisGroupAppendable<*>> SELF.appendCloseRoundBracket(): SELF {
    appendCloseParenthesis()
    return this
}
public inline fun <SELF : PostgreSqlParenthesisGroupAppendable<*>> SELF.closeParenthesis(): SELF {
    appendCloseParenthesis()
    return this
}
public inline fun <SELF : PostgreSqlParenthesisGroupAppendable<*>> SELF.closeRoundBracket(): SELF {
    appendCloseParenthesis()
    return this
}

@SqlStringDsl
public inline fun <SELF : PostgreSqlParenthesisGroupAppendable<*>> SELF.parenthesisGroup(block: SELF.() -> Unit): SELF =
    try {
        append('(')
        this.block()
        this
    } finally {
        append(')')
    }

@SqlStringDsl
public inline fun <SELF : PostgreSqlParenthesisGroupAppendable<*>> SELF.roundBracketGroup(block: SELF.() -> Unit): SELF =
    parenthesisGroup(block)