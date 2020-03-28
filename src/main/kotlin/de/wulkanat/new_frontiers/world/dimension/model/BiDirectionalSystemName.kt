package de.wulkanat.new_frontiers.world.dimension.model

import kotlin.math.roundToInt
import kotlin.random.Random
import kotlin.random.nextInt

class BiDirectionalNameSystem(val diameter: Long, val height: Long) {
    init {
        val systemFormat = "AA-G Ms"
        val quadrantFormat = "A-G NN"

        val totalNames = getTotalCombinations(systemFormat) * getTotalCombinations(quadrantFormat)
        val totalPoints = diameter * diameter * height

        if (totalPoints > totalNames) {
            throw Error("Star System is too big for Naming Scheme to be BiDirectional")
        }
    }

    val quadrantWidth = diameter / 1000
    val quadrantHeight = height / alphabet.size
    val quadrantHeightSubdivHeight = quadrantHeight / greekAlphabet.size

    fun generateSystemName(seed: Int): String {
        val random = Random(seed)
        return "${greekAlphabet.random(random).capitalize()} ${greekAlphabet.random(random)
            .capitalize()} ${minecraftMythicalCreatures.random(random)
            .capitalize()}${suffixes.random(random)} / ${alphabet.random(random).toUpperCase()}-${greekAlphabet.random(
            random
        ).capitalize()} ${random.nextInt(0..999)}-${random.nextInt(0..999)}"
    }

    fun quadrantName(galacticPosition: GalacticPosition): String {
        val x = galacticPosition.x_ly + diameter / 2
        val y = galacticPosition.y_ly + diameter / 2
        val z = galacticPosition.z_ly + height / 2

        val xIndex = ((x.toDouble() / diameter.toDouble()) * 1000.0).roundToInt()
        val yIndex = ((y.toDouble() / diameter.toDouble()) * 1000.0).roundToInt()
        val zAlphabet = alphabet[((z.toDouble() / height.toDouble()) * alphabet.size).roundToInt()]
        val zGreek = greekAlphabet[((z.toDouble() % height.toDouble()) * greekAlphabet.size).roundToInt()]

        return "${zAlphabet.toUpperCase()}-${zGreek.capitalize()} $xIndex-$yIndex"
    }

    fun decodeQuadrantName(name: String): GalacticPosition {
        val strings = name.split(",", " ")

        val xIndex = Integer.parseInt(strings[3])
        val yIndex = Integer.parseInt(strings[2])
        val zAlphabet = alphabet.indexOf(strings[1].toLowerCase())
        val zGreek = alphabet.indexOf(strings[0].toLowerCase())

        val x = (xIndex.toDouble() / 1000.0) * diameter.toDouble()
        val y = (yIndex.toDouble() / 1000.0) * diameter.toDouble()
        val z = (zAlphabet.toDouble() / alphabet.size.toDouble()) * height.toDouble() +
                (zAlphabet.toDouble() / greekAlphabet.size.toDouble()) * (height.toDouble() / alphabet.size.toDouble())

        return GalacticPosition(x.roundToInt(), y.roundToInt(), z.roundToInt())
    }

    /*fun systemName(galacticPosition: GalacticPosition): String {

    }*/

    companion object {
        val greekAlphabet = listOf(
            "alpha",
            "beta",
            "gamma",
            "delta",
            "epsilon",
            "zeta",
            "eta",
            "theta",
            "kappa",
            "lambda",
            "mu",
            "nu",
            "xi",
            "omicron",
            "pi",
            "rho",
            "sigma",
            "tau",
            "upsilon",
            "phi",
            "chi",
            "psi",
            "omega"
        )
        val minecraftMythicalCreatures = listOf(
            "creep",
            "end",
            "zomb",
            "osse",
            "aren",
            "herobr",
            "null",
            "pigma",
            "lim",
            "cuscar",
            "cust",
            "evok",
            "ravag",
            "husk",
            "stray",
            "vex",
            "vindi",
            "pythoniss",
            "with",
            "ghast",
            "strid",
            "verspertil",
            "cat",
            "pull",
            "cod",
            "vacc",
            "asin",
            "vulp",
            "equ",
            "bolet",
            "hinn",
            "ocel",
            "mascaren",
            "porc",
            "lep",
            "salm",
            "ovi",
            "gol",
            "lollig",
            "testud",
            "pagan",
            "mercat",
            "ap",
            "delphi",
            "lup"
        )
        val alphabet = listOf(
            "a",
            "b",
            "c",
            "d",
            "e",
            "f",
            "g",
            "h",
            "i",
            "j",
            "k",
            "l",
            "m",
            "o",
            "p",
            "q",
            "r",
            "s",
            "t",
            "u",
            "v",
            "w",
            "x",
            "y",
            "z"
        )
        val suffixes = listOf(
            "ae", "i", "us"
        )

        private fun getTotalCombinations(format: String): Long {
            var totalNames = 0L
            for (c in format) {
                when (c) {
                    'A' -> totalNames *= alphabet.size
                    'G' -> totalNames *= greekAlphabet.size
                    'M' -> totalNames *= minecraftMythicalCreatures.size
                    's' -> totalNames *= suffixes.size
                    'N' -> totalNames *= 1000
                }
            }
            return totalNames
        }
    }
}