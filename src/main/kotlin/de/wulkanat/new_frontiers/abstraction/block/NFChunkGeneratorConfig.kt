package de.wulkanat.new_frontiers.abstraction.block

import net.minecraft.block.BlockState
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig

class NFChunkGeneratorConfig(
    villageDistance: Int = 10,
    villageSeparation: Int = 8 // TODO: all of the Rest needs to be implemented
) : ChunkGeneratorConfig() {
    init {
        this.villageDistance = villageDistance
        this.villageSeparation
    }

    override fun getVillageDistance(): Int {
        return villageDistance
    }

    override fun getVillageSeparation(): Int {
        return this.villageSeparation
    }

    override fun getOceanMonumentSpacing(): Int {
        return oceanMonumentSpacing
    }

    override fun getOceanMonumentSeparation(): Int {
        return oceanMonumentSeparation
    }

    override fun getStrongholdDistance(): Int {
        return strongholdDistance
    }

    override fun getStrongholdCount(): Int {
        return strongholdCount
    }

    override fun getStrongholdSpread(): Int {
        return strongholdSpread
    }

    override fun getTempleDistance(): Int {
        return templeDistance
    }

    override fun getTempleSeparation(): Int {
        return 8
    }

    override fun getShipwreckSpacing(): Int {
        return 16
    }

    override fun getShipwreckSeparation(): Int {
        return 8
    }

    override fun getOceanRuinSpacing(): Int {
        return 16
    }

    override fun getOceanRuinSeparation(): Int {
        return 8
    }

    override fun getEndCityDistance(): Int {
        return endCityDistance
    }

    override fun getEndCitySeparation(): Int {
        return 11
    }

    override fun getMansionDistance(): Int {
        return mansionDistance
    }

    override fun getMansionSeparation(): Int {
        return 20
    }

    override fun getDefaultBlock(): BlockState? {
        return defaultBlock
    }

    override fun getDefaultFluid(): BlockState? {
        return defaultFluid
    }

    override fun setDefaultBlock(state: BlockState?) {
        defaultBlock = state
    }

    override fun setDefaultFluid(state: BlockState?) {
        defaultFluid = state
    }

    override fun getMaxY(): Int {
        return 0
    }

    override fun getMinY(): Int {
        return 256
    }
}