package dev.brella.amber.sql.postgres.string.parser.clauses

import dev.brella.amber.sql.postgres.PostgreSqlAppendable
import dev.brella.amber.sql.postgres.string.lexer.keywords.PostgreSqlIntoKeywordAppendable
import dev.brella.amber.sql.postgres.string.lexer.keywords.PostgreSqlStrictKeywordAppendable

public interface PostgreSqlIntoClauseAppendable<SELF : PostgreSqlIntoClauseAppendable<SELF>> : PostgreSqlAppendable<SELF>, PostgreSqlIntoKeywordAppendable<SELF>, PostgreSqlStrictKeywordAppendable<SELF>,  {
}