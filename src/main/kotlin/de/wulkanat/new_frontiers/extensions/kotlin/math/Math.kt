package de.wulkanat.new_frontiers.extensions.kotlin.math

import kotlin.math.abs

fun ClosedRange<Double>.range(): Double {
    return abs(this.start - this.endInclusive)
}

fun Number.normalize(range: ClosedRange<Double>): Double {
    return (this.toDouble() - range.start) / range.range()
}

fun Number.deNormalize(range: ClosedRange<Double>): Double {
    return this.toDouble() * range.range() + range.start
}

fun Double.clamp(range: ClosedRange<Double>): Double {
    return if (this < range.start) range.start else if (this > range.endInclusive) range.endInclusive else this
}

private inline fun Number.tempNormalized(range: ClosedRange<Double>, func: (Double) -> Double): Double {
    return func(this.normalize(range)).deNormalize(range)
}