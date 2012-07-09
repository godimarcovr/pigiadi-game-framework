/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.TextureImpl;

/**
 *
 * @author matteo
 */
public class Button extends Label{
    Color bSelColor;
     boolean enabled;

    public Button(Box box, String text, int font, Color tCol, Color bCol, Color sCol,Color bSelColor) {
        super(box, text, font, tCol, bCol, sCol);
        this.bSelColor = bSelColor;
    }
    
    @Override
public void draw() {
        if (visible){
            
        bgCol.bind();
        GL11.glPushMatrix();
        {
            GL11.glTranslatef(this.shape.x, this.shape.y, 0);

            GL11.glBegin(GL11.GL_QUADS);
            {
                GL11.glVertex2f(0, 0);

                GL11.glVertex2f(this.shape.w, 0);

                GL11.glVertex2f(this.shape.w, this.shape.h);

                GL11.glVertex2f(0, this.shape.h);
            }
            GL11.glEnd();
            if (!enabled){
            borderCol.bind();
            }
            else{
            bSelColor.bind();
            }
            
            GL11.glBegin(GL11.GL_LINE_LOOP);
            {
                GL11.glVertex2f(0, 0);

                GL11.glVertex2f(this.shape.w, 0);

                GL11.glVertex2f(this.shape.w, this.shape.h);

                GL11.glVertex2f(0, this.shape.h);
            }
            GL11.glEnd();

            GL11.glPushMatrix();
            {
                GL11.glTranslatef(fontCenterPosX(), fontCenterPosY(), 0);
                GL11.glEnable(GL11.GL_BLEND);
                TextureImpl.bindNone();
                this.font.drawString(0, 0, this.text, this.textCol);
                GL11.glDisable(GL11.GL_BLEND);
            }
            GL11.glPopMatrix();
        }
        GL11.glPopMatrix();
        }
    }
    
    
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    
    
    
    
    
}
