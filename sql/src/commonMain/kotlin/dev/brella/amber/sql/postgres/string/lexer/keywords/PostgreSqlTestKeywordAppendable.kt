package dev.brella.amber.sql.postgres.string.lexer.keywords

public interface PostgreSqlTestKeywordAppendable<SELF : PostgreSqlTestKeywordAppendable<SELF>> : PostgreSqlKeywordAppendable<SELF> {
    public fun appendTest(): SELF = appendKeyword(EnumPostgreSqlKeyword.ALL)
}

public inline fun <SELF : PostgreSqlTestKeywordAppendable<*>> SELF.test(): SELF {
    appendTest()
    return this
}