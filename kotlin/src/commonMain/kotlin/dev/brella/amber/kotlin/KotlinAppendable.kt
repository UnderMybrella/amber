package dev.brella.amber.kotlin

import dev.brella.amber.common.SelfAppendable

@KotlinStringDsl
public interface KotlinAppendable<SELF : KotlinAppendable<SELF>> : SelfAppendable<SELF> {
    public val settings: KotlinCodeGenSettings

    public fun appendAutoSpaced(value: Char): SELF =
        settings.autoSpacer.let { if (it == null) append(value) else append(value).append(it) }

    public fun appendAutoSpaced(value: CharSequence?): SELF =
        settings.autoSpacer.let { if (it == null) append(value) else append(value).append(it) }

    public fun appendAutoSeparated(value: CharSequence?): SELF =
        settings.autoSeparator.let { if (it == null) append(value) else append(value).append(it) }
}

public inline fun <SELF : KotlinAppendable<SELF>> SELF.appendAutoSpaced(block: SELF.() -> Unit): SELF =
    settings.autoSpacer.let { autoSpacer ->
        try {
            this.block()
            this
        } finally {
            if (autoSpacer != null) append(autoSpacer)
        }
    }

public inline fun <SELF : KotlinAppendable<SELF>> SELF.appendAutoSeparated(block: SELF.() -> Unit): SELF =
    settings.autoSeparator.let { autoSeparator ->
        try {
            this.block()
            this
        } finally {
            if (autoSeparator != null) append(autoSeparator)
        }
    }