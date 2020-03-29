package de.wulkanat.new_frontiers.block.entity

import de.wulkanat.new_frontiers.MOD_ID
import de.wulkanat.new_frontiers.block.BlockGalaxyMap
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.util.registry.Registry
import java.util.function.Supplier

class BlockGalaxyMapEntity : BlockEntity(BLOCK_GALAXY_MAP_ENTITY) {
    companion object {
        lateinit var BLOCK_GALAXY_MAP_ENTITY: BlockEntityType<BlockGalaxyMapEntity>

        fun register() {
            BLOCK_GALAXY_MAP_ENTITY = Registry.register(Registry.BLOCK_ENTITY, "$MOD_ID:galaxy_map", BlockEntityType.Builder.create(
                Supplier { BlockGalaxyMapEntity() }, BlockGalaxyMap.GALAXY_MAP).build(null))
        }
    }
}