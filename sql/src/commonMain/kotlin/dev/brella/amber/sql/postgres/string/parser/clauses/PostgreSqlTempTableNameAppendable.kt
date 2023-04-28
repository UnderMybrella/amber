package dev.brella.amber.sql.postgres.string.parser.clauses

import dev.brella.amber.sql.postgres.PostgreSqlAppendable
import dev.brella.amber.sql.postgres.string.lexer.keywords.*

public interface PostgreSqlTempTableNameAppendable<SELF : PostgreSqlTempTableNameAppendable<SELF>> :
    PostgreSqlAppendable<SELF>, PostgreSqlLocalKeywordAppendable<SELF>, PostgreSqlGlobalKeywordAppendable<SELF>,
    PostgreSqlTemporaryKeywordAppendable<SELF>, PostgreSqlTempKeywordAppendable<SELF>,
    PostgreSqlTableKeywordAppendable<SELF>, PostgreSqlUnloggedKeywordAppendable<SELF> {
}