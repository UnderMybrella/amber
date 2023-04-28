package dev.brella.amber.sql.postgres.string.parser

import dev.brella.amber.sql.postgres.PostgreSqlAppendable
import dev.brella.amber.sql.postgres.string.lexer.tokens.PostgreSqlDotAppendable
import dev.brella.amber.sql.postgres.string.general.PostgreSqlParenthesisGroupAppendable
import dev.brella.amber.sql.postgres.string.lexer.tokens.PostgreSqlStarAppendable

public interface PostgreSqlIndirectionAppendable<SELF : PostgreSqlIndirectionAppendable<SELF>> :
    PostgreSqlAppendable<SELF>, PostgreSqlDotAppendable<SELF>, PostgreSqlStarAppendable<SELF>,
    PostgreSqlParenthesisGroupAppendable<SELF> {
}