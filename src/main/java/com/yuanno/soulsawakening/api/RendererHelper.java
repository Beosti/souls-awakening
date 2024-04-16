package com.yuanno.soulsawakening.api;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.interfaces.IAttackAbility;
import com.yuanno.soulsawakening.ability.api.interfaces.IShootAbility;
import com.yuanno.soulsawakening.ability.api.interfaces.IWaveAbility;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.text.ITextProperties;
import net.minecraft.util.text.LanguageMap;
import net.minecraft.util.text.Style;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.gui.GuiUtils;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RendererHelper {

    public static void drawIcon(ResourceLocation rs, MatrixStack matrixStack, int x, int y, int z, int u, int v)
    {
        drawIcon(rs, matrixStack, x, y, z, u, v, 1, 1, 1, 1);
    }

    public static void drawIcon(ResourceLocation rs, MatrixStack matrixStack, int x, int y, int z, int u, int v, int intColor)
    {
        Color color = new Color(intColor);
        drawIcon(rs, matrixStack, x, y, z, u, v, color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f, 1);
    }

    /**
     * As the name says, made to draw any icon on the screen
     * @author Wynd-sensei
     */
    public static void drawIcon(ResourceLocation rs, MatrixStack matrixStack, int x, int y, int z, int u, int v, float red, float green, float blue, float alpha)
    {
        Matrix4f matrix = matrixStack.last().pose();
        RenderSystem.enableAlphaTest();
        RenderSystem.enableBlend();
        Minecraft.getInstance().getTextureManager().bind(rs);
        BufferBuilder bufferbuilder = Tessellator.getInstance().getBuilder();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR_TEX);
        bufferbuilder.vertex(matrix, x, y + v, z).color(red, green, blue, alpha).uv(0.0f, 1.0f).endVertex();
        bufferbuilder.vertex(matrix, x + u, y + v, z).color(red, green, blue, alpha).uv(1.0f, 1.0f).endVertex();
        bufferbuilder.vertex(matrix, x + u, y, z).color(red, green, blue, alpha).uv(1.0f, 0.0f).endVertex();
        bufferbuilder.vertex(matrix, x, y, z).color(red, green, blue, alpha).uv(0f, 0f).endVertex();
        Tessellator.getInstance().end();
        RenderSystem.disableBlend();
    }

    /**
     * Method to draw the ability tooltips (mostly because vanilla minecraft can't draw icons)
     * @author Wynd-sensei
     */
    public static void drawAbilityTooltip(Ability ability, MatrixStack mStack, java.util.List<? extends ITextProperties> textLines, int mouseX, int mouseY, int screenWidth, int screenHeight, int maxTextWidth, int backgroundColorStart, int backgroundColorEnd, int borderColorStart, int borderColorEnd, FontRenderer font)
    {
        ItemStack stack = ItemStack.EMPTY;
        if (!textLines.isEmpty())
        {
            RenderTooltipEvent.Pre event = new RenderTooltipEvent.Pre(stack, textLines, mStack, mouseX, mouseY, screenWidth, screenHeight, maxTextWidth, font);
            if (MinecraftForge.EVENT_BUS.post(event))
                return;
            mouseX = event.getX();
            mouseY = event.getY();
            screenWidth = event.getScreenWidth();
            screenHeight = event.getScreenHeight();
            maxTextWidth = event.getMaxWidth();
            font = event.getFontRenderer();

            RenderSystem.disableRescaleNormal();
            RenderSystem.disableDepthTest();
            int tooltipTextWidth = 0;

            for (ITextProperties textLine : textLines)
            {
                int textLineWidth = font.width(textLine);
                if (textLineWidth > tooltipTextWidth)
                    tooltipTextWidth = textLineWidth;
            }

            int titleLinesCount = 1;
            int tooltipX = mouseX + 12;
            if (tooltipX + tooltipTextWidth + 4 > screenWidth)
            {
                tooltipX = mouseX - 16 - tooltipTextWidth;
                if (tooltipX < 4) // if the tooltip doesn't fit on the screen
                {
                    if (mouseX > screenWidth / 2)
                        tooltipTextWidth = mouseX - 12 - 8;
                    else
                        tooltipTextWidth = screenWidth - 16 - mouseX;
                }
            }

            if (maxTextWidth > 0 && tooltipTextWidth > maxTextWidth)
            {
                tooltipTextWidth = maxTextWidth;
            }

            int wrappedTooltipWidth = 0;
            java.util.List<ITextProperties> wrappedTextLines = new ArrayList<>();
            for (int i = 0; i < textLines.size(); i++)
            {
                ITextProperties textLine = textLines.get(i);
                List<ITextProperties> wrappedLine = font.getSplitter().splitLines(textLine, tooltipTextWidth, Style.EMPTY);
                if (i == 0) {
                    titleLinesCount = wrappedLine.size();
                }

                for (ITextProperties line : wrappedLine)
                {
                    int lineWidth = font.width(line);
                    if (lineWidth > wrappedTooltipWidth) {
                        wrappedTooltipWidth = lineWidth;
                    }
                    wrappedTextLines.add(line);
                }
            }
            tooltipTextWidth = wrappedTooltipWidth;
            textLines = wrappedTextLines;

            if (mouseX > screenWidth / 2)
                tooltipX = mouseX - 16 - tooltipTextWidth;
            else
                tooltipX = mouseX + 12;

            int tooltipY = mouseY - 12;
            int tooltipHeight = 14;

            if (textLines.size() > 1)
            {
                tooltipHeight += (textLines.size() - 1) * 10;
                if (textLines.size() > titleLinesCount)
                    tooltipHeight += 2; // gap between title lines and next lines
            }

            if (tooltipY < 4)
                tooltipY = 4;
            else if (tooltipY + tooltipHeight + 4 > screenHeight)
                tooltipY = screenHeight - tooltipHeight - 4;

            final int zLevel = 400;

            mStack.pushPose();
            Matrix4f mat = mStack.last().pose();
            GuiUtils.drawGradientRect(mat, zLevel, tooltipX - 3, tooltipY - 3, tooltipX + tooltipTextWidth + 3, tooltipY + tooltipHeight + 3, backgroundColorStart, backgroundColorEnd);

            GuiUtils.drawGradientRect(mat, zLevel, tooltipX - 3, tooltipY - 4, tooltipX + tooltipTextWidth + 3, tooltipY - 3, borderColorStart, borderColorStart);
            GuiUtils.drawGradientRect(mat, zLevel, tooltipX + tooltipTextWidth + 3, tooltipY - 3, tooltipX + tooltipTextWidth + 4, tooltipY + tooltipHeight + 3, borderColorStart, borderColorEnd);
            GuiUtils.drawGradientRect(mat, zLevel, tooltipX - 4, tooltipY - 3, tooltipX - 3, tooltipY + tooltipHeight + 3, borderColorStart, borderColorEnd);
            GuiUtils.drawGradientRect(mat, zLevel, tooltipX - 3, tooltipY + tooltipHeight + 3, tooltipX + tooltipTextWidth + 3, tooltipY + tooltipHeight + 4, borderColorEnd, borderColorEnd);

            GuiUtils.drawGradientRect(mat, zLevel, tooltipX - 3, tooltipY - 3, tooltipX + tooltipTextWidth + 3, tooltipY - 3 + 1, borderColorStart, borderColorStart);
            GuiUtils.drawGradientRect(mat, zLevel, tooltipX + tooltipTextWidth + 2, tooltipY - 3 + 1, tooltipX + tooltipTextWidth + 3, tooltipY + tooltipHeight + 3 - 1, borderColorStart, borderColorEnd);
            GuiUtils.drawGradientRect(mat, zLevel, tooltipX - 3, tooltipY - 3 + 1, tooltipX - 3 + 1, tooltipY + tooltipHeight + 3 - 1, borderColorStart, borderColorEnd);
            GuiUtils.drawGradientRect(mat, zLevel, tooltipX - 3, tooltipY + tooltipHeight + 2, tooltipX + tooltipTextWidth + 3, tooltipY + tooltipHeight + 3, borderColorEnd, borderColorEnd);

            String abilityName = ability.getName();
            int nameWidth = font.width(abilityName);
            Color iconColor = Beapi.hexToRGB("#333333");
            Set<ResourceLocation> coloredIcons = new HashSet<>();
            Set<ResourceLocation> staticIcons = new HashSet<>();

            /*
            if (ability.getSourceHakiNature() != null) {
                if (ability.getSourceHakiNature().getTexture() != null) {
                    coloredIcons.add(ability.getSourceHakiNature().getTexture());
                }
            }

            if (ability.getSourceTypes() != null) {
                for (SourceType type : ability.getSourceTypes()) {
                    if (type.getTexture() != null) {
                        coloredIcons.add(type.getTexture());
                    }
                }
            }
             */

            if (ability.getSourceElement() != null) {
                if (ability.getSourceElement().getTexture() != null) {
                    coloredIcons.add(ability.getSourceElement().getTexture());
                }
            }

            if (ability instanceof IWaveAbility)
                staticIcons.add(new ResourceLocation(Main.MODID, "textures/gui/icons/wave.png"));
            if (ability instanceof IAttackAbility)
                staticIcons.add(new ResourceLocation(Main.MODID, "textures/gui/icons/attack.png"));
            if (ability instanceof IShootAbility)
                staticIcons.add(new ResourceLocation(Main.MODID, "textures/gui/icons/shoot.png"));
            int spacing = 4;
            for(ResourceLocation icon : coloredIcons) {
                spacing += 12;
                RendererHelper.drawIcon(icon, event.getMatrixStack(), tooltipX + nameWidth + (spacing - 12), tooltipY - 2, 500, 12, 12, iconColor.getRed() , iconColor.getGreen() , iconColor.getBlue(), 1.0f);
            }
            for(ResourceLocation icon : staticIcons) {
                spacing += 12;
                RendererHelper.drawIcon(icon, event.getMatrixStack(), tooltipX - 18 + (spacing - 12), (tooltipY + tooltipHeight) - 14, 500, 16, 16, 1.0f, 1.0f, 1.0f, 1.0f);
            }

            MinecraftForge.EVENT_BUS.post(new RenderTooltipEvent.PostBackground(stack, textLines, mStack, tooltipX, tooltipY, font, tooltipTextWidth, tooltipHeight));

            IRenderTypeBuffer.Impl renderType = IRenderTypeBuffer.immediate(Tessellator.getInstance().getBuilder());
            mStack.translate(0.0D, 0.0D, zLevel);

            int tooltipTop = tooltipY;

            for (int lineNumber = 0; lineNumber < textLines.size(); ++lineNumber)
            {
                ITextProperties line = textLines.get(lineNumber);
                if (line != null) {
                    font.drawInBatch(LanguageMap.getInstance().getVisualOrder(line), tooltipX, tooltipY, -1, true, mat, renderType, false, 0, 15728880);// 15728880
                }

                if (lineNumber + 1 == titleLinesCount) {
                    tooltipY += 2;
                }

                tooltipY += 10;
            }

            renderType.endBatch();
            mStack.popPose();

            MinecraftForge.EVENT_BUS.post(new RenderTooltipEvent.PostText(stack, textLines, mStack, tooltipX, tooltipTop, font, tooltipTextWidth, tooltipHeight));

            RenderSystem.enableDepthTest();
            RenderSystem.enableRescaleNormal();
        }
    }
}
