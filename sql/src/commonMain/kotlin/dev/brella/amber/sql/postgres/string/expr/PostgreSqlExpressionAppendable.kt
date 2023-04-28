package dev.brella.amber.sql.postgres.string.expr

import dev.brella.amber.sql.postgres.PostgreSqlAppendable
import dev.brella.amber.sql.postgres.string.general.*
import dev.brella.amber.sql.postgres.string.lexer.keywords.*
import dev.brella.amber.sql.postgres.string.lexer.tokens.PostgreSqlGreaterThanAppendable
import dev.brella.amber.sql.postgres.string.lexer.tokens.PostgreSqlLeftShiftAppendable
import dev.brella.amber.sql.postgres.string.lexer.tokens.PostgreSqlLessThanAppendable
import dev.brella.amber.sql.postgres.string.lexer.tokens.PostgreSqlRightShiftAppendable

public interface PostgreSqlAExprAccessor {
    public val aexpr: PostgreSqlAExprQualAppendable<*>
}

public interface PostgreSqlAExprQualAppendable<SELF : PostgreSqlAExprQualAppendable<SELF>> : PostgreSqlAppendable<SELF>,
    PostgreSqlAExprBitShiftAppendable<SELF>, PostgreSqlAExprAccessor

public interface PostgreSqlAExprBitShiftAppendable<SELF : PostgreSqlAExprBitShiftAppendable<SELF>> :
    PostgreSqlAppendable<SELF>, PostgreSqlLeftShiftAppendable<SELF>, PostgreSqlRightShiftAppendable<SELF>,
    PostgreSqlAExprORAppendable<SELF>

public interface PostgreSqlAExprORAppendable<SELF : PostgreSqlAExprORAppendable<SELF>> : PostgreSqlAppendable<SELF>,
    PostgreSqlOrKeywordAppendable<SELF>, PostgreSqlAExprANDAppendable<SELF>

public interface PostgreSqlAExprANDAppendable<SELF : PostgreSqlAExprANDAppendable<SELF>> : PostgreSqlAppendable<SELF>,
    PostgreSqlAndKeywordAppendable<SELF>, PostgreSqlAExprBetweenAppendable<SELF>

public interface PostgreSqlAExprBetweenAppendable<SELF : PostgreSqlAExprBetweenAppendable<SELF>> :
    PostgreSqlAppendable<SELF>, PostgreSqlNotKeywordAppendable<SELF>, PostgreSqlBetweenKeywordAppendable<SELF>,
    PostgreSqlSymmetricKeywordAppendable<SELF>, PostgreSqlAndKeywordAppendable<SELF>, PostgreSqlAExprInAppendable<SELF>

public interface PostgreSqlAExprInAppendable<SELF : PostgreSqlAExprInAppendable<SELF>> : PostgreSqlAppendable<SELF>,
    PostgreSqlNotKeywordAppendable<SELF>, PostgreSqlInKeywordAppendable<SELF>, PostgreSqlAExprUnaryNotAppendable<SELF>

public interface PostgreSqlAExprUnaryNotAppendable<SELF : PostgreSqlAExprUnaryNotAppendable<SELF>> :
    PostgreSqlAppendable<SELF>, PostgreSqlNotKeywordAppendable<SELF>, PostgreSqlAExprIsNullAppendable<SELF>

public interface PostgreSqlAExprIsNullAppendable<SELF : PostgreSqlAExprIsNullAppendable<SELF>> :
    PostgreSqlAppendable<SELF>, PostgreSqlIsNullKeywordAppendable<SELF>, PostgreSqlNotNullKeywordAppendable<SELF>,
    PostgreSqlAExprIsNotAppendable<SELF>

public interface PostgreSqlAExprIsNotAppendable<SELF : PostgreSqlAExprIsNotAppendable<SELF>> :
    PostgreSqlAppendable<SELF>, PostgreSqlIsKeywordAppendable<SELF>, PostgreSqlNotKeywordAppendable<SELF>,
    PostgreSqlNullKeywordAppendable<SELF>, PostgreSqlTrueKeywordAppendable<SELF>,
    PostgreSqlFalseKeywordAppendable<SELF>, PostgreSqlUnknownKeywordAppendable<SELF>,
    PostgreSqlDistinctKeywordAppendable<SELF>, PostgreSqlFromKeywordAppendable<SELF>,
    PostgreSqlAExprAccessor, PostgreSqlOfKeywordAppendable<SELF>, PostgreSqlParenthesisGroupAppendable<SELF>,
    PostgreSqlDocumentKeywordAppendable<SELF>, PostgreSqlNormalizedKeywordAppendable<SELF>

public interface PostgreSqlAExprCompareAppendable<SELF : PostgreSqlAExprCompareAppendable<SELF>> :
    PostgreSqlAppendable<SELF>, PostgreSqlLessThanAppendable<SELF>, PostgreSqlGreaterThanAppendable<SELF>