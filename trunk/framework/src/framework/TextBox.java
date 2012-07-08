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
    public int timer;
    public boolean cursor;

    public TextBox(Box box, String text, int font, Color tCol, Color bCol, Color sCol) {
        super(box, text, font, tCol, bCol, sCol);
        this.enabled = false;
        this.cursor=false;
    }

    public void update() {
        /*if (this.isClicked()) {
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
        }*/
        if(TimerHandler.isTimeUp(timer)){
            this.cursor=!this.cursor;
        }
    }

    public void upText(String s){
        if(s.equals("SPACE")){
            this.text=this.text.concat(" ");
        }
        else{
            if(s.equals("BACK")){
                if(this.text.length()>=1){
                    this.text=this.text.substring(0, this.text.length()-1);
                }
            }
            else{
                this.text=this.text.concat(s);
            }
        }
    }

    @Override
    public void draw() {
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

            borderCol.bind();

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
                GL11.glTranslatef(font.getWidth(" "), fontCenterPosY(), 0);
                GL11.glEnable(GL11.GL_BLEND);
                TextureImpl.bindNone();
                this.font.drawString(0, 0, this.text.concat(this.cursor?"|":""), this.textCol);
                GL11.glDisable(GL11.GL_BLEND);
            }
            GL11.glPopMatrix();
        }
        GL11.glPopMatrix();
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        if(this.enabled){
            this.timer=TimerHandler.createCD(250);
        }
        else{
            TimerHandler.removeTimer(timer);
            this.cursor=false;
        }
        
    }
}
