package dev.brella.amber.sql.postgres

import dev.brella.amber.common.EnumCaseConvention
import dev.brella.amber.sql.SqlCodeGenSettings
import dev.brella.amber.sql.postgres.string.lexer.keywords.EnumPostgreSqlKeyword
import kotlin.native.concurrent.ThreadLocal

public interface PostgreSqlCodeGenSettings : SqlCodeGenSettings {
    public val keywordConvention: EnumCaseConvention

    public interface Mutable : PostgreSqlCodeGenSettings, SqlCodeGenSettings.Mutable {
        override var keywordConvention: EnumCaseConvention
    }

    @ThreadLocal
    public companion object : PostgreSqlCodeGenSettings.Mutable {
        override var indent: String = "    "
        override var autoSpacer: String? = " "
        override var autoSeparator: String? = ", "
        override var keywordConvention: EnumCaseConvention = EnumCaseConvention.UPPERCASE
    }
}

public inline infix fun PostgreSqlCodeGenSettings.stringFor(keyword: EnumPostgreSqlKeyword): String =
    keyword stringFor keywordConvention