package de.wulkanat.new_frontiers.block.entity

import de.wulkanat.new_frontiers.MOD_ID
import de.wulkanat.new_frontiers.item_group.ItemGroupNewFrontiersCore
import net.fabricmc.fabric.api.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.Material
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

object BlockTransporter {
    val TRANSPORTER = Block(FabricBlockSettings.of(Material.METAL).build())

    fun register() {
        Registry.register(Registry.BLOCK, Identifier(MOD_ID, "transporter"), TRANSPORTER)
        Registry.register(Registry.ITEM, Identifier(MOD_ID, "transporter"), BlockItem(TRANSPORTER, Item.Settings().group(
            ItemGroupNewFrontiersCore.NEW_FRONTIERS_CORE)))
    }
}