/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.newdawn.slick.Color;

/**
 *
 * @author matteo
 */
public class Entity {
    boolean visible;
    Position pos;
    Poly p;
    float dx, dy;

    public Entity() {
    }

    

    public Entity(Position pos) {
       visible = true;
       this.pos = pos;
       p = new Poly(18, 30);
       p.translate(pos.x, pos.y);
     }
        public Entity(Position pos, Vector[] v) {
            visible = true;
       this.pos = pos;
       p = new Poly(v.length, 30);
       p.setVertices(v);
       p.translate(pos.x, pos.y);
    }
    
    
    public void move(int delta){
        for (Vector object : this.p.getVertices()) {
            object.x = object.x += (delta * dx) / 1000;
            object.y = object.y += (delta * dy) / 1000;
        }
        p.findCenter();
    }
    
    public boolean collides(Entity e){
        return p.collide(e.p);
    }
    
     public boolean collides(Poly p){
        return p.collide(p);
    }
    
    public void draw(){
        if(visible){
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
        public void setHorizontalMovement(float dx) {
        this.dx = dx;
    }

    public void setVerticalMovement(float dy) {
        this.dy = dy;
    }

    public float getDx() {
        return dx;
    }

    public float getDy() {
        return dy;
    }
    
    public boolean collidesNext(Entity e, int delta){
        Entity eNew = new Entity();
        eNew = e;
        eNew.move(delta);
        if (this.collides(eNew)){
            eNew.move(-delta);
            return true;
        }
            return false;
        }
        
    }


