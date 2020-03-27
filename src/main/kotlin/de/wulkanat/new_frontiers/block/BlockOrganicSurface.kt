package de.wulkanat.new_frontiers.block

import net.minecraft.block.Block
import net.minecraft.state.property.IntProperty

class BlockOrganicSurface(settings: Settings) : Block(settings) {
    init {
        defaultState = stateManager.defaultState.with(SEED, 0)
    }

    override fun getBlastResistance(): Float {
        return super.getBlastResistance()
    }

    companion object {
        val SEED: IntProperty = IntProperty.of("seed", Int.MIN_VALUE, Int.MAX_VALUE)
    }
}