package dev.brella.amber.kotlin

import dev.brella.amber.common.CodeGenSettings
import kotlin.native.concurrent.ThreadLocal

public interface KotlinCodeGenSettings : CodeGenSettings {
    public val indent: String

    public val autoSpacer: String?
    public val autoSeparator: String?

    public interface Mutable : KotlinCodeGenSettings, CodeGenSettings.Mutable {
        public override var indent: String
        public override var autoSpacer: String?
        public override var autoSeparator: String?
    }

    @ThreadLocal
    public companion object : KotlinCodeGenSettings.Mutable {
        override var indent: String = "    "
        override var autoSpacer: String? = " "
        override var autoSeparator: String? = ", "
    }
}