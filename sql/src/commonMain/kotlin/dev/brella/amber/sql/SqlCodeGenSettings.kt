package dev.brella.amber.sql

import dev.brella.amber.common.CodeGenSettings
import kotlin.native.concurrent.ThreadLocal

public interface SqlCodeGenSettings : CodeGenSettings {
    public val indent: String

    public val autoSpacer: String?
    public val autoSeparator: String?

    public interface Mutable : SqlCodeGenSettings, CodeGenSettings.Mutable {
        public override var indent: String
        public override var autoSpacer: String?
        public override var autoSeparator: String?
    }

    @ThreadLocal
    public companion object : SqlCodeGenSettings.Mutable {
        override var indent: String = "    "
        override var autoSpacer: String? = " "
        override var autoSeparator: String? = ", "
    }
}