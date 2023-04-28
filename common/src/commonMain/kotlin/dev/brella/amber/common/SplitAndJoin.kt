package dev.brella.amber.common

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract
import kotlin.math.max

public fun CharSequence.splitAndJoin(
    delimiter: String,
    ignoreCase: Boolean = false,
    splitLimit: Int = 0,
    separator: CharSequence = delimiter,
    prefix: CharSequence = "",
    postfix: CharSequence = "",
    joinLimit: Int = -1,
    truncated: CharSequence = "...",
    transform: (String) -> String,
): String = splitAndJoinTo(
    StringBuilder(),
    delimiter,
    ignoreCase,
    splitLimit,
    separator,
    prefix,
    postfix,
    joinLimit,
    truncated,
    transform
).toString()

public fun <A : Appendable> CharSequence.splitAndJoinTo(
    buffer: A,
    delimiter: String,
    ignoreCase: Boolean = false,
    splitLimit: Int = 0,
    separator: CharSequence = delimiter,
    prefix: CharSequence = "",
    postfix: CharSequence = "",
    joinLimit: Int = -1,
    truncated: CharSequence = "...",
    transform: (String) -> String,
): A {
    require(splitLimit >= 0) { "Limit must be non-negative, but was $splitLimit" }
    buffer.append(prefix)

    var currentOffset = 0
    var count = 0

    var nextIndex = indexOf(delimiter, currentOffset)
    if (nextIndex == -1 || splitLimit == 1) {
        buffer.append(this)
    } else {
        val isLimited = splitLimit > 0
        do {
            if (++count > 1) buffer.append(separator)
            if (joinLimit < 0 || count <= joinLimit) {
                buffer.append(transform(substring(currentOffset, nextIndex)))
            } else break

            currentOffset = nextIndex + delimiter.length
            // Do not search for next occurrence if we're reaching limit
            if (isLimited && count == splitLimit - 1) break
            nextIndex = indexOf(delimiter, currentOffset, ignoreCase)
        } while (nextIndex != -1)

        if (++count > 1) buffer.append(separator)
        if (joinLimit < 0 || count <= joinLimit) {
            buffer.append(transform(substring(currentOffset, length)))
        }

        if (joinLimit >= 0 && count < joinLimit) buffer.append(truncated)
    }

    buffer.append(postfix)
    return buffer
}

public fun CharSequence.splitAndJoin(
    delimiters: Map<String, String>,
    ignoreCase: Boolean = false,
    splitLimit: Int = 0,
    prefix: CharSequence = "",
    postfix: CharSequence = "",
    joinLimit: Int = -1,
    truncated: CharSequence = "...",
    transform: (String) -> String,
): String = splitAndJoinTo(
    StringBuilder(),
    delimiters,
    ignoreCase,
    splitLimit,
    prefix,
    postfix,
    joinLimit,
    truncated,
    transform
).toString()

public fun CharSequence.splitAndJoin(
    vararg delimiters: Pair<String, String>,
    ignoreCase: Boolean = false,
    splitLimit: Int = 0,
    prefix: CharSequence = "",
    postfix: CharSequence = "",
    joinLimit: Int = -1,
    truncated: CharSequence = "...",
    transform: (String) -> String,
): String = splitAndJoinTo(
    StringBuilder(),
    delimiters.toMap(),
    ignoreCase,
    splitLimit,
    prefix,
    postfix,
    joinLimit,
    truncated,
    transform
).toString()

public fun CharSequence.splitAndJoin(
    vararg delimiters: String,
    ignoreCase: Boolean = false,
    splitLimit: Int = 0,
    prefix: CharSequence = "",
    postfix: CharSequence = "",
    joinLimit: Int = -1,
    truncated: CharSequence = "...",
    transform: (String) -> String,
): String = splitAndJoinTo(
    StringBuilder(),
    delimiters.associateBy(String::toString),
    ignoreCase,
    splitLimit,
    prefix,
    postfix,
    joinLimit,
    truncated,
    transform
).toString()

public fun <A : Appendable> CharSequence.splitAndJoinTo(
    buffer: A,
    delimiters: Map<String, String>,
    ignoreCase: Boolean = false,
    splitLimit: Int = 0,
    prefix: CharSequence = "",
    postfix: CharSequence = "",
    joinLimit: Int = -1,
    truncated: CharSequence = "...",
    transform: (String) -> String,
): A {
    require(splitLimit >= 0) { "Limit must be non-negative, but was $splitLimit" }
    buffer.append(prefix)

    var currentOffset = 0
    var count = 0

    var kv: Map.Entry<String, String>
    var nextIndex: Int

    indexOf(delimiters, currentOffset, ignoreCase) { entry, i ->
        kv = entry
        nextIndex = i
    }

    if (nextIndex == -1 || splitLimit == 1) {
        buffer.append(this)
    } else {
        val isLimited = splitLimit > 0
        do {
            if (++count > 1) buffer.append(kv.value)
            if (joinLimit < 0 || count <= joinLimit) {
                buffer.append(transform(substring(currentOffset, nextIndex)))
            } else break

            currentOffset = nextIndex + kv.key.length
            // Do not search for next occurrence if we're reaching limit
            if (isLimited && count == splitLimit - 1) break
            indexOf(delimiters, currentOffset, ignoreCase) { entry, i ->
                kv = entry
                nextIndex = i
            }
        } while (nextIndex != -1)

        if (++count > 1) buffer.append(kv.value)
        if (joinLimit < 0 || count <= joinLimit) {
            buffer.append(transform(substring(currentOffset, length)))
        }

        if (joinLimit >= 0 && count < joinLimit) buffer.append(truncated)
    }

    buffer.append(postfix)
    return buffer
}

@OptIn(ExperimentalContracts::class)
private inline fun CharSequence.indexOf(
    delimiters: Map<String, String>,
    currentOffset: Int,
    ignoreCase: Boolean = false,
    block: (kv: Map.Entry<String, String>, index: Int) -> Unit,
) {
    contract {
        callsInPlace(block, kotlin.contracts.InvocationKind.EXACTLY_ONCE)
    }

    val iterator = delimiters.iterator()
    var minEntry: Map.Entry<String, String> = iterator.next()
    var minValue: Int = indexOf(minEntry.key, currentOffset, ignoreCase)
    while (iterator.hasNext()) {
        val k = iterator.next()
        val v = indexOf(k.key, currentOffset, ignoreCase)
        if (minValue == -1 || (v != -1 && v < minValue)) {
            minEntry = k
            minValue = v
        }
    }

    block(minEntry, minValue)
}

public fun String.titlecase(): String =
    lowercase().replaceFirstChar(Char::titlecase)