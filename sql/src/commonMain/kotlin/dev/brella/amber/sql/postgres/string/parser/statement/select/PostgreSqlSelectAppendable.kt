package dev.brella.amber.sql.postgres.string.parser.statement.select

import dev.brella.amber.sql.postgres.PostgreSqlAppendable
import dev.brella.amber.sql.postgres.string.general.PostgreSqlParenthesisGroupAppendable
import dev.brella.amber.sql.postgres.string.lexer.keywords.PostgreSqlAllKeywordAppendable
import dev.brella.amber.sql.postgres.string.lexer.keywords.PostgreSqlIntoKeywordAppendable
import dev.brella.amber.sql.postgres.string.lexer.keywords.PostgreSqlSelectKeywordAppendable
import dev.brella.amber.sql.postgres.string.lexer.keywords.PostgreSqlStrictKeywordAppendable

public interface PostgreSqlSelectAppendable<SELF : PostgreSqlSelectAppendable<SELF>> : PostgreSqlAppendable<SELF>,
    PostgreSqlParenthesisGroupAppendable<SELF>, PostgreSqlSelectKeywordAppendable<SELF>,
    PostgreSqlAllKeywordAppendable<SELF>, PostgreSqlIntoKeywordAppendable<SELF>,
    PostgreSqlStrictKeywordAppendable<SELF>,