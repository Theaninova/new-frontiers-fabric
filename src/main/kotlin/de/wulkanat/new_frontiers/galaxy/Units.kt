package de.wulkanat.new_frontiers.galaxy

fun lyToLs(ly: Int): Int {
    return ly / 31_557_600
}

fun lsToLy(ls: Int): Int {
    return ls * 31_557_600
}

fun lsToMm(ls: Int): Int {
    // not exact, we just do some rounding
    return ls / 300
}

fun mmToLs(mm: Int): Int {
    return mm * 300
}
