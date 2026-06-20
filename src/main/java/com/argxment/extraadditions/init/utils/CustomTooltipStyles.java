package com.argxment.extraadditions.init.utils;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.utils.GradientUtil;

import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;

import java.util.Map;
import java.util.function.UnaryOperator;

public class CustomTooltipStyles {

    // Tier main colors
    private static final int LV = 0xFFAAAAAA; // Gray
    private static final int MV = 0xFF55FFFF; // Aqua
    private static final int HV = 0xFFFFAA00; // Gold
    private static final int EV = 0xFFAA00AA; // Dark Purple
    private static final int IV = 0xFF5555FF; // Blue
    private static final int LuV = 0xFFFF55FF; // Light Purple
    private static final int ZPM = 0xFFFF5555; // Red
    private static final int UV = 0xFF00AAAA; // Dark Aqua

    private static final int WHITE = 0xFFFFFFFF;

    // Speed modifiers
    private static final double SPEED_SLOW = 0.1;
    private static final double SPEED_MEDIUM = 0.2;
    private static final double SPEED_FAST = 0.3;

    // Tries to replicate GTCEu Tooltip Helper
    public static TextColor movingGradient(double speed, int colorA, int colorB) {
        float t = (float) (Math.sin(GTValues.CLIENT_TIME * speed) * 0.5 + 0.5);
        int blended = GradientUtil.blend(colorA, colorB, t);
        return TextColor.fromRgb(blended & 0xFFFFFF);
    }

    /*
     * Side note:
     * Some of this gradients may already exist in GTCEu;
     * See "com.gregtechceu.gtceu.client.util.TooltipHelper" for reference
     * UV is "BLINKING_CYAN"
     * ZPM is "BLINKING_RED"
     * HV is "BLINKING_ORANGE"
     * LV is "BLINKING_GRAY"
     */
    public static final UnaryOperator<Style> LV_GRADIENT = style -> style
            .withColor(movingGradient(SPEED_SLOW, LV, WHITE));

    public static final UnaryOperator<Style> MV_GRADIENT = style -> style
            .withColor(movingGradient(SPEED_SLOW, MV, WHITE));

    public static final UnaryOperator<Style> HV_GRADIENT = style -> style
            .withColor(movingGradient(SPEED_SLOW, HV, WHITE));

    public static final UnaryOperator<Style> EV_GRADIENT = style -> style
            .withColor(movingGradient(SPEED_SLOW, EV, WHITE));

    public static final UnaryOperator<Style> IV_GRADIENT = style -> style
            .withColor(movingGradient(SPEED_SLOW, IV, WHITE));

    public static final UnaryOperator<Style> LUV_GRADIENT = style -> style
            .withColor(movingGradient(SPEED_SLOW, LuV, WHITE));

    public static final UnaryOperator<Style> ZPM_GRADIENT = style -> style
            .withColor(movingGradient(SPEED_SLOW, ZPM, WHITE));

    public static final UnaryOperator<Style> UV_GRADIENT = style -> style
            .withColor(movingGradient(SPEED_SLOW, UV, WHITE));

    // Tiered parallel, MUST BE paired with Tiered multiblock (Example: Advanced Fusion Reactors)
    public static final Map<Integer, UnaryOperator<Style>> TIER_GRADIENTS = Map.of(
            GTValues.LuV, LUV_GRADIENT,
            GTValues.ZPM, ZPM_GRADIENT,
            GTValues.UV, UV_GRADIENT
    );

    public static UnaryOperator<Style> forTier(int tier) {
        return TIER_GRADIENTS.getOrDefault(
                tier, LUV_GRADIENT
        );
    }
}