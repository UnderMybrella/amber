package dev.brella.amber.sql

import dev.brella.amber.common.SelfAppendable

@SqlStringDsl
public interface SqlAppendable<SELF : SqlAppendable<SELF>> : SelfAppendable<SELF> {
    public val settings: SqlCodeGenSettings

    public fun appendAutoSpaced(value: Char): SELF =
        settings.autoSpacer.let { if (it == null) append(value) else append(value).append(it) }

    public fun appendAutoSpacedPrefixed(value: Char): SELF =
        settings.autoSpacer.let { if (it == null) append(value) else append(it).append(value) }

    public fun appendAutoSpacedSurround(value: Char): SELF =
        settings.autoSpacer.let { if (it == null) append(value) else append(it).append(value).append(it) }

    public fun appendAutoSpaced(value: CharSequence?): SELF =
        settings.autoSpacer.let { if (it == null) append(value) else append(value).append(it) }

    public fun appendAutoSpacedPrefixed(value: CharSequence?): SELF =
        settings.autoSpacer.let { if (it == null) append(value) else append(it).append(value) }

    public fun appendAutoSpacedSurround(value: CharSequence?): SELF =
        settings.autoSpacer.let { if (it == null) append(value) else append(it).append(value).append(it) }

    public fun appendAutoSeparated(value: CharSequence?): SELF =
        settings.autoSeparator.let { if (it == null) append(value) else append(value).append(it) }
}

@SqlStringDsl
public inline fun <SELF : SqlAppendable<*>> SELF.appendAutoSpaced(block: SELF.() -> Unit): SELF =
    settings.autoSpacer.let { autoSpacer ->
        try {
            this.block()
            this
        } finally {
            if (autoSpacer != null) append(autoSpacer)
        }
    }

@SqlStringDsl
public inline fun <SELF : SqlAppendable<*>> SELF.appendAutoSeparated(block: SELF.() -> Unit): SELF =
    settings.autoSeparator.let { autoSeparator ->
        try {
            this.block()
            this
        } finally {
            if (autoSeparator != null) append(autoSeparator)
        }
    }