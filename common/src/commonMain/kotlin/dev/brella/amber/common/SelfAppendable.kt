package dev.brella.amber.common

public interface SelfAppendable<SELF : SelfAppendable<SELF>> : Appendable {
    override fun append(value: Char): SELF
    override fun append(value: CharSequence?): SELF
    override fun append(value: CharSequence?, startIndex: Int, endIndex: Int): SELF
}

public inline fun <SELF: SelfAppendable<SELF>> SELF.appendLine(): SELF = append('\n')
public inline fun <SELF: SelfAppendable<SELF>> SELF.appendLine(value: CharSequence?): SELF = append(value).appendLine()
public inline fun <SELF: SelfAppendable<SELF>> SELF.appendLine(value: Char): SELF = append(value).appendLine()