/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package marcotestjbox;

import marcotestjbox.Game;
import framework.Window;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.Fixture;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.newdawn.slick.Color;

/**
 *
 * @author Marco
 */
public class Entity {

    private boolean isCircle;
    private PolygonShape pS;
    private CircleShape cS;
    public Body body;
    public float dx, dy;

    public Entity(float w, float h, float x, float y) {
        pS = new PolygonShape();
        pS.setAsBox(w, h);
        BodyDef bd = new BodyDef();
        bd.type = BodyType.DYNAMIC;
        bd.position = new Vec2(x, y);
        FixtureDef fd = new FixtureDef();
        fd.shape = pS;
        fd.friction = 0;
        body = Window.game2.world.createBody(bd);
        body.createFixture(fd);
    }

    public Entity(float r, float x, float y) {
        cS = new CircleShape();
        cS.m_radius = r;
        isCircle = true;
        BodyDef bd = new BodyDef();
        bd.type = BodyType.DYNAMIC;
        bd.position = new Vec2(x, y);
        FixtureDef fd = new FixtureDef();
        fd.shape = cS;
        fd.friction = 0;
        body = Window.game2.world.createBody(bd);
        body.createFixture(fd);
    }

    public Entity(Vec2[] vertex, float x, float y) {
        pS = new PolygonShape();
        pS.set(vertex, vertex.length);
        BodyDef bd = new BodyDef();
        bd.type = BodyType.DYNAMIC;
        bd.position = new Vec2(x, y);
        FixtureDef fd = new FixtureDef();
        fd.shape = pS;
        fd.friction = 0;
        body = Window.game2.world.createBody(bd);

        body.createFixture(fd);
    }

    public void move(int delta) {
        float x = (delta * dx) / 1000;
        float y = (delta * dy) / 1000;
        this.body.setLinearVelocity(new Vec2(x, y));
    }

    public void draw() {
        
        Color.white.bind();
        float delta_theta = 0.01f;
        GL11.glPushMatrix();
        GL11.glTranslatef(this.body.getPosition().x, this.body.getPosition().y, 0);
        GL11.glBegin(GL11.GL_LINE_LOOP);
        {
            if (isCircle) {
                for (float i = 0; i < 2 * Math.PI; i += delta_theta) {
                    GL11.glVertex2f((float) Math.sin(i) * cS.m_radius, (float) Math.cos(i) * cS.m_radius);
                }

            } else {
                for (int j = 0; j < pS.getVertexCount(); j++) {
                    GL11.glVertex2f(pS.getVertex(j).x, pS.getVertex(j).y);
                }
            }

        }
        GL11.glEnd();
        GL11.glPopMatrix();


    }
}