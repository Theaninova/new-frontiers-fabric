package de.wulkanat.new_frontiers.item_group

import de.wulkanat.new_frontiers.abstraction.block.NFItemGroup
import de.wulkanat.new_frontiers.block.entity.BlockTransporter
import net.minecraft.item.ItemStack

object ItemGroupNewFrontiersCore : NFItemGroup(
    name = "new_frontiers_core",
    previewItem = ItemStack(BlockTransporter.TRANSPORTER)
)