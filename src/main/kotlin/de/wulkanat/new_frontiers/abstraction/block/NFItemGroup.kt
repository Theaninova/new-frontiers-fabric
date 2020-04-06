package de.wulkanat.new_frontiers.abstraction.block

import de.wulkanat.new_frontiers.MOD_ID
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder
import net.minecraft.item.ItemStack
import net.minecraft.util.Identifier

abstract class NFItemGroup(
    name: String,
    previewItem: ItemStack
) {
    val INSTANCE = FabricItemGroupBuilder.build(
        Identifier(
            MOD_ID,
            name
        )
    ) { previewItem }
}