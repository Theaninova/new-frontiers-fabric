package de.wulkanat.new_frontiers

import kotlin.random.Random
import kotlin.random.nextInt

val consonantGroups = arrayOf(
    "b", "c", "d", "f", "g", "h", "j", "k", "l", "m", "n", "p", "q", "r", "s", "t", "v", "w", "x", "y", "z",
    "cl", "fl", "gl", "kl", "pl", "ql", "sl", "tl", "xl",
    "st", "sc",
    "tr",
    "ch", "chl", "chr"
)

val vocalGroups = arrayOf(
    "a", "e", "i", "o", "u", "ae", "ea", "ou", "io", "oi", "ie", "ei", "y", "ia"
)

val suffixes = arrayOf(
    "ite", "um"
)

val prefixes = arrayOf(
    "di", "tri", "mono", "Hexa", "Penta"
)

fun generateName(groups: Int, prefix: Boolean, suffix: Boolean, random: Random): String {
    val out = StringBuilder()

    if (prefix) out.append(prefixes.randomElement(random))

    if (random.nextBoolean()) out.append(vocalGroups.randomElement(random))

    for (i in 0 until groups) {
        out.append(consonantGroups.randomElement(random))
        out.append(vocalGroups.randomElement(random))
    }

    if (random.nextBoolean()) out.append(consonantGroups.randomElement(random))

    if (suffix) out.append(suffixes.randomElement(random))

    out[0] = out[0].toUpperCase()

    return out.toString()
}

fun <T> Array<T>.randomElement(random: Random): T {
    return get(random.nextInt(0 until size))
}