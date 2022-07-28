package com.github.jx4e.minecode.impl.ui.editor.panes;

import com.github.jx4e.minecode.api.ui.AbstractPane;
import com.github.jx4e.minecode.impl.manager.RenderManager;
import com.github.jx4e.minecode.util.render.style.BoxColorScheme;
import net.minecraft.client.util.math.MatrixStack;

/**
 * @author Jake (github.com/jx4e)
 * @since 23/06/2022
 **/

public class EditorPanel extends AbstractPane {
    private final com.github.jx4e.minecode.api.ui.text.TextPane textPane;

    public EditorPanel(int x, int y, int width, int height, BoxColorScheme colorScheme) {
        super(x, y, width, height, colorScheme);
        textPane = new com.github.jx4e.minecode.api.ui.text.TextPane(getX(), getY(), getWidth(), getHeight(), colorScheme);
    }

    @Override
    public void draw(MatrixStack matrices, int mouseX, int mouseY) {
        /* Background */
        matrices.push();
        RenderManager.instance().getRenderer().box(
                matrices, getX(), getY(), getWidth(), getHeight(), getColorScheme(), null
        );
        matrices.pop();

        /* Text */
        matrices.push();
        textPane.draw(matrices, mouseX, mouseY);
        matrices.pop();
    }

    @Override
    public void charTyped(char chr, int modifiers) {
        super.charTyped(chr, modifiers);
        textPane.charTyped(chr, modifiers);
    }

    @Override
    public void keyPressed(int keyCode, int scanCode, int modifiers) {
        super.keyPressed(keyCode, scanCode, modifiers);
        textPane.keyPressed(keyCode, scanCode, modifiers);
    }
}