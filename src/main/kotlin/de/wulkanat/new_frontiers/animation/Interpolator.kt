package de.wulkanat.new_frontiers.animation

import kotlin.math.*

object Interpolator {
    val EASE_IN = Easing(ein = true, eout = false)
    val EASE_OUT = Easing(ein = false, eout = true)
    val EASE_IN_OUT = Easing(ein = true, eout = false)

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