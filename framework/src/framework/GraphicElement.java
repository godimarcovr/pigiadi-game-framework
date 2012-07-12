/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;

/**
 *
 * @author matteo
 */
public class GraphicElement {
     private PolygonShape pS;
     Position p;
     
     public GraphicElement(float w, float h, float x, float y) {
        pS = new PolygonShape();
        pS.setAsBox(w, h);
        p = new Position(x, y);
        
    }
     
     public void Draw(){
        Color.white.bind();
        GL11.glPushMatrix();
        GL11.glTranslatef(p.x, p.y, 0);
        GL11.glBegin(GL11.GL_LINE_LOOP);
        {
            for (int j = 0; j < pS.getVertexCount(); j++) {
                GL11.glVertex2f(pS.getVertex(j).x, pS.getVertex(j).y);
            }
        }
        GL11.glEnd();
        GL11.glPopMatrix();


    }
     
}
