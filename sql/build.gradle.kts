import com.diffplug.gradle.spotless.SpotlessExtension
import dev.brella.amber.kotlin.KotlinStringWriter
import dev.brella.amber.kotlin.import
import dev.brella.amber.kotlin.string.parser.general.parenthesisGroup
import dev.brella.amber.plugin.KotlinCodeGenTask
import dev.brella.amber.plugin.codeGenerationContainer
import dev.brella.amber.plugin.registerKotlinCodeGenTask
import dev.brella.amber.plugin.runSpotlessApplyAfterGeneration
import dev.brella.kornea.gradle.korneaBaseModule
import groovy.json.JsonSlurper
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

plugins {
    kotlin("multiplatform")
    id("dev.brella.amber")
    id("com.diffplug.spotless")
}

group = "dev.brella"
version = "1.0.0"

repositories {
    mavenCentral()
}

val commonMainResources =
    kotlin.sourceSets.commonMain
        .map(KotlinSourceSet::resources)
        .map(SourceDirectorySet::getSrcDirs)
        .map(Set<File>::first)

val commonGenerated = codeGenerationContainer("commonGenerated") {
    val postgresqlKeywordsFile = commonMainResources.map { File(it, "postgresql_keywords.json") }
    val postgresqlKeywordOverridesFile = commonMainResources.map { File(it, "postgresql_keyword_overrides.json") }

    @Suppress("UNCHECKED_CAST")
    val postgresqlKeywords = postgresqlKeywordsFile.map { file -> JsonSlurper().parse(file) as List<List<String>> }

    @Suppress("UNCHECKED_CAST")
    val postgresqlKeywordOverrides =
        postgresqlKeywordOverridesFile.map { file -> JsonSlurper().parse(file) as Map<String, List<String>> }

    val generateEnumPostgreSqlKeywords =
        registerKotlinCodeGenTask("generateEnumPostgreSqlKeywords") {
            group = "postgresql"
            dataset.add("")
            inputs.file(postgresqlKeywordsFile)

            generate("dev.brella.amber.sql.postgres.string.lexer.keywords", "EnumPostgreSqlKeyword") {
                generateEnumPostgreSqlKeyword(postgresqlKeywords.get())
            }
        }

    registerKotlinCodeGenTask<List<List<String>>>("generatePostgreSqlKeywordAppendables") {
        group = "postgresql"
        dataset.addAll(postgresqlKeywords.zip(postgresqlKeywordOverrides) { keywordList, overrideMap ->
            keywordList.map { list ->
                listOf(list, overrideMap[list.first()] ?: emptyList())
            }
        })

        inputs.file(postgresqlKeywordsFile)
        inputs.file(postgresqlKeywordOverridesFile)
        dependsOn(generateEnumPostgreSqlKeywords)

        filePackage { "dev.brella.amber.sql.postgres.string.lexer.keywords" }
        fileName { (list, overrides) ->
            val fileName = overrides.getOrNull(0) ?: run {
                list.first()
                    .replace("\u200B", "")
                    .split(" ", "_", "-")
                    .joinToString("") { it.lowercase().replaceFirstChar(Char::uppercaseChar) }
            }

            "PostgreSql${fileName}KeywordAppendable"
        }

        generate { (list, overrides) ->
            generatePostgreSqlKeywordAppendable(list.first(), overrides)
        }
    }

    runSpotlessApplyAfterGeneration()
}

configure<SpotlessExtension> {
    kotlin {
        target(commonGenerated.source)
        ktlint("0.48.2")
    }
}

kotlin {
    jvm {
        jvmToolchain(8)
        withJava()
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }
    js(IR) {
        browser {
            commonWebpackConfig {
                cssSupport {
                    enabled.set(true)
                }
            }
        }
    }
    val hostOs = System.getProperty("os.name")
    val isMingwX64 = hostOs.startsWith("Windows")
    val nativeTarget = when {
        hostOs == "Mac OS X" -> macosX64("native")
        hostOs == "Linux" -> linuxX64("native")
        isMingwX64 -> mingwX64("native")
        else -> throw GradleException("Host OS is not supported in Kotlin/Native.")
    }

    sourceSets {
        val commonMain by getting {
            kotlin.source(commonGenerated.source)

            dependencies {
                api(project(":amber-common"))
                implementation(korneaBaseModule())
            }
        }
        val commonTest by getting {
            dependencies {

                implementation(kotlin("test"))
            }
        }
        val jvmMain by getting
        val jvmTest by getting
        val jsMain by getting
        val jsTest by getting
        val nativeMain by getting
        val nativeTest by getting

        all {
            languageSettings.apply {
                optIn("kotlin.RequiresOptIn")
                enableLanguageFeature("ContextReceivers")
                explicitApi()
            }
        }
    }
}

dependencies {
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

fun KotlinStringWriter.generateEnumPostgreSqlKeyword(keywords: List<List<String>>) {
    import("dev.brella.amber.common.EnumCaseConvention")
    import("dev.brella.amber.sql.postgres.string.lexer.keywords.PostgreSqlKeywordStatus.Companion.nonReserved")
    import("dev.brella.amber.sql.postgres.string.lexer.keywords.PostgreSqlKeywordStatus.Companion.nonReservedRequiresAs")
    import("dev.brella.amber.sql.postgres.string.lexer.keywords.PostgreSqlKeywordStatus.Companion.nonReservedCannotBeFunctionOrType")
    import("dev.brella.amber.sql.postgres.string.lexer.keywords.PostgreSqlKeywordStatus.Companion.nonReservedCannotBeFunctionOrTypeRequiresAs")
    import("dev.brella.amber.sql.postgres.string.lexer.keywords.PostgreSqlKeywordStatus.Companion.reserved")
    import("dev.brella.amber.sql.postgres.string.lexer.keywords.PostgreSqlKeywordStatus.Companion.reservedRequiresAs")
    import("dev.brella.amber.sql.postgres.string.lexer.keywords.PostgreSqlKeywordStatus.Companion.reservedCanBeFunctionOrType")
    import("dev.brella.amber.sql.postgres.string.lexer.keywords.PostgreSqlKeywordStatus.Companion.reservedCanBeFunctionOrTypeRequiresAs")
    appendLine()

    appendLine("public enum class EnumPostgreSqlKeyword(")
    appendIndent(1).appendLine("public val postgreSqlStatus: PostgreSqlKeywordStatus? = null,")
    appendIndent(1).appendLine("public val sql2016Status: PostgreSqlKeywordStatus? = null,")
    appendIndent(1).appendLine("public val sql2011Status: PostgreSqlKeywordStatus? = null,")
    appendIndent(1).appendLine("public val sql92Status: PostgreSqlKeywordStatus? = null,")
    appendIndent(1).appendLine("keyword: String? = null,")
    appendLine(") {")
    fun parseStatus(status: String): String? =
        status.replace("non-reserved", "nonReserved")
            .replace(" (can be function or type)", "CanBeFunctionOrType")
            .replace(" (cannot be function or type)", "CannotBeFunctionOrType")
            .replace(", requires AS", "RequiresAs")
            .replace("\u200B", "")
            .replace(" ", "")
            .takeIf(kotlin.String::isNotEmpty)
    keywords.fold(keywords[0][0][0].uppercaseChar()) { lastChar, (keyword, psql, sql2016, sql2011, sql92) ->
        val keyword = keyword.replace("\u200B", "")
        val enumName = keyword.uppercase().replace('-', '_')
        val firstChar = enumName.first()

        if (lastChar != firstChar) appendLine()

        appendIndent(1)
        append(enumName)
        append('(')
        parseStatus(psql)
            ?.let(this::append)
            ?.append("()") ?: append("null")
        append(", ")

        parseStatus(sql2016)
            ?.let(this::append)
            ?.append("()") ?: append("null")
        append(", ")

        parseStatus(sql2011)
            ?.let(this::append)
            ?.append("()") ?: append("null")
        append(", ")

        parseStatus(sql92)
            ?.let(this::append)
            ?.append("()") ?: append("null")

        if (enumName != keyword) {
            append(", \"")
            append(keyword)
            append("\"")
        }

        appendLine("),")
        firstChar
    }

    appendLine()
    appendIndent(1).appendLine(";")
    appendLine()

    appendIndent(1).appendLine("private val cases = EnumCaseConvention.transformLazy(keyword ?: name)")
    appendLine()
    appendIndent(1).appendLine("public infix fun stringFor(convention: EnumCaseConvention): String = ")
    appendIndent(2).appendLine("cases[convention.ordinal]")

    appendLine("}")
}

fun KotlinStringWriter.generatePostgreSqlKeywordAppendable(keyword: String, overrides: List<String>) {
    val keyword = keyword.replace("\u200B", "")
    val enumName = keyword.uppercase().replace('-', '_')
    val pascalName = overrides.getOrNull(0) ?: (keyword.split(" ", "_", "-")
        .joinToString("") { it.lowercase().replaceFirstChar(Char::uppercaseChar) })

    val camelName = overrides.getOrNull(1) ?: pascalName.replaceFirstChar(Char::lowercaseChar)

    println("Generating $pascalName...")

    append("public interface PostgreSql")
    append(pascalName)
    append("KeywordAppendable<SELF : PostgreSql")
    append(pascalName)
    appendLine("KeywordAppendable<SELF>> : PostgreSqlKeywordAppendable<SELF> {")
    appendIndent(1)
        .append("public fun append")
        .append(pascalName)
        .append("(): SELF = appendKeyword(EnumPostgreSqlKeyword.")
        .append(enumName)
        .appendLine(")")
    appendLine("}")
    appendLine()

    append("public inline fun <SELF : PostgreSql")
        .append(pascalName)
        .append("KeywordAppendable<*>> SELF.`")
        .append(camelName)
        .appendLine("`(): SELF {")

    appendIndent(1)
        .append("append")
        .append(pascalName)
        .appendLine("()")

    appendIndent(1)
        .appendLine("return this")
    appendLine("}")
}