package com.extendedfeatures.init.utils.internal.rendering.range;

import com.extendedfeatures.ExtendedFeaturesCore;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.core.BlockPos;
import net.minecraftforge.api.distmarker.*;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.*;
import java.util.function.Function;

@Mod.EventBusSubscriber(
        modid = ExtendedFeaturesCore.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT
)
@OnlyIn(Dist.CLIENT)
public class RangeRenderer {

    private static class ActiveBoxData {

        final BlockPos position;
        final int range;
        long expireAtGameTime;

        ActiveBoxData(BlockPos position, int range, long expireAtGameTime) {
            this.position = position;
            this.range = range;
            this.expireAtGameTime = expireAtGameTime;
        }
    }

    private static final Map<BlockPos, ActiveBoxData> activeBoxes = new HashMap<>();

    public static void showBoxAtPositionWithRange(BlockPos position, int range, int durationTicks) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null)
            return;
        long expireAt = mc.level.getGameTime() + durationTicks;
        activeBoxes.put(position.immutable(), new ActiveBoxData(position.immutable(), range, expireAt));
    }

    public static void hideBoxAtPosition(BlockPos position) {
        activeBoxes.remove(position);
    }

    private static final RenderStateShard.TransparencyStateShard TRANSLUCENT_TRANSPARENCY = new RenderStateShard
            .TransparencyStateShard("translucent",
            () -> {
                RenderSystem.enableBlend();
                RenderSystem.defaultBlendFunc();
            },
            RenderSystem::disableBlend);

    private static final RenderStateShard.WriteMaskStateShard CUSTOM_COLOR_DEPTH_WRITE = new RenderStateShard
            .WriteMaskStateShard(true, true);

    private static final RenderType TRANSLUCENT_FILL = RenderType.create(
            "wireless_range_fill",
            DefaultVertexFormat.POSITION_COLOR,
            VertexFormat.Mode.QUADS, 256, false, true,
            RenderType.CompositeState.builder()
                    .setShaderState(new RenderStateShard.ShaderStateShard(GameRenderer::getPositionColorShader))
                    .setTransparencyState(TRANSLUCENT_TRANSPARENCY)
                    .setWriteMaskState(CUSTOM_COLOR_DEPTH_WRITE)
                    .setCullState(new RenderStateShard.CullStateShard(false))
                    .createCompositeState(true));

    @SubscribeEvent
    public static void onRenderWorld(RenderLevelStageEvent event) {
        if (event.getStage() != RenderLevelStageEvent.Stage.AFTER_TRANSLUCENT_BLOCKS)
            return;

        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.level == null)
            return;

        long now = mc.level.getGameTime();
        activeBoxes.values().removeIf(activeBoxData -> now >= activeBoxData.expireAtGameTime);

        if (activeBoxes.isEmpty()) return;

        PoseStack poseStack = event.getPoseStack();
        var camera = mc.gameRenderer.getMainCamera();

        double camX = camera.getPosition().x;
        double camY = camera.getPosition().y;
        double camZ = camera.getPosition().z;

        poseStack.pushPose();
        poseStack.translate(-camX, -camY, -camZ);

        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableCull();
        RenderSystem.setShader(GameRenderer::getPositionColorShader);

        var bufferSource = mc.renderBuffers().bufferSource();
        VertexConsumer buffer = bufferSource.getBuffer(TRANSLUCENT_FILL);

        for (ActiveBoxData boxData : activeBoxes.values()) {
            BlockPos pos = boxData.position;

            double radius = (double) boxData.range - 0.01f;
            double x1 = pos.getX() - radius;
            double z1 = pos.getZ() - radius;
            double x2 = pos.getX() + radius + 1;
            double z2 = pos.getZ() + radius + 1;
            double minY = mc.level.getMinBuildHeight();
            double maxY = mc.level.getMaxBuildHeight();

            int posColour = pos.hashCode();

            renderWalls(poseStack, buffer, x1, minY, z1, x2, maxY, z2, posColour);
        }

        bufferSource.endBatch(TRANSLUCENT_FILL);
        RenderSystem.disableBlend();
        poseStack.popPose();
    }

    private static void renderWalls(PoseStack poseStack, VertexConsumer buffer,
                                    double x1, double y1, double z1,
                                    double x2, double y2, double z2,
                                    int hashCode) {
        var matrix = poseStack.last().pose();

        float baseHue = ((hashCode & 0xFFFFFF) / (float) 0xFFFFFF);

        Function<Float, float[]> hsvToRgb = (h) -> {
            float s = 1.0f, v = 1.0f;
            float c = v * s;
            float x = c * (1 - Math.abs(((h * 6) % 2) - 1));
            float m = v - c;
            float r = 0, g = 0, b = 0;
            if (h < 1f / 6f) {
                r = c;
                g = x;
            } else
                if (h < 2f / 6f) {
                r = x;
                g = c;
            } else
                if (h < 3f / 6f) {
                g = c;
                b = x;
            } else
                if (h < 4f / 6f) {
                g = x;
                b = c;
            } else
                if (h < 5f / 6f) {
                r = x;
                b = c;
            } else {
                r = c;
                b = x;
            }
            return new float[] { r + m, g + m, b + m };
        };

        float hueStep = 1f / 8f;
        float[][] colors = new float[8][3];
        for (int i = 0; i < 8; i++) {
            colors[i] = hsvToRgb.apply((baseHue + i * hueStep) % 1.0f);
        }

        // Wall 1
        buffer.vertex(matrix, (float) x1, (float) y1, (float) z1).color(colors[0][0], colors[0][1], colors[0][2], (float) 0.3).endVertex();
        buffer.vertex(matrix, (float) x2, (float) y1, (float) z1).color(colors[1][0], colors[1][1], colors[1][2], (float) 0.3).endVertex();
        buffer.vertex(matrix, (float) x2, (float) y2, (float) z1).color(colors[2][0], colors[2][1], colors[2][2], (float) 0.3).endVertex();
        buffer.vertex(matrix, (float) x1, (float) y2, (float) z1).color(colors[3][0], colors[3][1], colors[3][2], (float) 0.3).endVertex();

        // Wall 2
        buffer.vertex(matrix, (float) x2, (float) y1, (float) z2).color(colors[4][0], colors[4][1], colors[4][2], (float) 0.3).endVertex();
        buffer.vertex(matrix, (float) x1, (float) y1, (float) z2).color(colors[5][0], colors[5][1], colors[5][2], (float) 0.3).endVertex();
        buffer.vertex(matrix, (float) x1, (float) y2, (float) z2).color(colors[6][0], colors[6][1], colors[6][2], (float) 0.3).endVertex();
        buffer.vertex(matrix, (float) x2, (float) y2, (float) z2).color(colors[7][0], colors[7][1], colors[7][2], (float) 0.3).endVertex();

        // Walls 3 and 4
        buffer.vertex(matrix, (float) x1, (float) y1, (float) z2).color(colors[5][0], colors[5][1], colors[5][2], (float) 0.3).endVertex();
        buffer.vertex(matrix, (float) x1, (float) y1, (float) z1).color(colors[0][0], colors[0][1], colors[0][2], (float) 0.3).endVertex();
        buffer.vertex(matrix, (float) x1, (float) y2, (float) z1).color(colors[3][0], colors[3][1], colors[3][2], (float) 0.3).endVertex();
        buffer.vertex(matrix, (float) x1, (float) y2, (float) z2).color(colors[6][0], colors[6][1], colors[6][2], (float) 0.3).endVertex();

        buffer.vertex(matrix, (float) x2, (float) y1, (float) z1).color(colors[1][0], colors[1][1], colors[1][2], (float) 0.3).endVertex();
        buffer.vertex(matrix, (float) x2, (float) y1, (float) z2).color(colors[4][0], colors[4][1], colors[4][2], (float) 0.3).endVertex();
        buffer.vertex(matrix, (float) x2, (float) y2, (float) z2).color(colors[7][0], colors[7][1], colors[7][2], (float) 0.3).endVertex();
        buffer.vertex(matrix, (float) x2, (float) y2, (float) z1).color(colors[2][0], colors[2][1], colors[2][2], (float) 0.3).endVertex();
    }
}