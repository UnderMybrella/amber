package dev.brella.amber.sql.postgres.string.parser

import dev.brella.amber.sql.postgres.PostgreSqlAppendable
import dev.brella.amber.sql.postgres.string.lexer.keywords.PostgreSqlLeftKeywordAppendable
import dev.brella.amber.sql.postgres.string.lexer.keywords.PostgreSqlRightKeywordAppendable

public interface PostgreSqlColumnIDAppendable<SELF : PostgreSqlColumnIDAppendable<SELF>> : PostgreSqlAppendable<SELF>, PostgreSqlLeftKeywordAppendable<SELF>, PostgreSqlRightKeywordAppendable<SELF> {
}