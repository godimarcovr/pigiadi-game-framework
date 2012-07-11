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
    Color color;
    Position pos;
    Poly p;
    float dx, dy;

    public Entity(Position pos, Vector[] v) {
       color = Color.white;
       visible = true;
       this.pos = pos;
       p = new Poly(v.length, 30);
       p.setVertices(v);
      // p.translate(pos.x, pos.y);
    }

    public Entity() {
    }

    public Entity(Position pos) {

         color = Color.white;
       visible = true;
       this.pos = pos;
       p = new Poly(18, 30);
   //    p.translate(pos.x, pos.y);
     }


    public void move(int delta) {
        float dex = (delta * dx) / 1000;
        float dey = (delta * dy) / 1000;
        for (Vector object : this.p.getVertices()) {
            object.x += dex;
            object.y += dey;

        }

        this.p.xCenter += dex;
        this.p.yCenter += dey;

    }

    public boolean collides(Entity e) {
        return p.collide(e.p);
    }

    public boolean collides(Poly p) {
        return p.collide(p);
    }

    
    public void draw(){
        if(visible){
        
        color.bind();
        GL11.glPushMatrix();
        GL11.glTranslatef(0, 0, 0);
        GL11.glBegin(GL11.GL_LINE_LOOP);
        {
            for (Vector object : p.getVertices()) {
                GL11.glVertex2f(object.x, object.y);
              }
            GL11.glEnd();
            GL11.glPopMatrix();
        }

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

    @Override
    protected Entity clone() {
        return new Entity(new Position(this.p.xCenter, this.p.yCenter), this.p.getCloneVertices());
    }

    public boolean collidesNext(Entity e, int delta) {
        Entity eNew = new Entity(new Position());
        Vector[] v = new Vector[e.p.getVertices().length];
        
        for (int i = 0; i < v.length; i++) {
            v[i] = new Vector(e.p.getVertices()[i].x, e.p.getVertices()[i].y);          
        }
        
        eNew.p.setVertices(v);
        eNew.dx = e.dx;
        eNew.dy = e.dy;
        eNew.visible = true;
        eNew.move(delta);
        
        System.out.print(e.p.getVertices()[0].x+" ");
        System.out.print(eNew.p.getVertices()[0].x+"\n");
        if (this.collides(eNew)){
            return true;
        }
            return false;
        }

    }

