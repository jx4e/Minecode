package com.github.jx4e.minecode.util.render;

import com.github.jx4e.minecode.util.render.style.BoxBorder;
import com.github.jx4e.minecode.util.render.style.BoxColorScheme;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Matrix4f;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author Jake (github.com/jx4e)
 * @since 11/06/2022
 **/

public class Renderer extends DrawableHelper{
    public void box(MatrixStack matrices, int x, int y, int width, int height,
                        @NotNull BoxColorScheme scheme, @Nullable BoxBorder border) {
        colorBox(matrices.peek().getPositionMatrix(), x, y, x + width, y + height, scheme);
    }

    public void box(MatrixStack matrices, float x, float y, float width, float height,
                    @NotNull BoxColorScheme scheme, @Nullable BoxBorder border) {
        colorBox(matrices.peek().getPositionMatrix(), x, y, x + width, y + height, scheme);
    }


    private void colorBox(Matrix4f matrix, int x1, int y1, int x2, int y2, BoxColorScheme colorScheme) {
        int i;
        if (x1 < x2) {
            i = x1;
            x1 = x2;
            x2 = i;
        }
        if (y1 < y2) {
            i = y1;
            y1 = y2;
            y2 = i;
        }

        BufferBuilder bufferBuilder = Tessellator.getInstance().getBuffer();
        RenderSystem.enableBlend();
        RenderSystem.disableTexture();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
        bufferBuilder.vertex(matrix, x1, y2, 0.0f).color(colorScheme.getBottomLeft().getRGB()).next();
        bufferBuilder.vertex(matrix, x2, y2, 0.0f).color(colorScheme.getBottomRight().getRGB()).next();
        bufferBuilder.vertex(matrix, x2, y1, 0.0f).color(colorScheme.getTopRight().getRGB()).next();
        bufferBuilder.vertex(matrix, x1, y1, 0.0f).color(colorScheme.getTopLeft().getRGB()).next();
        BufferRenderer.drawWithShader(bufferBuilder.end());
        RenderSystem.enableTexture();
        RenderSystem.disableBlend();
    }

    private void colorBox(Matrix4f matrix, float x1, float y1, float x2, float y2, BoxColorScheme colorScheme) {
        float i;
        if (x1 < x2) {
            i = x1;
            x1 = x2;
            x2 = i;
        }
        if (y1 < y2) {
            i = y1;
            y1 = y2;
            y2 = i;
        }

        BufferBuilder bufferBuilder = Tessellator.getInstance().getBuffer();
        RenderSystem.enableBlend();
        RenderSystem.disableTexture();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
        bufferBuilder.vertex(matrix, x1, y2, 0.0f).color(colorScheme.getBottomLeft().getRGB()).next();
        bufferBuilder.vertex(matrix, x2, y2, 0.0f).color(colorScheme.getBottomRight().getRGB()).next();
        bufferBuilder.vertex(matrix, x2, y1, 0.0f).color(colorScheme.getTopRight().getRGB()).next();
        bufferBuilder.vertex(matrix, x1, y1, 0.0f).color(colorScheme.getTopLeft().getRGB()).next();
        BufferRenderer.drawWithShader(bufferBuilder.end());
        RenderSystem.enableTexture();
        RenderSystem.disableBlend();
    }
}
