package de.wulkanat.new_frontiers.block.entity

import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry
import net.minecraft.client.MinecraftClient
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher
import net.minecraft.client.render.block.entity.BlockEntityRenderer
import net.minecraft.client.render.model.json.ModelTransformation
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import java.util.function.Function

class BlockGalaxyMapRenderer(dispatcher: BlockEntityRenderDispatcher) : BlockEntityRenderer<BlockGalaxyMapEntity>(dispatcher) {
    override fun render(
        blockEntity: BlockGalaxyMapEntity?,
        tickDelta: Float,
        matrices: MatrixStack?,
        vertexConsumers: VertexConsumerProvider?,
        light: Int,
        overlay: Int
    ) {
        matrices!!.push()

        matrices.translate(0.5, 1.25, 0.5)
        MinecraftClient.getInstance().itemRenderer.renderItem(stack, ModelTransformation.Type.GROUND, light, overlay, matrices, vertexConsumers)

        matrices.pop()
    }

    companion object {
        private val stack = ItemStack(Items.ACACIA_PLANKS) // TODO

        fun register() {
            BlockEntityRendererRegistry.INSTANCE.register(BlockGalaxyMapEntity.BLOCK_GALAXY_MAP_ENTITY) { BlockGalaxyMapRenderer(it) }
        }
    }
}