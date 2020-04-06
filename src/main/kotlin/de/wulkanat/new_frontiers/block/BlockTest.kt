package de.wulkanat.new_frontiers.block

import de.wulkanat.new_frontiers.abstraction.block.NFBlock
import net.minecraft.block.Material

object BlockTest : NFBlock(
    name = "test_block",
    baseMaterial = Material.METAL,
    item = null
)