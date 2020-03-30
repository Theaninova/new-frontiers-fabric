package de.wulkanat.new_frontiers.block

import de.wulkanat.new_frontiers.MOD_ID
import de.wulkanat.new_frontiers.block.entity.BlockGalaxyMapEntity
import de.wulkanat.new_frontiers.block.entity.BlockTransporter
import de.wulkanat.new_frontiers.item_group.ItemGroupNewFrontiersCore
import net.fabricmc.fabric.api.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.BlockWithEntity
import net.minecraft.block.Material
import net.minecraft.block.entity.BlockEntity
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import javax.swing.text.html.BlockView

class BlockGalaxyMap : BlockWithEntity(Settings.of(Material.METAL)) {
    companion object {
        val GALAXY_MAP = BlockGalaxyMap()

        fun register() {
            Registry.register(Registry.BLOCK, Identifier(MOD_ID, "galaxy_map"), GALAXY_MAP)
            Registry.register(
                Registry.ITEM, Identifier(MOD_ID, "galaxy_map"), BlockItem(
                    GALAXY_MAP, Item.Settings().group(
                        ItemGroupNewFrontiersCore.NEW_FRONTIERS_CORE
                    )
                )
            )
        }
    }

    override fun createBlockEntity(view: net.minecraft.world.BlockView?): BlockEntity? {
        return BlockGalaxyMapEntity()
    }
}