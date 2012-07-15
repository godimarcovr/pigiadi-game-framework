/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

import framework.Window;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.newdawn.slick.Color;

/**
 *
 * @author Marco
 */
public class Player extends Entity{

    public int c,r;

    public Player(float w, float h, float x, float y) {
        super(w, h, x, y);
    }

    public Player(Vec2[] vertex, float x, float y) {
        super(vertex, x, y);
    }


    @Override
    public void draw() {

        Color.white.bind();
        GL11.glPushMatrix();
        GL11.glTranslatef(this.body.getPosition().x, this.body.getPosition().y, 0);
        if(Kb.isPressed("R")) GL11.glRotatef(45, 0f, 0f, 1f);
        GL11.glBegin(GL11.GL_LINE_LOOP);
        {
            for (int j = 0; j < pS.getVertexCount(); j++) {
                GL11.glVertex2f(pS.getVertex(j).x, pS.getVertex(j).y);
                System.out.println(pS.getVertex(j).x+" // "+ pS.getVertex(j).y);
            }
            System.out.println("");
        }
        GL11.glEnd();
        GL11.glPopMatrix();
        
    }
    
    public void setMatrixCoordinates(Position p){
        this.c = (int)p.x;
        this.r = (int)p.y;
    }

    public void update(){
        if(Kb.isPressed("R"))   this.body.setTransform(this.body.getPosition(), 45);
        else{
            this.body.setTransform(this.body.getPosition(), 0);
        }
    }
}
