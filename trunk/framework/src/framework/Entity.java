/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;
import framework.Ms.*;
import java.awt.Font;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.newdawn.slick.Color;

/**
 *
 * @author matteo
 */
public class Entity {
    Position pos;
    Poly p;

    

    public Entity(Position pos) {
       this.pos = pos;
       p = new Poly(5, 30);
       p.translate(pos.x, pos.y);
       // p.setVertices(new Vector[]{new Vector(0, 0), new Vector(40, 0), new Vector(40, 40), new Vector(0, 40)});
    }
    
    
    
    public void move(float dX, float dY){
        p.translate(dX, dY);
    }
    
    public boolean collides(Entity e){
        return p.collide(e.p);
    }
    
    public void draw(){
        Color.white.bind();
        GL11.glPushMatrix();
        GL11.glTranslatef(0, 0, 0);
        GL11.glBegin(GL11.GL_LINE_LOOP);
        {
            for (Vector object : p.getVertices()) {
                GL11.glVertex2f(object.x, object.y);
            }
            
        }
        GL11.glEnd();
        GL11.glPopMatrix();
        
        
    }
    

}
