package de.wulkanat.new_frontiers.abstraction.block

import de.wulkanat.new_frontiers.MOD_ID
import de.wulkanat.new_frontiers.item_group.ItemGroupNewFrontiersCore
import net.minecraft.block.Block
import net.minecraft.block.Material
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

abstract class NFBlock(
    val name: String,
    baseMaterial: Material = Material.EARTH,
    opaque: Boolean = true,
    hasCollision: Boolean = true,
    strength: Strength = Strength(1F, 1F),
    slipperiness: Float = 0F,
    jumpVelocityMultiplier: Float = 1F,
    private val item: BlockItemInfo? = BlockItemInfo()
) : Block(Settings.of(baseMaterial)
    .strength(strength.hardness, strength.resistance)
    .runIf(!hasCollision) { it.noCollision() }
    .runIf(!opaque) { it.nonOpaque() }
    .slipperiness(slipperiness)
    .jumpVelocityMultiplier(jumpVelocityMultiplier)
) {
    class Strength(
        val hardness: Float,
        val resistance: Float
    )

    class BlockItemInfo(
        val group: ItemGroup = ItemGroupNewFrontiersCore.INSTANCE
    )

    fun register() {
        Registry.register(Registry.BLOCK, Identifier(MOD_ID, name), this)
        if (item != null) {
            Registry.register(
                Registry.ITEM,
                Identifier(MOD_ID, name),
                BlockItem(this, Item.Settings().group(item.group))
            )
        }
    }
}

inline fun <T> T.runIf(predicate: Boolean, callback: (T) -> T): T {
    return if (predicate) callback(this)
    else this
}
