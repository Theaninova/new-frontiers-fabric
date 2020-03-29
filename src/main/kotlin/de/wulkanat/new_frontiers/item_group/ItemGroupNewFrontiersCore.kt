package de.wulkanat.new_frontiers.item_group

import de.wulkanat.new_frontiers.MOD_ID
import de.wulkanat.new_frontiers.block.entity.BlockTransporter
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder
import net.minecraft.item.ItemStack
import net.minecraft.util.Identifier

object ItemGroupNewFrontiersCore {
    val NEW_FRONTIERS_CORE = FabricItemGroupBuilder.build(
        Identifier(
            MOD_ID,
            "new_frontiers_core"
        )
    ) { ItemStack(BlockTransporter.TRANSPORTER) }
}