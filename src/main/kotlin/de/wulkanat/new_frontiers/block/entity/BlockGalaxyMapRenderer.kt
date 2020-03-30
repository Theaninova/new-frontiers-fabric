package de.wulkanat.new_frontiers.block.entity

import de.wulkanat.new_frontiers.GALAXY_INSTANCE
import de.wulkanat.new_frontiers.world.dimension.model.GalacticPosition
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
    private val startPosition = GalacticPosition(0.0, 0.0, 0.0)
    var map = GALAXY_INSTANCE.getNearSystems(startPosition)
    var position = startPosition
        set(value) {
            field = value
            map = GALAXY_INSTANCE.getNearSystems(position)
        }
    var scale = 1.0F
    var defaultScale = 0.1F

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
            matrices.translate(0.5 + x * scale * defaultScale, 1.25 + 16.0 * scale * defaultScale * 0.5 + y * scale * defaultScale, 0.5 + z * scale * defaultScale)
            matrices.scale(defaultScale * scale, defaultScale * scale, defaultScale * scale)

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

        matrices!!.push()

        matrices.translate(0.5, 1.25, 0.5)
        matrices.scale(0.05F, 0.05F, 0.05F)

        val lightAbove = WorldRenderer.getLightmapCoordinates(blockEntity?.world, blockEntity?.pos?.up())
        MinecraftClient.getInstance().textRenderer.draw(
            "${position.x_ly}, ${position.y_ly}, ${position.z_ly}",
            1.0F, -0.5F, 0, false, matrices.peek().model, vertexConsumers, false, 9999999, lightAbove
        )

        matrices.pop()
    }

    companion object {
        private val stack = ItemStack(Items.GLOWSTONE) // TODO

        fun register() {
            BlockEntityRendererRegistry.INSTANCE.register(BlockGalaxyMapEntity.BLOCK_GALAXY_MAP_ENTITY) {
                BlockGalaxyMapRenderer(
                    it
                )
            }
        }
    }
}