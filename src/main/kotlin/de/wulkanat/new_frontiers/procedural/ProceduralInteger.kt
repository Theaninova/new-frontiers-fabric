package de.wulkanat.new_frontiers.procedural

import kotlin.math.*
import kotlin.random.Random
import kotlin.random.nextInt

fun Random.nextProceduralInt(range: IntRange, distribution: (Double) -> Double = Distributions.linear()): Int {
    return this.nextInt(range).distribute(range.first.toDouble()..range.last.toDouble(), distribution).roundToInt()
}

fun Random.nextProceduralDouble(
    range: ClosedRange<Double>,
    distribution: (Double) -> Double = Distributions.linear()
): Double {
    return this.nextDouble(range.start, range.endInclusive).distribute(range, distribution)
}

fun Random.nextProceduralFloat(
    range: ClosedRange<Double>,
    distribution: (Double) -> Double = Distributions.linear()
): Float {
    return nextProceduralDouble(range, distribution).toFloat()
}

fun Random.nextSeed(): Long {
    return this.nextLong()
}

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

const val TWO_PI = 2 * PI

fun Random.normalDistributedDouble(
    range: ClosedRange<Double> = 0.0..1.0,
    expectation: Double = 0.5,
    variance: Double = 0.1
): Double {
    val u1 = nextDouble()
    val u2 = nextDouble()

    return (expectation + variance * sqrt(-2 * ln(u1)) * cos(TWO_PI * u2)).deNormalize(range).clamp(range)
}

fun Random.normalDistributedFloat(
    range: ClosedRange<Double> = 0.0..1.0,
    expectation: Double = 0.5,
    variance: Double = 0.2
): Float {
    return normalDistributedDouble(range, expectation, variance).toFloat()
}

fun Random.normalDistributedInt(range: IntRange, expectation: Double, variance: Double): Int {
    return normalDistributedDouble(range.first.toDouble()..range.last.toDouble(), expectation, variance).roundToInt()
}

fun Random.nextBoolean(probability: Double): Boolean {
    return nextDouble() <= probability
}

fun Random.runRandomly(probability: Double, function: () -> Unit): Boolean {
    val run = nextBoolean(probability)
    if (run) function()
    return run
}

fun Number.distribute(range: ClosedRange<Double>, distribution: (Double) -> Double): Double {
    return tempNormalized(range, distribution)
}

val EASE_IN = Distributions.Easing(ein = true, eout = false)
val EASE_OUT = Distributions.Easing(ein = false, eout = true)
val EASE_IN_OUT = Distributions.Easing(ein = true, eout = false)

object Distributions {
    private const val PI_HALF = PI / 2
    private const val PI_THREE_HALF = (PI / 2) * 3

    data class Easing(val ein: Boolean, val eout: Boolean)

    fun linear(): (Double) -> Double = { value -> value }

    // TODO
    fun polynomial(exponent: Double): (Double) -> Double = { it.pow(exponent) }

    /**
     * From 0 to 1 in a sin shape
     *
     * cos(x * (PI/2))
     */
    fun sinusial(easing: Easing = EASE_IN_OUT): (Double) -> Double = {
        when (easing) {
            EASE_IN_OUT -> -cos(it * PI) / 2 + 0.5
            EASE_OUT -> sin(it * PI_HALF)
            EASE_IN -> sin((it * PI_HALF + PI_THREE_HALF)) / 2 + 0.5
            else -> 0.0 // never
        }
    }

    /**
     * Type in this in Google to get a visual representation of the distribution
     *
     * e^(-(x - peakPos)^2 / (2 * standardDeviation^2))
     *
     * The default values are probably fine for most situations
     */
    fun gaussian(standardDeviation: Double = 0.1, peakPos: Double = 0.5): (Double) -> Double =
        { E.pow(-(it - peakPos).pow(2) / (2 * standardDeviation.pow(2))) }
}