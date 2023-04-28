package dev.brella.amber.sql.postgres.string.lexer.keywords

import dev.brella.amber.sql.postgres.PostgreSqlAppendable
import dev.brella.amber.sql.postgres.stringFor

public interface PostgreSqlKeywordAppendable<SELF : PostgreSqlKeywordAppendable<SELF>> : PostgreSqlAppendable<SELF> {
    public fun appendKeyword(keyword: EnumPostgreSqlKeyword): SELF =
        appendAutoSpaced(settings.stringFor(keyword))

    public fun appendKeywordSurround(keyword: EnumPostgreSqlKeyword): SELF =
        appendAutoSpacedSurround(settings.stringFor(keyword))
}

public inline fun <SELF : PostgreSqlKeywordAppendable<*>> SELF.keyword(keyword: EnumPostgreSqlKeyword): SELF {
    appendKeyword(keyword)
    return this
}

public inline fun <SELF : PostgreSqlKeywordAppendable<*>> SELF.keywordSurround(keyword: EnumPostgreSqlKeyword): SELF {
    appendKeywordSurround(keyword)
    return this
}