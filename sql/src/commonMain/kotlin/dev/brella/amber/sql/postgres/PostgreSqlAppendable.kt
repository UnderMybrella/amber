package dev.brella.amber.sql.postgres

import dev.brella.amber.sql.SqlAppendable
import dev.brella.amber.sql.SqlCodeGenSettings

public interface PostgreSqlAppendable<SELF : PostgreSqlAppendable<SELF>> : SqlAppendable<SELF> {
    override val settings: PostgreSqlCodeGenSettings
}