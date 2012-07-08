/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.TextureImpl;

/**
 *
 * @author matteo
 */
public class TextBox extends Label {

    public boolean enabled;

    public TextBox(Box box, String text, int font, Color tCol, Color bCol, Color sCol) {
        super(box, text, font, tCol, bCol, sCol);
        this.enabled = false;
    }

    public void update() {


        if (this.isClicked()) {
            enabled = true;
        } else {
            enabled = false;
        }

        if (enabled) {
            String s = Kb.getChars();
            if (s != "") {
                if ("BACK".equals(s)) {
                    text = text.substring(0, text.length() - 1);
                } else if ("SPACE".equals(s)) {
                    text = text + " ";
                } else if (s.length() < 2) {
                    text += s;
                }
            }
        }
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
