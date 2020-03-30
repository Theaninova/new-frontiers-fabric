package de.wulkanat.new_frontiers.block.entity

import de.wulkanat.new_frontiers.GALAXY_INSTANCE
import de.wulkanat.new_frontiers.world.dimension.model.GalacticPosition
import de.wulkanat.new_frontiers.world.dimension.model.Planet
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry
import net.minecraft.client.MinecraftClient
import net.minecraft.client.render.OverlayTexture
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.WorldRenderer
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher
import net.minecraft.client.render.block.entity.BlockEntityRenderer
import net.minecraft.client.render.model.json.ModelTransformation
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.item.ItemStack
import net.minecraft.item.Items

class BlockGalaxyMapRenderer(dispatcher: BlockEntityRenderDispatcher) :
    BlockEntityRenderer<BlockGalaxyMapEntity>(dispatcher) {
    var map = GALAXY_INSTANCE.getNearSystems(GalacticPosition(0.0, 0.0, 0.0))
    var position = GalacticPosition(0.0, 0.0, 0.0)
        set(value) {
            field = value
            map = GALAXY_INSTANCE.getNearSystems(position)
        }

    override fun render(
        blockEntity: BlockGalaxyMapEntity?,
        tickDelta: Float,
        matrices: MatrixStack?,
        vertexConsumers: VertexConsumerProvider?,
        light: Int,
        overlay: Int
    ) {

        for (system in map) {
            matrices!!.push()

            val (x, y, z) = system.position - position
            matrices.translate(0.5 + x / 2, 1.25 + 4.0 + y / 2, 0.5 + z / 2)

            val lightAbove = WorldRenderer.getLightmapCoordinates(blockEntity?.world, blockEntity?.pos?.up())
            MinecraftClient.getInstance().itemRenderer.renderItem(
                stack,
                ModelTransformation.Type.GROUND,
                lightAbove,
                OverlayTexture.DEFAULT_UV,
                matrices,
                vertexConsumers
            )

            matrices.pop()
        }
    }

    companion object {
        private val stack = ItemStack(Items.ACACIA_PLANKS) // TODO

        fun register() {
            BlockEntityRendererRegistry.INSTANCE.register(BlockGalaxyMapEntity.BLOCK_GALAXY_MAP_ENTITY) {
                BlockGalaxyMapRenderer(
                    it
                )
            }
        }
    }
}