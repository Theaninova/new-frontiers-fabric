package de.wulkanat.new_frontiers.extensions.kotlin.random

import de.wulkanat.new_frontiers.extensions.kotlin.math.clamp
import de.wulkanat.new_frontiers.extensions.kotlin.math.deNormalize
import kotlin.math.*
import kotlin.random.Random

private const val TWO_PI = 2 * PI

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