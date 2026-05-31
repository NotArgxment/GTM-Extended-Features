package com.vyx.extraadditions.machines.client;

import com.gregtechceu.gtceu.api.GTValues;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;

import java.util.Map;
import java.util.function.UnaryOperator;

public class EATooltipStyles {

    private static final double SPEED = 0.2;

    public static final UnaryOperator<Style> LUV_GRADIENT = style -> {
        float t = (float)(Math.sin(GTValues.CLIENT_TIME * SPEED) * 0.5 + 0.5);
        int r = 255;
        int g = (int)(255 - t * 105);
        int b = (int)(255 - t * 75);
        return style.withColor(TextColor.fromRgb((r << 16) | (g << 8) | b));
    };

    public static final UnaryOperator<Style> ZPM_GRADIENT = style -> {
            float t = (float)(Math.sin(GTValues.CLIENT_TIME * SPEED) * 0.5 + 0.5);
            int r = (int)(255 + t * (252 - 255));
            int g = (int)(51 + t * (154 - 51));
            int b = (int)(51 + t * (154 - 51));
            return style.withColor(TextColor.fromRgb((r << 16) | (g << 8) | b));
    };

    public static final UnaryOperator<Style> UV_GRADIENT = style -> {
        float t = (float)(Math.sin(GTValues.CLIENT_TIME * SPEED) * 0.5 + 0.5);
        int r = (int)(0 + t * (255 - 0));
        int g = (int)(170 + t * (255 - 170));
        int b = (int)(170 + t * (255 - 170));
        return style.withColor(TextColor.fromRgb((r << 16) | (g << 8) | b));
    };

    public static final Map<Integer, UnaryOperator<Style>> TIER_GRADIENTS = Map.of(
            GTValues.LuV, LUV_GRADIENT,
            GTValues.ZPM, ZPM_GRADIENT,
            GTValues.UV,  UV_GRADIENT
    );

    public static UnaryOperator<Style> forTier(int tier) {
        return TIER_GRADIENTS.getOrDefault(tier, LUV_GRADIENT); // fallback
    }
}